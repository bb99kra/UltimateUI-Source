package dev.xqedii.ultimateUI;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Xqedii_1ks3mne extends Xqedii_piddhjw {

   public Xqedii_1ks3mne(File var1) {
      super(var1);
   }

   public Map<String, Object> Xqedii_3o9nf7h() {
      {
         Map var1 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var1.put(
            "namespace",
            "server_form"
         );
         Map var2 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var2.put(
            "$screen_content",
            "server_form.uui_screen_content"
         );
         String var10001 = "button_mappings";
         Object[] var10002 = new Object[1];
         var10002[0] = Xqedii_1qdin93(
            "button.menu_cancel",
            "button.menu_exit",
            "global"
         );
         var2.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var1.put(
            "third_party_server_screen@common.base_screen",
            var2
         );
         Map var3 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var3.put(
            "long_form",
            "@server_form.uui_long_form"
         );
         var3.put(
            "custom_form",
            "@server_form.uui_custom_form"
         );
         Map var4 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var4.put(
            "type",
            "factory"
         );
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var4.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var4.put("control_ids", var3);
         Map var5 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var5.put(
            "type",
            "panel"
         );
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "controls";
         var10002 = new Object[1];
         var10002[0] = Xqedii_2dr0g9h(
            "server_form_factory", var4
         );
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var1.put("uui_screen_content", var5);
         var1.put(
            "uui_long_form",
            this.Xqedii_drl108g(
               "vanilla_long_form@server_form.long_form",
               "\u00a7u\u00a7u\u00a7i",
               "uui_pages@uui.long_root"
            )
         );
         var1.put(
            "uui_custom_form",
            this.Xqedii_drl108g(
               "vanilla_custom_form@server_form.custom_form",
               "\u00a7u\u00a7u\u00a7j",
               "uui_inputs@uui.input_root"
            )
         );
         return var1;
      }
   }

   public Map<String, Object> Xqedii_drl108g(String var1, String var2, String var3) {
      {
         Map var4 = Xqedii_29ge1r6.Xqedii_15tec8i();
         String var10001 = "bindings";
         Object[] var10002 = new Object[2];
         var10002[0] = Xqedii_3sbajo5();
         var10002[1] = Xqedii_2jx1t4g(var2, false);
         var4.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         Map var5 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var5.put(
            "type",
            "panel"
         );
         var10001 = "size";
         var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "controls";
         var10002 = new Object[2];
         var10002[0] = Xqedii_2dr0g9h(var1, var4);
         var10002[1] = Xqedii_2dr0g9h(var3, Xqedii_29ge1r6.Xqedii_15tec8i());
         var5.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         return var5;
      }
   }

   public Map<String, Object> Xqedii_1kzuvnb(List<Object> var1) {
      {
         Map var2 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var2.put(
            "type",
            "panel"
         );
         String var10001 = "size";
         Object[] var10002 = new Object[2];
         var10002[0] = "100%";
         var10002[1] = "100%";
         var2.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var2.put(
            "anchor_from",
            "top_left"
         );
         var2.put(
            "anchor_to",
            "top_left"
         );
         var10001 = "offset";
         var10002 = new Object[2];
         var10002[0] = 0;
         var10002[1] = 0;
         var2.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var2.put(
            "layer", 5
         );
         var2.put("controls", var1);
         return var2;
      }
   }

   public Map<String, Object> Xqedii_3k9g5n8() {
      {
         Map var1 = Xqedii_29ge1r6.Xqedii_15tec8i();
         String var10001 = "ui_defs";
         Object[] var10002 = new Object[1];
         var10002[0] = "ui/uui/uui_screens.json";
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         return var1;
      }
   }

   public Map<String, Object> Xqedii_3n33qm0() {
      {
         Map var1 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var1.put(
            "format_version",
            2
         );
         Map var2 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var2.put("name", this.Xqedii_291jtfy);
         var2.put(
            "description",
            "UltimateUI screens compiled for Bedrock Edition"
         );
         var2.put(
            "uuid",
            Xqedii_qd4r4ef(Xqedii_7cu26z5bq78z(this.Xqedii_291jtfy)).toString()
         );
         String var10001 = "version";
         Object[] var10002 = new Object[3];
         var10002[0] = this.Xqedii_3hhz4ql[0];
         var10002[1] = this.Xqedii_3hhz4ql[1];
         var10002[2] = this.Xqedii_3hhz4ql[2];
         var2.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "min_engine_version";
         var10002 = new Object[3];
         var10002[0] = this.Xqedii_3tmaf8b[0];
         var10002[1] = this.Xqedii_3tmaf8b[1];
         var10002[2] = this.Xqedii_3tmaf8b[2];
         var2.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var1.put("header", var2);
         Map var3 = Xqedii_29ge1r6.Xqedii_15tec8i();
         var3.put(
            "type",
            "resources"
         );
         var3.put(
            "uuid",
            Xqedii_qd4r4ef(Xqedii_lik92qctgvyz(this.Xqedii_291jtfy)).toString()
         );
         var10001 = "version";
         var10002 = new Object[3];
         var10002[0] = this.Xqedii_3hhz4ql[0];
         var10002[1] = this.Xqedii_3hhz4ql[1];
         var10002[2] = this.Xqedii_3hhz4ql[2];
         var3.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         var10001 = "modules";
         var10002 = new Object[1];
         var10002[0] = var3;
         var1.put(var10001, Xqedii_29ge1r6.Xqedii_2fhwu22(var10002));
         return var1;
      }
   }

   public static UUID Xqedii_qd4r4ef(String var0) {
      {
         return UUID.nameUUIDFromBytes(Xqedii_1y11fpy3s50ez(var0).getBytes(StandardCharsets.UTF_8));
      }
   }

   private static /* synthetic */ String Xqedii_7cu26z5bq78z(String string) {
        return "header:" + string;
    }

    private static /* synthetic */ String Xqedii_lik92qctgvyz(String string) {
        return "module:" + string;
    }

    private static /* synthetic */ String Xqedii_1y11fpy3s50ez(String string) {
        return "dev.xqedii.ultimateui.bedrock:" + string;
    }
}
