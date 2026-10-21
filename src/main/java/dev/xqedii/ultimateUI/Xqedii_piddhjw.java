package dev.xqedii.ultimateUI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Xqedii_piddhjw extends Xqedii_162huh6 {
   public static final int Xqedii_24ohys7 = 4;
   public static final int Xqedii_360856c = 18;
   public static final int Xqedii_1xocs63 = 10;
   public static final int Xqedii_38w5ozq = 70;
   public static final int Xqedii_1rjqp83 = 110;

   public Xqedii_piddhjw(File var1) {
      super(var1);
   }

   public Map<String, Object> Xqedii_36hjqhg(Xqedii_2kb1ns6 var1, BedrockPackTextureExporter var2, String var3, List<String> var4, Map<String, Object> var5, boolean var6) throws IOException {
      {
         Xqedii_39t0x33 var7 = new Xqedii_39t0x33(var1, var2, var3, var4, var5, var6);
         Map var8 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var8.put(
            "type",
            "panel"
         );
         String var10001 = "size";
         Object[] var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var8.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var8.put(
            "anchor_from",
            "top_left"
         );
         var8.put(
            "anchor_to",
            "top_left"
         );
         var10001 = "offset";
         var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var8.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var8.put("collection_name", var7.collection());
         var10001 = "bindings";
         var10002 = new Object[2];
         var10002[0] = Xqedii_3sbajo5();
         var10002[1] = Xqedii_2jx1t4g(
            var6 ? var1.Xqedii_33bg1mh() : var1.Xqedii_s8xbs95(), true
         );
         var8.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         GuiBoundingBox var9 = new GuiBoundingBox(var1.Xqedii_2g46lck, var1.Xqedii_1m61jit, var1.Xqedii_12g62h3, var1.Xqedii_r5a1fhj);
         List var10 = this.Xqedii_azbn0pf(var7, "", var9);
         ArrayList var11 = new ArrayList();
         if (var6) {
            var11.add(Xqedii_2dr0g9h("submit", this.Xqedii_3m2j2tt()));
         }

         var11.add(Xqedii_2dr0g9h("fit", this.Xqedii_2zb0cvs(var1, var10)));
         var8.put("controls", var11);
         Xqedii_11xm6r3(var8, var7.collection());
         if (this.Xqedii_13gj34d && !var6) {
            var10.addAll(
               this.Xqedii_1aakgur(
                  var7, (var1.Xqedii_ggpkjmc.size() + 4) * 4
               )
            );
         }

         return var8;
      }
   }

   public static void Xqedii_11xm6r3(Object var0, String var1) {
      if (var0 instanceof Map var2
         && var2.get("controls") instanceof List var3) {
         int var11 = 0;

         for (Object var6 : var3) {
            if (var6 instanceof Map var7) {
               for (Object var9 : var7.values()) {
                  if (var9 instanceof Map var10
                     && var10.containsKey(
                        "collection_index"
                     )) {
                     var11 = 1;
                     Xqedii_ru3jy9x(var10, var1);
                  }

                  Xqedii_11xm6r3(var9, var1);
               }
            }
         }

         if (var11 != 0) {
            if ("panel"
               .equals(var2.get("type"))) {
               var2.put(
                  "type",
                  "collection_panel"
               );
            }

            var2.putIfAbsent("collection_name", var1);
         }
      }
   }

   public static void Xqedii_ru3jy9x(Map<String, Object> var0, String var1) {
      {
         ArrayList var2 = var0.get("bindings") instanceof List var3
            ? new ArrayList(var3)
            : new ArrayList();

         for (Object var7 : var2) {
            if (var7 instanceof Map var5
               && "collection_details"
                  .equals(var5.get("binding_type"))) {
               return;
            }
         }

         var2.add(0, Xqedii_35q5x1t(var1));
         var0.put("bindings", var2);
      }
   }

   public Map<String, Object> Xqedii_2zb0cvs(Xqedii_2kb1ns6 var1, List<Object> var2) {
      {
         double var3 = Math.max(Double.longBitsToDouble(4607182418800017408L), var1.Xqedii_12g62h3);
         double var5 = Math.max(Double.longBitsToDouble(4607182418800017408L), var1.Xqedii_r5a1fhj);
         double var7 = var5 / var3 * Double.longBitsToDouble(4636737291354636288L);
         double var9 = var3 / var5 * Double.longBitsToDouble(4636737291354636288L);
         Map var11 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var11.put(
            "type",
            "panel"
         );
         String var10001 = "size";
         Object[] var10002 = new Object[2];
         var10002[0] = Xqedii_2jjyraz(
            var9, "y"
         );
         var10002[1] = "100%";
         var11.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var11.put(
            "anchor_from",
            "center"
         );
         var11.put(
            "anchor_to",
            "center"
         );
         var10001 = "offset";
         var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var11.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var11.put("controls", var2);
         Map var12 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var12.put(
            "type",
            "panel"
         );
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = Xqedii_2jjyraz(
            var7, "x"
         );
         var12.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "max_size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var12.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var12.put(
            "anchor_from",
            "center"
         );
         var12.put(
            "anchor_to",
            "center"
         );
         var10001 = "offset";
         var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var12.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "controls";
         var10002 = new Object[1];
         var10002[0] = Xqedii_2dr0g9h(
            "canvas", var11
         );
         var12.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         Map var13 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var13.put(
            "type",
            "panel"
         );
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = Xqedii_1vomtf5(this.Xqedii_rnovv49);
         var10002[1] = Xqedii_1vomtf5(this.Xqedii_rnovv49 * var5 / var3);
         var13.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "max_size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var13.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var13.put(
            "anchor_from",
            "center"
         );
         var13.put(
            "anchor_to",
            "center"
         );
         var10001 = "offset";
         var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var13.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var13.put(
            "layer", 2
         );
         var10001 = "controls";
         var10002 = new Object[1];
         var10002[0] = Xqedii_2dr0g9h(
            "aspect", var12
         );
         var13.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         return var13;
      }
   }

   public Map<String, Object> Xqedii_3m2j2tt() {
      {
         Map var1 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var1.put(
            "type",
            "button"
         );
         String var10001 = "size";
         Object[] var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var1.put(
            "anchor_from",
            "top_left"
         );
         var1.put(
            "anchor_to",
            "top_left"
         );
         var10001 = "offset";
         var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var1.put("layer", 1);
         var1.put(
            "default_control",
            "default"
         );
         var1.put(
            "hover_control",
            "hover"
         );
         var1.put(
            "pressed_control",
            "pressed"
         );
         var10001 = "controls";
         var10002 = new Object[3];
         var10002[0] = Xqedii_2dr0g9h(
            "default", Xqedii_76waj0d()
         );
         var10002[1] = Xqedii_2dr0g9h(
            "hover", Xqedii_76waj0d()
         );
         var10002[2] = Xqedii_2dr0g9h(
            "pressed", Xqedii_76waj0d()
         );
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "button_mappings";
         var10002 = new Object[2];
         var10002[0] = Xqedii_1qdin93(
            "button.menu_select",
            "button.submit_custom_form",
            "pressed"
         );
         var10002[1] = Xqedii_1qdin93(
            "button.menu_ok",
            "button.submit_custom_form",
            "pressed"
         );
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         return var1;
      }
   }

   public static Map<String, Object> Xqedii_76waj0d() {
      {
         Map var0 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var0.put(
            "type",
            "panel"
         );
         return var0;
      }
   }

   public List<Object> Xqedii_azbn0pf(Xqedii_39t0x33 var1, String var2, GuiBoundingBox var3) throws IOException {
      {
         ArrayList var4 = new ArrayList();
         int var5 = 1;

         for (Xqedii_v7p1hqx var7 : var1.page().Xqedii_ggpkjmc) {
            if (var2.equals(this.Xqedii_1dsrufb(var1.page(), var7))) {
               var4.addAll(this.Xqedii_1eq9emc(var1, var7, var3, var5));
               var5 += 4;
            }
         }

         return var4;
      }
   }

   public String Xqedii_1dsrufb(Xqedii_2kb1ns6 var1, Xqedii_v7p1hqx var2) {
      {
         String var3 = var2.Xqedii_76cq2mh == null ? "" : var2.Xqedii_76cq2mh;
         return !var3.isBlank() && var1.Xqedii_3ow3583.containsKey(var3)
            ? var3
            : "";
      }
   }

   public List<Object> Xqedii_1aakgur(Xqedii_39t0x33 var1, int var2) {
      {
         Xqedii_2kb1ns6 var3 = var1.page();
         int var4 = -1;

         for (Xqedii_v7p1hqx var6 : var3.Xqedii_ggpkjmc) {
            if (var6.Xqedii_1kwgg41 && var6.Xqedii_21n4stt >= 0) {
               var4 = var6.Xqedii_21n4stt;
               break;
            }
         }

         for (Xqedii_v7p1hqx var23 : var3.Xqedii_ggpkjmc) {
            if (var4 < 0 && var23.Xqedii_2hjfin7 >= 0) {
               var4 = var23.Xqedii_2hjfin7;
            }
         }

         ArrayList var22 = new ArrayList();
         if (var4 < 0) {
            return var22;
         } else {
            int var24 = Math.max(1, var3.Xqedii_1mt8sib());
            String var7 = this.Xqedii_2pixreh(
               var1,
               "probe_cell",
               false
            );
            String var8 = this.Xqedii_2pixreh(
               var1,
               "probe_cell_details",
               true
            );
            Map var9 = Xqedii_3aun5hx(var4);
            String var10001 = "bindings";
            Object[] var10002 = new Object[1];
            var10002[0] = Xqedii_gwex9af();
            var9.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "controls";
            var10002 = new Object[1];
            var10002[0] = Xqedii_2dr0g9h(
               "text",
               Xqedii_1yb4c77(true)
            );
            var9.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            Map var10 = Xqedii_3aun5hx(var4);
            var10001 = "controls";
            var10002 = new Object[1];
            var10002[0] = Xqedii_2dr0g9h(
               "text",
               Xqedii_1yb4c77(true)
            );
            var10.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            Map var11 = Xqedii_1yb4c77(true);
            var11.put("collection_index", var4);
            var10001 = "bindings";
            var10002 = new Object[2];
            var10002[0] = Xqedii_gwex9af();
            var10002[1] = Xqedii_31egszk(
               "#form_button_text"
            );
            var11.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            Map var12 = Xqedii_wm6s3kz(var4, var24, var7);
            Map var13 = Xqedii_wm6s3kz(var4, var24, var8);
            Map var14 = Xqedii_29ge1r6.Xqedii_15tec8i();
            String[] var10000 = new String[4];
            var10000[0] = "button";
            var10000[1] = "label";
            var10000[2] = "header";
            var10000[3] = "divider";
            String[] var15 = var10000;
            int var16 = var15.length;

            for (int var17 = 0; var17 < var16; var17++) {
               String var18 = var15[var17];
               var14.put(var18, Xqedii_bu7edj69h5oy(var8));
            }

            Map var25 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var25.put(
               "name",
               "buttons"
            );
            var25.put("control_ids", var14);
            Map var26 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var26.put(
               "binding_name",
               "#form_button_contents"
            );
            var26.put(
               "binding_name_override",
               "#collection_length"
            );
            Map var27 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var27.put(
               "type",
               "stack_panel"
            );
            var27.put(
               "orientation",
               "vertical"
            );
            var27.put(
               "anchor_from",
               "top_left"
            );
            var27.put(
               "anchor_to",
               "top_left"
            );
            var10001 = "offset";
            var10002 = new Object[2];
            var10002[0] = 0;
            var10002[1] = -var4 * 10;
            var27.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "size";
            var10002 = new Object[2];
            var10002[0] = 110;
            var10002[1] = "100%c";
            var27.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var27.put(
               "collection_name",
               "form_buttons"
            );
            var27.put("factory", var25);
            var10001 = "bindings";
            var10002 = new Object[1];
            var10002[0] = var26;
            var27.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            Object[] var29 = new Object[2];
            var29[0] = "A cpanel+details";
            var29[1] = var9;
            Object[] var36 = new Object[2];
            var36[0] = "B cpanel";
            var36[1] = var10;
            var10002 = new Object[2];
            var10002[0] = "C cpanel label";
            var10002[1] = var11;
            Object[] var10003 = new Object[2];
            var10003[0] = "D grid";
            var10003[1] = var12;
            Object[] var10004 = new Object[2];
            var10004[0] = "E grid+details";
            var10004[1] = var13;
            Object[] var10005 = new Object[2];
            var10005[0] = "F factory";
            var10005[1] = var27;
            List var28 = List.of(var29, var36, var10002, var10003, var10004, var10005);

            for (int var19 = 0; var19 < var28.size(); var19++) {
               Object[] var20 = (Object[])var28.get(var19);
               var22.add(
                  Xqedii_2dr0g9h(
                     Xqedii_97ftwcdn3wj8(var19),
                     this.Xqedii_oer3xnh(
                        var19, (String)var20[0], var20[1], var2
                     )
                  )
               );
            }

            return var22;
         }
      }
   }

   public Map<String, Object> Xqedii_oer3xnh(int var1, String var2, Object var3, int var4) {
      {
         Map var5 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var5.put(
            "type",
            "collection_panel"
         );
         var5.put(
            "anchor_from",
            "top_left"
         );
         var5.put(
            "anchor_to",
            "top_left"
         );
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = 70;
         var10002[1] = 0;
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = 110;
         var10002[1] = 10;
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var5.put(
            "clips_children",
            Boolean.valueOf(true)
         );
         var5.put(
            "collection_name",
            "form_buttons"
         );
         var10001 = "controls";
         var10002 = new Object[1];
         var10002[0] = Map.of(
            "probe", var3
         );
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         Map var6 = Xqedii_1yb4c77(false);
         var6.put("text", var2);
         var10001 = "color";
         var10002 = new Object[3];
         var10002[0] = Double.longBitsToDouble(4607182418800017408L);
         var10002[1] = Double.longBitsToDouble(4607182418800017408L);
         var10002[2] = Double.longBitsToDouble(0L);
         var6.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         Map var7 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var7.put(
            "type",
            "panel"
         );
         var7.put(
            "anchor_from",
            "top_left"
         );
         var7.put(
            "anchor_to",
            "top_left"
         );
         var10001 = "offset";
         var10002 = new Object[2];
         var10002[0] = 2;
         var10002[1] = 2
            + var1 * 11;
         var7.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = 180;
         var10002[1] = 10;
         var7.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var7.put("layer", var4 + var1);
         var7.put(
            "collection_name",
            "form_buttons"
         );
         var10001 = "controls";
         var10002 = new Object[2];
         var10002[0] = Xqedii_2dr0g9h(
            "caption", var6
         );
         var10002[1] = Xqedii_2dr0g9h(
            "value", var5
         );
         var7.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         return var7;
      }
   }

   public static Map<String, Object> Xqedii_wm6s3kz(int var0, int var1, String var2) {
      {
         Map var3 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var3.put(
            "type",
            "grid"
         );
         var3.put(
            "anchor_from",
            "top_left"
         );
         var3.put(
            "anchor_to",
            "top_left"
         );
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = -var0 * 10;
         var3.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = 110;
         var10002[1] = var1 * 10;
         var3.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "grid_dimensions";
         var10002 = new Object[2];
         var10002[0] = 1;
         var10002[1] = var1;
         var3.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var3.put("grid_item_template", var2);
         var3.put(
            "collection_name",
            "form_buttons"
         );
         return var3;
      }
   }

   public static Map<String, Object> Xqedii_3aun5hx(int var0) {
      {
         Map var1 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var1.put(
            "type",
            "panel"
         );
         var1.put(
            "anchor_from",
            "top_left"
         );
         var1.put(
            "anchor_to",
            "top_left"
         );
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var1.put("collection_index", var0);
         return var1;
      }
   }

   public static Map<String, Object> Xqedii_1yb4c77(boolean var0) {
      {
         Map var1 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var1.put(
            "type",
            "label"
         );
         var1.put(
            "anchor_from",
            "left_middle"
         );
         var1.put(
            "anchor_to",
            "left_middle"
         );
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = "default";
         var10002[1] = "default";
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "color";
         var10002 = new Object[3];
         var10002[0] = Double.longBitsToDouble(4607182418800017408L);
         var10002[1] = Double.longBitsToDouble(4607182418800017408L);
         var10002[2] = Double.longBitsToDouble(4607182418800017408L);
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var1.put(
            "font_scale_factor",
            Double.longBitsToDouble(4605380978949069210L)
         );
         var1.put(
            "localize",
            Boolean.valueOf(false)
         );
         var1.put(
            "shadow",
            Boolean.valueOf(true)
         );
         if (var0) {
            var1.put(
               "text",
               "#form_button_text"
            );
            var10001 = "bindings";
            var10002 = new Object[1];
            var10002[0] = Xqedii_31egszk(
               "#form_button_text"
            );
            var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         }

         return var1;
      }
   }

   public String Xqedii_2pixreh(Xqedii_39t0x33 var1, String var2, boolean var3) {
      {
         if (!var1.namespace().containsKey(var2)) {
            Map var4 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var4.put(
               "type",
               "panel"
            );
            String var10001 = "size";
            Object[] var10002 = new Object[2];
            var10002[0] = 110;
            var10002[1] = 10;
            var4.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            if (var3) {
               var10001 = "bindings";
               var10002 = new Object[1];
               var10002[0] = Xqedii_gwex9af();
               var4.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            }

            var10001 = "controls";
            var10002 = new Object[1];
            var10002[0] = Xqedii_2dr0g9h(
               "text",
               Xqedii_1yb4c77(true)
            );
            var4.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var1.namespace().put(var2, var4);
         }

         return Xqedii_2cr8l1i38i62x(var2);
      }
   }

   public List<Object> Xqedii_1eq9emc(Xqedii_39t0x33 var1, Xqedii_v7p1hqx var2, GuiBoundingBox var3, int var4) throws IOException {
      {
         Xqedii_2kb1ns6 var5 = var1.page();
         ArrayList var6 = new ArrayList();
         String var7 = var2.Xqedii_30bxc0n(var2.Xqedii_3mzx85f);
         int var8 = var1.slotIndex(var5.Xqedii_sytzb79);
         int var9 = var2.Xqedii_33uodds && var5.Xqedii_sytzb79 >= 0 && var8 >= 0
            ? 1
            : 0;
         int var10 = var9 != 0 ? 0 : var4;
         Map var11 = this.Xqedii_35fl8n4(var1, var2, false);
         if (var11 != null) {
            this.Xqedii_ibdapof(var11, var3, var2.Xqedii_3l6f7ti, var2.Xqedii_10bvdlm, var2.Xqedii_35yqe6o, var2.Xqedii_zgu3xeu);
            var11.put("layer", var10);
            var6.add(Xqedii_2dr0g9h(var7, var11));
         }

         if (var2.Xqedii_221jxyb()) {
            var6.addAll(this.Xqedii_609wugh(var1, var2, var3, var7, var10));
         }

         if (var2.Xqedii_350upak != null) {
            Xqedii_3duv2su var12 = var2.Xqedii_350upak;
            Map var13 = this.Xqedii_13roph7(var1, var12, var3, var10 + 1);
            String var14 = var12.Xqedii_3epk1e6()
               ? "@common.scrolling_panel"
               : "";
            var6.add(Xqedii_2dr0g9h(Xqedii_39gsnup49bqev(var7, var14), var13));
         }

         if (!var1.overlay() && (var2.Xqedii_pxr9qfs() || var2.Xqedii_5ommxdp)) {
            var6.add(
               Xqedii_2dr0g9h(Xqedii_32cs2999t20ib(var7), this.Xqedii_21bs51y(var1, var2, var3, var10 + 2))
            );
         }

         if (var9 == 0) {
            return var6;
         } else {
            Map var15 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var15.put(
               "type",
               "panel"
            );
            var15.put(
               "anchor_from",
               "top_left"
            );
            var15.put(
               "anchor_to",
               "top_left"
            );
            String var10001 = "offset";
            Object[] var10002 = new Object[2];
            var10002[0] = 0;
            var10002[1] = 0;
            var15.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "size";
            var10002 = new Object[2];
            var10002[0] = "100%";
            var10002[1] = "100%";
            var15.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var15.put("layer", var4);
            var15.put("collection_index", var8);
            var10001 = "bindings";
            var10002 = new Object[2];
            var10002[0] = Xqedii_12kquew(var1.collection(), var1.textProperty());
            var10002[1] = Xqedii_1x5x1vv(
               Xqedii_1pxa11tm7ct48(var1.textProperty(), var2.Xqedii_8os42uu(), var1.textProperty()),
               "#visible"
            );
            var15.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var15.put("controls", var6);
            ArrayList var16 = new ArrayList();
            var16.add(Xqedii_2dr0g9h(Xqedii_erupvtrpxpha(var7), var15));
            return var16;
         }
      }
   }

   public List<Object> Xqedii_609wugh(Xqedii_39t0x33 var1, Xqedii_v7p1hqx var2, GuiBoundingBox var3, String var4, int var5) {
      {
         ArrayList var6 = new ArrayList();
         Map var7 = this.Xqedii_3jkhq3u(var1, var2, var3, var2.Xqedii_21n4stt, var2.Xqedii_1vycaiv, var5 + 1);
         Map var8 = this.Xqedii_3jkhq3u(var1, var2, var3, var2.Xqedii_2hjfin7, var2.Xqedii_2cab797, var5 + 1);
         if (var1.overlay() && var2.Xqedii_3ma30bi >= 0) {
            String var9 = Xqedii_2kb1ns6.Xqedii_2jnvi3m(var2.Xqedii_3ma30bi);
            Map var10 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var10.put(
               "type",
               "panel"
            );
            var10.put(
               "anchor_from",
               "top_left"
            );
            var10.put(
               "anchor_to",
               "top_left"
            );
            String var10001 = "offset";
            Object[] var10002 = new Object[2];
            var10002[0] = 0;
            var10002[1] = 0;
            var10.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "size";
            var10002 = new Object[2];
            var10002[0] = "100%";
            var10002[1] = "100%";
            var10.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10.put(
               "layer",
               var5 + 1
            );
            var10001 = "bindings";
            var10002 = new Object[2];
            var10002[0] = Xqedii_3sbajo5();
            var10002[1] = Xqedii_2jx1t4g(var9, false);
            var10.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "controls";
            var10002 = new Object[2];
            var10002[0] = Xqedii_2dr0g9h(Xqedii_x08fbmavgzu0(var4), var7);
            var10002[1] = Xqedii_2dr0g9h(Xqedii_1ub2zvx9j8sd(var4), var8);
            var10.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var6.add(Xqedii_2dr0g9h(Xqedii_2a2t6wkct3i55(var4), var10));
            Map var11 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var11.put(
               "type",
               "panel"
            );
            this.Xqedii_ibdapof(var11, var3, var2.Xqedii_16l0t04, var2.Xqedii_10bvdlm, var2.Xqedii_3b8346h, var2.Xqedii_zgu3xeu);
            var11.put(
               "layer",
               (var1.page().Xqedii_ggpkjmc.size() + 2) * 4
            );
            var11.put(
               "collection_index",
               0
            );
            var10001 = "bindings";
            var10002 = new Object[2];
            var10002[0] = Xqedii_3sbajo5();
            var10002[1] = Xqedii_2jx1t4g(var9, true);
            var11.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "controls";
            var10002 = new Object[1];
            var10002[0] = Xqedii_2dr0g9h(
               "edit@settings_common.option_text_edit_control",
               this.Xqedii_1k7yz9c(var2)
            );
            var11.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var6.add(Xqedii_2dr0g9h(Xqedii_75ww21eg1z7h(var4), var11));
            return var6;
         } else {
            var6.add(Xqedii_2dr0g9h(Xqedii_3ka83miye54nd(var4), var7));
            var6.add(Xqedii_2dr0g9h(Xqedii_1pr6d1yql0kdd(var4), var8));
            return var6;
         }
      }
   }

   public Map<String, Object> Xqedii_1k7yz9c(Xqedii_v7p1hqx var1) {
      {
         Map var2 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var2.put(
            "anchor_from",
            "center"
         );
         var2.put(
            "anchor_to",
            "center"
         );
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var2.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "min_size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = 18;
         var2.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var2.put(
            "layer", 1
         );
         var10001 = "$option_text_edit_control_size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var2.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var2.put(
            "$max_text_edit_length",
            var1.Xqedii_3k742qq
         );
         var2.put(
            "$option_binding_name",
            "#custom_input_text"
         );
         var2.put(
            "$option_place_holder_text",
            "#custom_placeholder_text"
         );
         var2.put(
            "$option_label",
            ""
         );
         var2.put(
            "$option_enabled_binding_name",
            ""
         );
         var2.put(
            "$enabled",
            Boolean.valueOf(true)
         );
         var2.put(
            "$text_box_name",
            "custom_input"
         );
         var2.put(
            "$text_edit_box_grid_collection_name",
            "custom_form"
         );
         var2.put(
            "$text_edit_box_content_binding_type",
            "collection"
         );
         var2.put(
            "$text_edit_box_content_binding_name",
            "#custom_input_text"
         );
         var2.put(
            "$text_edit_box_placeholder_content_binding_type",
            "collection"
         );
         var2.put(
            "$text_edit_box_placeholder_content_binding_name",
            "#custom_placeholder_text"
         );
         var2.put(
            "$text_alignment",
            Xqedii_2d62dgj(var1.Xqedii_34ivekx)
         );
         var2.put(
            "$text_box_text_color",
            Xqedii_q9nfioh(var1.Xqedii_1vycaiv)
         );
         var2.put(
            "$place_holder_text_color",
            Xqedii_q9nfioh(var1.Xqedii_2cab797)
         );
         return var2;
      }
   }

   public Map<String, Object> Xqedii_21bs51y(Xqedii_39t0x33 var1, Xqedii_v7p1hqx var2, GuiBoundingBox var3, int var4) throws IOException {
      {
         Map var5 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var5.put(
            "type",
            "button"
         );
         this.Xqedii_ibdapof(var5, var3, var2.Xqedii_3l6f7ti, var2.Xqedii_10bvdlm, var2.Xqedii_35yqe6o, var2.Xqedii_zgu3xeu);
         var5.put("layer", var4);
         var5.put(
            "default_control",
            "default"
         );
         var5.put(
            "hover_control",
            "hover"
         );
         var5.put(
            "pressed_control",
            "pressed"
         );
         ArrayList var6 = new ArrayList();
         var6.add(Xqedii_2dr0g9h("default", Xqedii_76waj0d()));
         var6.add(
            Xqedii_2dr0g9h(
               "hover",
               this.Xqedii_306x0cr(this.Xqedii_v5188du(var1, var2), var2, true)
            )
         );
         var6.add(
            Xqedii_2dr0g9h(
               "pressed",
               this.Xqedii_306x0cr(this.Xqedii_v5188du(var1, var2), var2, true)
            )
         );
         var5.put("controls", var6);
         if (var2.Xqedii_pxr9qfs()) {
            var5.put(
               "collection_index", var2.Xqedii_2sh7mex
            );
            String var10001 = "bindings";
            Object[] var10002 = new Object[1];
            var10002[0] = Xqedii_gwex9af();
            var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "button_mappings";
            var10002 = new Object[2];
            var10002[0] = Xqedii_1qdin93(
               "button.menu_select",
               "button.form_button_click",
               "pressed"
            );
            var10002[1] = Xqedii_1qdin93(
               "button.menu_ok",
               "button.form_button_click",
               "pressed"
            );
            var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         }

         return var5;
      }
   }

   public Map<String, Object> Xqedii_v5188du(Xqedii_39t0x33 var1, Xqedii_v7p1hqx var2) throws IOException {
      {
         return var2.Xqedii_5ommxdp && !var2.Xqedii_210a5o9() && !var2.Xqedii_323l2jb()
            ? this.Xqedii_35fl8n4(var1, var2, true)
            : Xqedii_76waj0d();
      }
   }

   public Map<String, Object> Xqedii_13roph7(Xqedii_39t0x33 var1, Xqedii_3duv2su var2, GuiBoundingBox var3, int var4) throws IOException {
      {
         Xqedii_2kb1ns6 var5 = var1.page();
         String var6 = Xqedii_3s6jh58cyhdj5(
            var1.overlay()
               ? "i"
               : "",
            var5.Xqedii_2kz3xjg,
            Xqedii_296z12u(var2.Xqedii_2h2vht7),
            var1.namespace().size()
         );
         GuiBoundingBox var7 = new GuiBoundingBox(var2.Xqedii_366js72, var2.Xqedii_1zzzeuc, var2.Xqedii_2543rc0(), var2.Xqedii_2vric3z());
         Map var8 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var8.put(
            "type",
            "panel"
         );
         var8.put(
            "anchor_from",
            "top_left"
         );
         var8.put(
            "anchor_to",
            "top_left"
         );
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var8.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = Xqedii_rejug23(
            var7.width()
               / Math.max(Double.longBitsToDouble(4607182418800017408L), var2.Xqedii_3ihceot)
               * Double.longBitsToDouble(4636737291354636288L)
         );
         var10002[1] = Xqedii_rejug23(
            var7.height()
               / Math.max(Double.longBitsToDouble(4607182418800017408L), var2.Xqedii_232d414)
               * Double.longBitsToDouble(4636737291354636288L)
         );
         var8.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var8.put("collection_name", var1.collection());
         var1.namespace().put(var6, var8);
         var8.put(
            "controls",
            this.Xqedii_azbn0pf(var1, var2.Xqedii_2h2vht7, var7)
         );
         Xqedii_11xm6r3(var8, var1.collection());
         if (!var2.Xqedii_3epk1e6()) {
            if (!var1.overlay()) {
               var1.warnings().add(Xqedii_8rd1nxorjxad(var5.Xqedii_3j6s705, var2.Xqedii_2h2vht7));
            }

            Map var15 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var15.put(
               "type",
               "panel"
            );
            this.Xqedii_ibdapof(var15, var3, var2.Xqedii_366js72, var2.Xqedii_1zzzeuc, var2.Xqedii_3ihceot, var2.Xqedii_232d414);
            var15.put("layer", var4);
            var15.put(
               "clips_children",
               Boolean.valueOf(true)
            );
            var10001 = "controls";
            var10002 = new Object[1];
            var10002[0] = Xqedii_2dr0g9h(Xqedii_1sjuj5e9a5mqj(var6), Xqedii_29ge1r6.Xqedii_15tec8i());
            var15.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            return var15;
         } else {
            double var9 = this.Xqedii_rnovv49
               / Math.max(Double.longBitsToDouble(4607182418800017408L), var5.Xqedii_12g62h3);
            int var11 = (int)Math.max(2L, Math.round(var2.Xqedii_1k53pgm * var9));
            String var12 = var2.Xqedii_37hovis
               ? this.Xqedii_2bv387c(
                  var1,
                  Xqedii_yoz8q9j7cio5(var2.Xqedii_2gekh4q),
                  var2.Xqedii_2gekh4q,
                  Double.longBitsToDouble(4607182418800017408L)
               )
               : "common.empty_panel";
            String var13 = var2.Xqedii_37hovis
               ? this.Xqedii_2bv387c(
                  var1,
                  Xqedii_2agk4vwl9w8li(var2.Xqedii_rlw6fkn),
                  var2.Xqedii_rlw6fkn,
                  Double.longBitsToDouble(4599976659396224614L)
               )
               : "common.empty_panel";
            Map var14 = Xqedii_29ge1r6.Xqedii_15tec8i();
            this.Xqedii_ibdapof(var14, var3, var2.Xqedii_366js72, var2.Xqedii_1zzzeuc, var2.Xqedii_3ihceot, var2.Xqedii_232d414);
            var14.put("layer", var4);
            var14.put(
               "$show_background",
               Boolean.valueOf(false)
            );
            var14.put(
               "$scrolling_content",
               Xqedii_2t13q3hkp0ndq(var6)
            );
            var14.put(
               "$scroll_bar_contained",
               Boolean.valueOf(true)
            );
            var14.put(
               "$scroll_bar_contained_touch",
               Boolean.valueOf(true)
            );
            var14.put(
               "$allow_scrolling_even_when_content_fits",
               Boolean.valueOf(false)
            );
            var10001 = "$scroll_size";
            var10002 = new Object[2];
            var10002[0] = var2.Xqedii_37hovis ? var11 : 0;
            var10002[1] = "100% - 4px";
            var14.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "$scroll_size_touch";
            var10002 = new Object[2];
            var10002[0] = var2.Xqedii_37hovis ? var11 : 0;
            var10002[1] = "100% - 4px";
            var14.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var14.put(
               "$scroll_box_visible",
               var2.Xqedii_37hovis
            );
            var14.put(
               "$scroll_box_visible_touch",
               var2.Xqedii_37hovis
            );
            var10001 = "$scroll_bar_right_padding_size";
            var10002 = new Object[2];
            var10002[0] = var2.Xqedii_37hovis
               ? 1
               : 0;
            var10002[1] = 0;
            var14.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "$scroll_bar_right_padding_size_touch";
            var10002 = new Object[2];
            var10002[0] = var2.Xqedii_37hovis
               ? 1
               : 0;
            var10002[1] = 0;
            var14.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var14.put(
               "$scroll_track_image_control",
               var13
            );
            var14.put(
               "$scroll_box_mouse_image_control",
               var12
            );
            var14.put(
               "$scroll_box_touch_image_control",
               var12
            );
            return var14;
         }
      }
   }

   public String Xqedii_2bv387c(Xqedii_39t0x33 var1, String var2, String var3, double var4) {
      {
         if (!var1.namespace().containsKey(var2)) {
            Map var6 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var6.put(
               "type",
               "image"
            );
            var6.put("texture", var1.solidTexture());
            var6.put(
               "keep_ratio",
               Boolean.valueOf(false)
            );
            var6.put("color", Xqedii_q9nfioh(var3));
            var6.put("alpha", var4);
            String var10001 = "size";
            Object[] var10002 = new Object[2];
            var10002[0] = "100%";
            var10002[1] = "100%";
            var6.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var6.put(
               "layer", 4
            );
            var1.namespace().put(var2, var6);
         }

         return Xqedii_1zj2lx955uvh5(var2);
      }
   }

   public static String Xqedii_296z12u(String var0) {
      {
         StringBuilder var1 = new StringBuilder();
         String var2 = var0 == null ? "" : var0;

         for (int var3 = 0;
            var3 < var2.length() && var1.length() < 32;
            var3++
         ) {
            char var4 = Character.toLowerCase(var2.charAt(var3));
            if (var4 >= 97 && var4 <= 122
               || var4 >= 48 && var4 <= 57
               || var4 == 95) {
               var1.append(var4);
            }
         }

         return var1.length() == 0 ? "box" : var1.toString();
      }
   }

   public Map<String, Object> Xqedii_306x0cr(Map<String, Object> var1, Xqedii_v7p1hqx var2, boolean var3) {
      {
         if (var1 == null) {
            var1 = Xqedii_76waj0d();
         }

         var1.put(
            "anchor_from",
            "top_left"
         );
         var1.put(
            "anchor_to",
            "top_left"
         );
         if (!var3) {
            String var13 = "offset";
            Object[] var16 = new Object[2];
            var16[0] = 0;
            var16[1] = 0;
            var1.put(var13, Xqedii_29ge1r6.Xqedii_2fhwu22(var16));
            var13 = "size";
            var16 = new Object[2];
            var16[0] = "100%";
            var16[1] = "100%";
            var1.put(var13, Xqedii_29ge1r6.Xqedii_2fhwu22(var16));
            return var1;
         } else {
            double var4 = var2.Xqedii_2ia1vvf;
            double var6 = var2.Xqedii_277q0eq;
            double var8 = var2.Xqedii_3qyfu7f
                  / Math.max(Double.longBitsToDouble(4607182418800017408L), var2.Xqedii_35yqe6o)
                  * Double.longBitsToDouble(4636737291354636288L)
               - (var4 - Double.longBitsToDouble(4607182418800017408L))
                  * Double.longBitsToDouble(4632233691727265792L);
            double var10 = var2.Xqedii_39mt8ms
                  / Math.max(Double.longBitsToDouble(4607182418800017408L), var2.Xqedii_zgu3xeu)
                  * Double.longBitsToDouble(4636737291354636288L)
               - (var6 - Double.longBitsToDouble(4607182418800017408L))
                  * Double.longBitsToDouble(4632233691727265792L);
            String var10001 = "offset";
            Object[] var10002 = new Object[2];
            var10002[0] = Xqedii_rejug23(var8);
            var10002[1] = Xqedii_rejug23(var10);
            var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "size";
            var10002 = new Object[2];
            var10002[0] = Xqedii_rejug23(
               var4 * Double.longBitsToDouble(4636737291354636288L)
            );
            var10002[1] = Xqedii_rejug23(
               var6 * Double.longBitsToDouble(4636737291354636288L)
            );
            var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            return var1;
         }
      }
   }

   public Map<String, Object> Xqedii_35fl8n4(Xqedii_39t0x33 var1, Xqedii_v7p1hqx var2, boolean var3) throws IOException {
      {
         Xqedii_2kb1ns6 var4 = var1.page();
         String var5 = var3 && !var2.Xqedii_1sxdt6k.isBlank() ? var2.Xqedii_1sxdt6k : var2.Xqedii_2lnnf2d;
         double var6 = Math.max(
            Double.longBitsToDouble(0L),
            Math.min(
               Double.longBitsToDouble(4607182418800017408L),
               var2.Xqedii_3dznsnb + (var3 ? var2.Xqedii_2re3coj : Double.longBitsToDouble(0L))
            )
         );
         String var8 = var2.Xqedii_272zqk2;
         int var9 = -1;
         switch (var8.hashCode()) {
            case -1217012392:
               if (var8.equals("hitbox")) {
                  var9 = 7;
               }
               break;
            case 3242771:
               if (var8.equals("item")) {
                  var9 = 6;
               }
               break;
            case 3532157:
               if (var8.equals("skin")) {
                  var9 = 5;
               }
               break;
            case 3556653:
               if (var8.equals("text")) {
                  var9 = 0;
               }
               break;
            case 100313435:
               if (var8.equals("image")) {
                  var9 = 1;
               }
               break;
            case 100358090:
               if (var8.equals("input")) {
                  var9 = 3;
               }
               break;
            case 556765310:
               if (var8.equals("player_head")) {
                  var9 = 4;
               }
               break;
            case 1385468589:
               if (var8.equals("rounded")) {
                  var9 = 2;
               }
         }

         switch (var9) {
            case 0:
               return this.Xqedii_3oe2d98(var1, var2, var5, var6, var2.Xqedii_21n4stt, var2.Xqedii_2zm1if4);
            case 1:
               String var13 = var3 && !var2.Xqedii_29vci0a.isBlank() ? var2.Xqedii_29vci0a : var2.Xqedii_3ouyp5a;
               String var11 = var1.baker().Xqedii_2uhab9t(var13);
               if (var11.isEmpty()) {
                  if (!var1.overlay()) {
                     var1.warnings().add(Xqedii_39ofzp9k51umq(var4.Xqedii_3j6s705, var13));
                  }

                  return null;
               } else {
                  Map var12 = Xqedii_29ge1r6.Xqedii_15tec8i();
                  var12.put(
                     "type",
                     "image"
                  );
                  var12.put("texture", var11);
                  var12.put(
                     "keep_ratio",
                     Boolean.valueOf(false)
                  );
                  var12.put("alpha", var6);
                  if (!"ffffff".equals(var5)) {
                     var12.put("color", Xqedii_q9nfioh(var5));
                  }

                  return var12;
               }
            case 2:
            case 3:
               if (!var2.Xqedii_1qmzhc1()) {
                  return this.Xqedii_33i66wx(var1.solidTexture(), var5, var6);
               }

               String var10 = var1.baker()
                  .Xqedii_3hykzkc(
                     var2.Xqedii_35yqe6o,
                     var2.Xqedii_zgu3xeu,
                     var2.Xqedii_21vvlvv,
                     var2.Xqedii_2wmm1xa,
                     var2.Xqedii_19ovobf,
                     var2.Xqedii_3qu3hp4,
                     var2.Xqedii_2y1xcjw
                  );
               return this.Xqedii_33i66wx(var10, var5, var6);
            case 4:
               if (var1.overlay()) {
                  return null;
               }

               this.Xqedii_3153b6r(
                  Xqedii_3vx7hyi7ljtrj(
                     var2.Xqedii_30bxc0n(var2.Xqedii_3mzx85f), var4.Xqedii_3j6s705, var3, var2.Xqedii_20r4t4p, var2.Xqedii_3rdwuj0, var4.Xqedii_1mt8sib()
                  )
               );
               return this.Xqedii_1wxgupj(var2, var6, false);
            case 5:
               if (var2.Xqedii_2fywzck) {
                  return this.Xqedii_10k67i8(var2, var6);
               } else {
                  if (var1.overlay()) {
                     return null;
                  }

                  this.Xqedii_3153b6r(
                     Xqedii_2235xqttf5qor(var2.Xqedii_30bxc0n(var2.Xqedii_3mzx85f), var4.Xqedii_3j6s705, var2.Xqedii_687jy1w, var2.Xqedii_3rdwuj0)
                  );
                  return this.Xqedii_1wxgupj(var2, var6, true);
               }
            case 6:
               return this.Xqedii_3ftwkzd(var1, var2, var6);
            case 7:
               return Xqedii_76waj0d();
            default:
               return this.Xqedii_33i66wx(var1.solidTexture(), var5, var6);
         }
      }
   }

   public Map<String, Object> Xqedii_3ftwkzd(Xqedii_39t0x33 var1, Xqedii_v7p1hqx var2, double var3) {
      {
         int var5 = var1.slotIndex(var2.Xqedii_21n4stt);
         if (var5 < 0) {
            return null;
         } else {
            String var6 = var1.textProperty();
            Map var7 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var7.put(
               "type",
               "custom"
            );
            var7.put(
               "renderer",
               "inventory_item_renderer"
            );
            var7.put(
               "anchor_from",
               "top_left"
            );
            var7.put(
               "anchor_to",
               "top_left"
            );
            String var10001 = "offset";
            Object[] var10002 = new Object[2];
            var10002[0] = 0;
            var10002[1] = 0;
            var7.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var10001 = "size";
            var10002 = new Object[2];
            var10002[0] = "100%";
            var10002[1] = "100%";
            var7.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var7.put("alpha", var3);
            var10001 = "bindings";
            var10002 = new Object[2];
            var10002[0] = Xqedii_12kquew(var1.collection(), var6);
            var10002[1] = Xqedii_1x5x1vv(
               Xqedii_bu4thunjjnen(var6), "#item_id_aux"
            );
            var7.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            Map var8 = Xqedii_29ge1r6.Xqedii_15tec8i();
            var8.put(
               "type",
               "panel"
            );
            var8.put("collection_index", var5);
            var10001 = "controls";
            var10002 = new Object[1];
            var10002[0] = Xqedii_2dr0g9h(
               "item", var7
            );
            var8.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            return var8;
         }
      }
   }

   public Map<String, Object> Xqedii_10k67i8(Xqedii_v7p1hqx var1, double var2) {
      {
         Map var4 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var4.put(
            "type",
            "custom"
         );
         var4.put(
            "renderer",
            "live_player_renderer"
         );
         Map var5 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var5.put("#look_at_cursor", var1.Xqedii_3c7r0v5);
         var4.put("property_bag", var5);
         var4.put(
            "anchor_from",
            "center"
         );
         var4.put(
            "anchor_to",
            "center"
         );
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var4.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var4.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var4.put("alpha", var2);
         Map var6 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var6.put(
            "type",
            "panel"
         );
         var10001 = "controls";
         var10002 = new Object[1];
         var10002[0] = Xqedii_2dr0g9h(
            "player", var4
         );
         var6.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         return var6;
      }
   }

   public Map<String, Object> Xqedii_1wxgupj(Xqedii_v7p1hqx var1, double var2, boolean var4) {
      {
         Map var5 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var5.put(
            "type",
            "image"
         );
         var5.put(
            "anchor_from",
            "center"
         );
         var5.put(
            "anchor_to",
            "center"
         );
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var5.put("keep_ratio", var4);
         var5.put("alpha", var2);
         var10001 = "bindings";
         var10002 = new Object[3];
         var10002[0] = Xqedii_3r2co6p(
            "#form_button_texture",
            "#texture"
         );
         var10002[1] = Xqedii_3r2co6p(
            "#form_button_texture_file_system",
            "#texture_file_system"
         );
         var10002[2] = Xqedii_1x5x1vv(
            "(not ((#texture = '') or (#texture = 'loading')))",
            "#visible"
         );
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         Map var6 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var6.put(
            "type",
            "panel"
         );
         var6.put(
            "collection_index",
            Math.max(0, var1.Xqedii_3rdwuj0)
         );
         var10001 = "controls";
         var10002 = new Object[1];
         var10002[0] = Xqedii_2dr0g9h(
            "img", var5
         );
         var6.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         return var6;
      }
   }

   public Map<String, Object> Xqedii_33i66wx(String var1, String var2, double var3) {
      {
         Map var5 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var5.put(
            "type",
            "image"
         );
         var5.put("texture", var1);
         var5.put(
            "keep_ratio",
            Boolean.valueOf(false)
         );
         var5.put("color", Xqedii_q9nfioh(var2));
         var5.put("alpha", var3);
         return var5;
      }
   }

   public Map<String, Object> Xqedii_3jkhq3u(Xqedii_39t0x33 var1, Xqedii_v7p1hqx var2, GuiBoundingBox var3, int var4, String var5, int var6) {
      {
         Map var7 = this.Xqedii_3oe2d98(
            var1,
            var2,
            var5,
            Double.longBitsToDouble(4607182418800017408L),
            var4,
            ""
         );
         var7.put(
            "clips_children",
            Boolean.valueOf(true)
         );
         this.Xqedii_ibdapof(var7, var3, var2.Xqedii_16l0t04, var2.Xqedii_10bvdlm, var2.Xqedii_3b8346h, var2.Xqedii_zgu3xeu);
         var7.put("layer", var6);
         return var7;
      }
   }

   public Map<String, Object> Xqedii_3oe2d98(Xqedii_39t0x33 var1, Xqedii_v7p1hqx var2, String var3, double var4, int var6, String var7) {
      {
         String var9 = Xqedii_2d62dgj(var2.Xqedii_34ivekx);
         int var10 = -1;
         switch (var9.hashCode()) {
            case -1364013995:
               if (var9.equals("center")) {
                  var10 = 0;
               }
               break;
            case 108511772:
               if (var9.equals("right")) {
                  var10 = 1;
               }
         }
         String var8 = switch (var10) {
            case 0 -> "center";
            case 1 -> "right_middle";
            default -> "left_middle";
         };
         Map var12 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var12.put(
            "type",
            "label"
         );
         var12.put("anchor_from", var8);
         var12.put("anchor_to", var8);
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var12.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = "default";
         var10002[1] = "default";
         var12.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var12.put("color", Xqedii_q9nfioh(var3));
         var12.put("alpha", var4);
         var12.put("font_type", Xqedii_1yvyns7(var2.Xqedii_2xkehob));
         var12.put(
            "text_alignment",
            Xqedii_2d62dgj(var2.Xqedii_34ivekx)
         );
         var12.put(
            "font_scale_factor",
            this.Xqedii_1qnyo4j(var1.page(), var2)
         );
         var12.put(
            "localize",
            Boolean.valueOf(false)
         );
         var12.put(
            "shadow",
            Boolean.valueOf(false)
         );
         Map var13 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var13.put(
            "type",
            "panel"
         );
         int var11 = var6 >= 0 ? var1.slotIndex(var6) : -1;
         if (var11 >= 0) {
            var12.put("text", var1.textProperty());
            var10001 = "bindings";
            var10002 = new Object[1];
            var10002[0] = Xqedii_12kquew(var1.collection(), var1.textProperty());
            var12.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
            var13.put("collection_index", var11);
         } else {
            var12.put(
               "text",
               var6 < 0 && var7 != null ? var7 : ""
            );
         }

         var10001 = "controls";
         var10002 = new Object[1];
         var10002[0] = Xqedii_2dr0g9h(
            "text", var12
         );
         var13.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         return var13;
      }
   }

   public void Xqedii_ibdapof(Map<String, Object> var1, GuiBoundingBox var2, double var3, double var5, double var7, double var9) {
      {
         double var11 = Math.max(Double.longBitsToDouble(4607182418800017408L), var2.width());
         double var13 = Math.max(Double.longBitsToDouble(4607182418800017408L), var2.height());
         var1.put(
            "anchor_from",
            "top_left"
         );
         var1.put(
            "anchor_to",
            "top_left"
         );
         String var10001 = "offset";
         Object[] var10002 = new Object[2];
         var10002[0] = Xqedii_rejug23(
            (var3 - var2.originX()) / var11 * Double.longBitsToDouble(4636737291354636288L)
         );
         var10002[1] = Xqedii_rejug23(
            (var5 - var2.originY()) / var13 * Double.longBitsToDouble(4636737291354636288L)
         );
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = Xqedii_rejug23(
            var7 / var11 * Double.longBitsToDouble(4636737291354636288L)
         );
         var10002[1] = Xqedii_rejug23(
            var9 / var13 * Double.longBitsToDouble(4636737291354636288L)
         );
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
      }
   }

   private static /* synthetic */ String Xqedii_bu7edj69h5oy(String string) {
        return "@" + string;
    }

    private static /* synthetic */ String Xqedii_97ftwcdn3wj8(int n) {
        return "uui_probe_" + n;
    }

    private static /* synthetic */ String Xqedii_2cr8l1i38i62x(String string) {
        return "uui." + string;
    }

    private static /* synthetic */ String Xqedii_39gsnup49bqev(String string, String string2) {
        return string + "_scroll" + string2;
    }

    private static /* synthetic */ String Xqedii_32cs2999t20ib(String string) {
        return string + "_btn";
    }

    private static /* synthetic */ String Xqedii_1pxa11tm7ct48(String string, String string2, String string3) {
        return "((" + string + " - '" + string2 + "') = " + string3 + ")";
    }

    private static /* synthetic */ String Xqedii_erupvtrpxpha(String string) {
        return string + "_vis";
    }

    private static /* synthetic */ String Xqedii_3ka83miye54nd(String string) {
        return string + "_value";
    }

    private static /* synthetic */ String Xqedii_1pr6d1yql0kdd(String string) {
        return string + "_hint";
    }

    private static /* synthetic */ String Xqedii_x08fbmavgzu0(String string) {
        return string + "_value";
    }

    private static /* synthetic */ String Xqedii_1ub2zvx9j8sd(String string) {
        return string + "_hint";
    }

    private static /* synthetic */ String Xqedii_2a2t6wkct3i55(String string) {
        return string + "_idle";
    }

    private static /* synthetic */ String Xqedii_75ww21eg1z7h(String string) {
        return string + "_typing";
    }

    private static /* synthetic */ String Xqedii_3s6jh58cyhdj5(String string, int n, String string2, int n2) {
        return "scroll_" + string + n + "_" + string2 + "_" + n2;
    }

    private static /* synthetic */ String Xqedii_8rd1nxorjxad(String string, String string2) {
        return "[" + string + "] scroll box '" + string2 + "' scrolls horizontally; Bedrock scroll views are vertical only, so it is shown clipped without scrolling.";
    }

    private static /* synthetic */ String Xqedii_1sjuj5e9a5mqj(String string) {
        return "content@uui." + string;
    }

    private static /* synthetic */ String Xqedii_yoz8q9j7cio5(String string) {
        return "scroll_thumb_" + string;
    }

    private static /* synthetic */ String Xqedii_2agk4vwl9w8li(String string) {
        return "scroll_track_" + string;
    }

    private static /* synthetic */ String Xqedii_2t13q3hkp0ndq(String string) {
        return "uui." + string;
    }

    private static /* synthetic */ String Xqedii_1zj2lx955uvh5(String string) {
        return "uui." + string;
    }

    private static /* synthetic */ String Xqedii_39ofzp9k51umq(String string, String string2) {
        return "[" + string + "] image '" + string2 + "' was not found in contents/images and will not render on Bedrock.";
    }

    private static /* synthetic */ String Xqedii_3vx7hyi7ljtrj(String string, String string2, boolean bl, String string3, int n, int n2) {
        return "  head '" + string + "' on page '" + string2 + "' hover=" + bl + " owner='" + string3 + "' imageSlot=" + n + "/" + n2;
    }

    private static /* synthetic */ String Xqedii_2235xqttf5qor(String string, String string2, String string3, int n) {
        return "  skin '" + string + "' on page '" + string2 + "' source='" + string3 + "' imageSlot=" + n;
    }

    private static /* synthetic */ String Xqedii_bu4thunjjnen(String string) {
        return "(" + string + " * 1)";
    }
}
