package dev.xqedii.ultimateUI;

import java.lang.reflect.Method;
import java.security.CodeSource;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class Xqedii_2pn9w2s {
   public static final String Xqedii_2ioo3ih = "org.geysermc.floodgate.api.FloodgateApi";
   public static final String Xqedii_17jm5qa = "org.geysermc.cumulus.form.SimpleForm";
   public static final String Xqedii_3gy5tfy = "org.geysermc.cumulus.form.SimpleForm$Builder";
   public static final String Xqedii_ln7ikf6 = "org.geysermc.cumulus.response.SimpleFormResponse";
   public static final String Xqedii_2h9hj0v = "org.geysermc.cumulus.form.Form";
   public static final String Xqedii_rnjhtfj = "org.geysermc.cumulus.util.FormImage";
   public static final String Xqedii_20mg3uy = "org.geysermc.cumulus.form.CustomForm";
   public static final String Xqedii_2c5er54 = "org.geysermc.cumulus.form.CustomForm$Builder";
   public static final String Xqedii_3d0a8ko = "org.geysermc.cumulus.response.CustomFormResponse";
   public final Logger Xqedii_1jbts14;
   public volatile boolean Xqedii_2ei87u8;
   public boolean Xqedii_3qdt53q;
   public boolean Xqedii_2w3xedy;
   public Object Xqedii_2biloq5;
   public Method Xqedii_3f2n87x;
   public Method Xqedii_3ellkhm;
   public Method Xqedii_3oezx8m;
   public Method Xqedii_3glhone;
   public Method Xqedii_2nn5way;
   public Method Xqedii_3bwmkfu;
   public Method Xqedii_3mtjsgt;
   public Method Xqedii_3j59mfk;
   public Method Xqedii_25jxqzp;
   public Method Xqedii_3nz1o0z;
   public Method Xqedii_19rhjiy;
   public Method Xqedii_2i6wjwl;
   public Method Xqedii_1gv8yrz;
   public Method Xqedii_1wbnw6r;
   public Method Xqedii_285gapc;
   public Method Xqedii_2dg7hrv;
   public Method Xqedii_3ulam69;
   public Method Xqedii_3emeqso;
   public Method Xqedii_1ey3nbc;
   public Method Xqedii_1y8ea71;
   public Method Xqedii_opvmtpt;
   public Method Xqedii_3tqm03g;
   public Method Xqedii_3vn6pof;

   public Xqedii_2pn9w2s(Logger var1) {
      this.Xqedii_1jbts14 = var1;
   }

   public void Xqedii_211piqv(boolean var1) {
      {
         this.Xqedii_2ei87u8 = var1;
      }
   }

   public void Xqedii_2mbjsgo(String var1) {
      {
         if (this.Xqedii_2ei87u8 && this.Xqedii_1jbts14 != null) {
            this.Xqedii_1jbts14.info(Xqedii_3tbylxi4u9vry(var1));
         }
      }
   }

   public static String Xqedii_lrxq2tw(Class<?> var0) {
      if (var0 == null) {
         return "<absent>";
      } else {
         try {
            CodeSource var1 = var0.getProtectionDomain().getCodeSource();
            return var1 != null && var1.getLocation() != null
               ? var1.getLocation().toString()
               : "<jvm>";
         } catch (Throwable var2) {
            return "<unknown>";
         }
      }
   }

   public synchronized void Xqedii_2n1xvs8() {
      if (!this.Xqedii_3qdt53q) {
         this.Xqedii_3qdt53q = true;
         Class var1 = this.Xqedii_2a9hgu7(
            "org.geysermc.floodgate.api.FloodgateApi"
         );
         if (var1 != null) {
            try {
               Method var2 = var1.getMethod("getInstance");
               this.Xqedii_2biloq5 = var2.invoke(null);
               if (this.Xqedii_2biloq5 == null) {
                  return;
               }

               String var10002 = "isFloodgatePlayer";
               Class[] var10003 = new Class[1];
               var10003[0] = UUID.class;
               this.Xqedii_3f2n87x = var1.getMethod(var10002, var10003);
            } catch (Throwable var11) {
               this.Xqedii_1z8fzhi(Xqedii_14b3cqn9nra6d(String.valueOf(var11)));
               this.Xqedii_2biloq5 = null;
               return;
            }

            Class var12 = this.Xqedii_2a9hgu7(
               "org.geysermc.cumulus.form.Form"
            );
            Class var3 = this.Xqedii_2a9hgu7(
               "org.geysermc.cumulus.form.SimpleForm"
            );
            Class var4 = this.Xqedii_2a9hgu7(
               "org.geysermc.cumulus.form.SimpleForm$Builder"
            );
            Class var5 = this.Xqedii_2a9hgu7(
               "org.geysermc.cumulus.response.SimpleFormResponse"
            );
            if (var12 != null && var3 != null && var4 != null && var5 != null) {
               try {
                  String var13 = "sendForm";
                  Class[] var22 = new Class[2];
                  var22[0] = UUID.class;
                  var22[1] = var12;
                  this.Xqedii_3ellkhm = var1.getMethod(var13, var22);

                  try {
                     var13 = "closeForm";
                     var22 = new Class[1];
                     var22[0] = UUID.class;
                     this.Xqedii_3oezx8m = var1.getMethod(var13, var22);
                  } catch (NoSuchMethodException var9) {
                     this.Xqedii_3oezx8m = null;
                  }

                  this.Xqedii_3glhone = var3.getMethod("builder");
                  var13 = "title";
                  var22 = new Class[1];
                  var22[0] = String.class;
                  this.Xqedii_2nn5way = var4.getMethod(var13, var22);
                  var13 = "content";
                  var22 = new Class[1];
                  var22[0] = String.class;
                  this.Xqedii_3bwmkfu = var4.getMethod(var13, var22);
                  var13 = "button";
                  var22 = new Class[1];
                  var22[0] = String.class;
                  this.Xqedii_3mtjsgt = var4.getMethod(var13, var22);
                  Class var6 = this.Xqedii_2a9hgu7(
                     "org.geysermc.cumulus.util.FormImage"
                  );
                  this.Xqedii_2mbjsgo(Xqedii_3mp7pxh8thsnc(Xqedii_lrxq2tw(var6)));
                  if (var6 == null) {
                     this.Xqedii_1z8fzhi(
                        "Cumulus FormImage class not found - player heads will not render on Bedrock."
                     );
                  } else {
                     try {
                        var13 = "of";
                        var22 = new Class[2];
                        var22[0] = String.class;
                        var22[1] = String.class;
                        this.Xqedii_25jxqzp = var6.getMethod(var13, var22);
                        var13 = "button";
                        var22 = new Class[2];
                        var22[0] = String.class;
                        var22[1] = var6;
                        this.Xqedii_3j59mfk = var4.getMethod(var13, var22);
                        this.Xqedii_2mbjsgo(Xqedii_26ddas73w1399(String.valueOf(this.Xqedii_25jxqzp), String.valueOf(this.Xqedii_3j59mfk)));
                     } catch (Throwable var8) {
                        this.Xqedii_25jxqzp = null;
                        this.Xqedii_3j59mfk = null;
                        this.Xqedii_1z8fzhi(Xqedii_2za1fcc8ex6q2(String.valueOf(var8)));
                     }
                  }

                  var13 = "validResultHandler";
                  var22 = new Class[1];
                  var22[0] = Consumer.class;
                  this.Xqedii_3nz1o0z = var4.getMethod(var13, var22);
                  var13 = "closedOrInvalidResultHandler";
                  var22 = new Class[1];
                  var22[0] = Runnable.class;
                  this.Xqedii_19rhjiy = var4.getMethod(var13, var22);
                  this.Xqedii_2i6wjwl = var4.getMethod("build");
                  this.Xqedii_1gv8yrz = var5.getMethod(
                     "clickedButtonId"
                  );
                  this.Xqedii_z97q3am();
                  this.Xqedii_2w3xedy = true;
                  this.Xqedii_2mbjsgo(
                     Xqedii_2qz2jux03ak8p(
                        Xqedii_lrxq2tw(var3),
                        Xqedii_lrxq2tw(var1),
                        ((this.Xqedii_3oezx8m != null
                           ? 1
                           : 0) != 0),
                        ((this.Xqedii_3j59mfk != null
                           ? 1
                           : 0) != 0)
                     )
                  );
               } catch (Throwable var10) {
                  this.Xqedii_1z8fzhi(Xqedii_18naulg1li3ep(String.valueOf(var10)));
                  this.Xqedii_2w3xedy = false;
               }
            } else {
               this.Xqedii_1z8fzhi(
                  "Floodgate was found but Cumulus form classes are missing - Bedrock UIs are disabled."
               );
            }
         }
      }
   }

   public void Xqedii_z97q3am() {
      {
         Class var1 = this.Xqedii_2a9hgu7(
            "org.geysermc.cumulus.form.CustomForm"
         );
         Class var2 = this.Xqedii_2a9hgu7(
            "org.geysermc.cumulus.form.CustomForm$Builder"
         );
         Class var3 = this.Xqedii_2a9hgu7(
            "org.geysermc.cumulus.response.CustomFormResponse"
         );
         if (var1 != null && var2 != null && var3 != null) {
            try {
               this.Xqedii_1wbnw6r = var1.getMethod("builder");
               String var10002 = "title";
               Class[] var10003 = new Class[1];
               var10003[0] = String.class;
               this.Xqedii_285gapc = var2.getMethod(var10002, var10003);
               var10002 = "input";
               var10003 = new Class[3];
               var10003[0] = String.class;
               var10003[1] = String.class;
               var10003[2] = String.class;
               this.Xqedii_2dg7hrv = var2.getMethod(var10002, var10003);
               var10002 = "toggle";
               var10003 = new Class[1];
               var10003[0] = String.class;
               this.Xqedii_3ulam69 = Xqedii_1evov0x(var2, var10002, var10003);
               var10002 = "validResultHandler";
               var10003 = new Class[1];
               var10003[0] = Consumer.class;
               this.Xqedii_3emeqso = var2.getMethod(var10002, var10003);
               var10002 = "closedOrInvalidResultHandler";
               var10003 = new Class[1];
               var10003[0] = Runnable.class;
               this.Xqedii_1ey3nbc = var2.getMethod(var10002, var10003);
               var10002 = "closedResultHandler";
               var10003 = new Class[1];
               var10003[0] = Runnable.class;
               this.Xqedii_1y8ea71 = Xqedii_1evov0x(var2, var10002, var10003);
               var10002 = "invalidResultHandler";
               var10003 = new Class[1];
               var10003[0] = Consumer.class;
               this.Xqedii_opvmtpt = Xqedii_1evov0x(var2, var10002, var10003);
               this.Xqedii_3tqm03g = var2.getMethod("build");
               var10002 = "asInput";
               var10003 = new Class[1];
               var10003[0] = int.class;
               this.Xqedii_3vn6pof = var3.getMethod(var10002, var10003);
            } catch (Throwable var5) {
               this.Xqedii_1wbnw6r = null;
               this.Xqedii_1z8fzhi(Xqedii_1qpz7r80m6yuf(String.valueOf(var5)));
            }
         } else {
            this.Xqedii_1z8fzhi(
               "Cumulus custom forms are missing - input boxes cannot be typed into on Bedrock."
            );
         }
      }
   }

   public static Method Xqedii_1evov0x(Class<?> var0, String var1, Class<?>... var2) {
      {
         try {
            return var0.getMethod(var1, var2);
         } catch (NoSuchMethodException var4) {
            return null;
         }
      }
   }

   public boolean Xqedii_2m5qhgo() {
      {
         this.Xqedii_2n1xvs8();
         return ((this.Xqedii_1148524() && this.Xqedii_1wbnw6r != null
            ? 1
            : 0) != 0);
      }
   }

   public boolean Xqedii_2s5vyvr() {
      {
         return ((this.Xqedii_2m5qhgo() && this.Xqedii_3ulam69 != null
            ? 1
            : 0) != 0);
      }
   }

   public boolean Xqedii_1kz6g4o(UUID var1, String var2, String var3, String var4, String var5, Consumer<String> var6, Runnable var7) {
      {
         return this.Xqedii_34o28ib(var1, var2, var3, var4, var5, List.of(), var6, var7);
      }
   }

   public boolean Xqedii_34o28ib(UUID var1, String var2, String var3, String var4, String var5, List<String> var6, Consumer<String> var7, Runnable var8) {
      if (var1 == null || !this.Xqedii_2m5qhgo()) {
         return false;
      } else if (var6 != null && !var6.isEmpty() && this.Xqedii_3ulam69 == null) {
         return false;
      } else {
         try {
            Object var9 = this.Xqedii_1wbnw6r.invoke(null);
            Method var10000 = this.Xqedii_285gapc;
            Object[] var10002 = new Object[1];
            var10002[0] = var2 == null
               ? ""
               : var2;
            var9 = var10000.invoke(var9, var10002);
            var10000 = this.Xqedii_2dg7hrv;
            var10002 = new Object[3];
            var10002[0] = var3 == null
               ? ""
               : var3;
            var10002[1] = var4 == null
               ? ""
               : var4;
            var10002[2] = var5 == null
               ? ""
               : var5;
            var9 = var10000.invoke(var9, var10002);
            if (var6 != null) {
               for (String var11 : var6) {
                  var10000 = this.Xqedii_3ulam69;
                  var10002 = new Object[1];
                  var10002[0] = var11 == null
                     ? ""
                     : var11;
                  var9 = var10000.invoke(var9, var10002);
               }
            }

            Consumer var22 = var2x -> {
               {
                  String var3x = "";

                  try {
                     Method var10000x = this.Xqedii_3vn6pof;
                     Object[] var10002x = new Object[1];
                     var10002x[0] = 0;
                     Object var4x = var10000x.invoke(var2x, var10002x);
                     var3x = var4x == null ? "" : var4x.toString();
                  } catch (Throwable var5x) {
                     this.Xqedii_1z8fzhi(Xqedii_3la8wu5w1fwjf(String.valueOf(var5x)));
                  }

                  if (var7 != null) {
                     var7.accept(var3x);
                  }
               }
            };
            Runnable var23 = var8 == null ? () -> {
               if ((-475327753 * -475327753 - -475327753 & 1) != 0) {
                  throw new IllegalStateException();
               }
            } : var8;
            var10000 = this.Xqedii_3emeqso;
            var10002 = new Object[1];
            var10002[0] = var22;
            var9 = var10000.invoke(var9, var10002);
            if (this.Xqedii_1y8ea71 != null && this.Xqedii_opvmtpt != null) {
               Consumer var12 = var2x -> {
                  {
                     this.Xqedii_1z8fzhi(Xqedii_3l2u50ohtn30j(Xqedii_21nwrxz(var2x)));
                     var23.run();
                  }
               };
               var10000 = this.Xqedii_1y8ea71;
               var10002 = new Object[1];
               var10002[0] = var23;
               var9 = var10000.invoke(var9, var10002);
               var10000 = this.Xqedii_opvmtpt;
               var10002 = new Object[1];
               var10002[0] = var12;
               var9 = var10000.invoke(var9, var10002);
            } else {
               var10000 = this.Xqedii_1ey3nbc;
               var10002 = new Object[1];
               var10002[0] = var23;
               var9 = var10000.invoke(var9, var10002);
            }

            Object var24 = this.Xqedii_3tqm03g.invoke(var9);
            var10000 = this.Xqedii_3ellkhm;
            Object var10001 = this.Xqedii_2biloq5;
            var10002 = new Object[2];
            var10002[0] = var1;
            var10002[1] = var24;
            int var14 = var10000.invoke(var10001, var10002) instanceof Boolean var15 && !var15
               ? 0
               : 1;
            this.Xqedii_2mbjsgo(Xqedii_axjstvts6ejn((var14 != 0), var2));
            return (var14 != 0);
         } catch (Throwable var16) {
            this.Xqedii_1z8fzhi(Xqedii_3oqhz3ow30hez(String.valueOf(var16)));
            return false;
         }
      }
   }

   public static String Xqedii_21nwrxz(Object var0) {
      if (var0 == null) {
         return "no details";
      } else {
         try {
            Object var1 = var0.getClass()
               .getMethod("errorMessage")
               .invoke(var0);
            Object var2 = var0.getClass()
               .getMethod("componentIndex")
               .invoke(var0);
            return Xqedii_vl1p8e285h2w(String.valueOf(var1), String.valueOf(var2));
         } catch (Throwable var3) {
            return String.valueOf(var0);
         }
      }
   }

   public Class<?> Xqedii_2a9hgu7(String var1) {
      {
         try {
            return Class.forName(var1);
         } catch (Throwable var3) {
            return null;
         }
      }
   }

   public void Xqedii_1z8fzhi(String var1) {
      {
         if (this.Xqedii_1jbts14 != null) {
            this.Xqedii_1jbts14.warning(Xqedii_24185i95ra34t(var1));
         }
      }
   }

   public boolean Xqedii_1148524() {
      {
         this.Xqedii_2n1xvs8();
         return ((this.Xqedii_2biloq5 != null && this.Xqedii_2w3xedy
            ? 1
            : 0) != 0);
      }
   }

   public boolean Xqedii_2h9n0cx() {
      {
         this.Xqedii_2n1xvs8();
         return ((this.Xqedii_1148524() && this.Xqedii_3j59mfk != null && this.Xqedii_25jxqzp != null
            ? 1
            : 0) != 0);
      }
   }

   public boolean Xqedii_29ttl5r() {
      {
         this.Xqedii_2n1xvs8();
         return ((this.Xqedii_2biloq5 != null ? 1 : 0) != 0);
      }
   }

   public boolean Xqedii_1z3dg26() {
      {
         return ((Bukkit.getPluginManager()
                     .getPlugin("Geyser-Spigot")
                  == null
               && this.Xqedii_2a9hgu7(
                     "org.geysermc.geyser.api.GeyserApi"
                  )
                  == null
            ? 0
            : 1) != 0);
      }
   }

   public boolean Xqedii_30xe33f(Player var1) {
      {
         return var1 != null && this.Xqedii_1iudxbd(var1.getUniqueId());
      }
   }

   public boolean Xqedii_1iudxbd(UUID var1) {
      if (var1 == null) {
         return false;
      } else {
         this.Xqedii_2n1xvs8();
         if (this.Xqedii_2biloq5 != null && this.Xqedii_3f2n87x != null) {
            try {
               Method var10000 = this.Xqedii_3f2n87x;
               Object var10001 = this.Xqedii_2biloq5;
               Object[] var10002 = new Object[1];
               var10002[0] = var1;
               if (var10000.invoke(var10001, var10002) instanceof Boolean var3) {
                  return var3;
               }
            } catch (Throwable var4) {
            }
         }

         return Xqedii_2q861q9(var1);
      }
   }

   public static boolean Xqedii_2q861q9(UUID var0) {
      {
         return ((var0 != null
               && var0.getMostSignificantBits() == 0L
               && var0.getLeastSignificantBits() > 0L
            ? 1
            : 0) != 0);
      }
   }

   public boolean Xqedii_5mpbqew(UUID var1, String var2, String var3, List<UiImageLabel> var4, IntConsumer var5, Runnable var6) {
      if (var1 != null && this.Xqedii_1148524()) {
         try {
            Object var7 = this.Xqedii_3glhone.invoke(null);
            Method var10000 = this.Xqedii_2nn5way;
            Object[] var10002 = new Object[1];
            var10002[0] = var2 == null
               ? ""
               : var2;
            var7 = var10000.invoke(var7, var10002);
            var10000 = this.Xqedii_3bwmkfu;
            var10002 = new Object[1];
            var10002[0] = var3 == null
               ? ""
               : var3;
            var7 = var10000.invoke(var7, var10002);
            this.Xqedii_2mbjsgo(
               Xqedii_3namk8eo42sb2(
                  String.valueOf(var1),
                  var2 == null
                     ? ""
                     : var2.replace(
                        "\u00a7",
                        "\\u00a7"
                     ),
                  var4 == null ? 0 : var4.size()
               )
            );
            if (var4 != null) {
               int var8 = 0;

               for (UiImageLabel var10 : var4) {
                  String var11 = var10 != null && var10.text() != null
                     ? var10.text()
                     : "";
                  String var12 = var10 == null ? null : var10.imageUrl();
                  int var13 = var12 != null && !var12.isBlank()
                     ? 1
                     : 0;
                  if (var13 != 0 && this.Xqedii_3j59mfk != null) {
                     var10000 = this.Xqedii_25jxqzp;
                     var10002 = new Object[2];
                     var10002[0] = "url";
                     var10002[1] = var12;
                     Object var14 = var10000.invoke(null, var10002);
                     var10000 = this.Xqedii_3j59mfk;
                     var10002 = new Object[2];
                     var10002[0] = var11;
                     var10002[1] = var14;
                     var7 = var10000.invoke(var7, var10002);
                     this.Xqedii_2mbjsgo(
                        Xqedii_1f0f6v7m6l67t(
                           var8,
                           var12,
                           var14 == null ? "null" : var14.getClass().getName()
                        )
                     );
                  } else {
                     if (var13 != 0) {
                        this.Xqedii_1z8fzhi(Xqedii_2lqxpmytzwu2i(var8, var12));
                     } else {
                        this.Xqedii_2mbjsgo(Xqedii_1xvaxcthyez9q(var8, var11));
                     }

                     var10000 = this.Xqedii_3mtjsgt;
                     var10002 = new Object[1];
                     var10002[0] = var11;
                     var7 = var10000.invoke(var7, var10002);
                  }

                  var8++;
               }
            }

            Consumer var20 = var2x -> {
               if (var5 != null) {
                  try {
                     if (this.Xqedii_1gv8yrz.invoke(var2x) instanceof Number var4x) {
                        var5.accept(var4x.intValue());
                     }
                  } catch (Throwable var5x) {
                     this.Xqedii_1z8fzhi(Xqedii_21ckwudw5q7s9(String.valueOf(var5x)));
                  }
               }
            };
            Runnable var21 = var6 == null ? () -> {
               if ((695298703 * 695298703 - 695298703 & 1) != 0) {
                  throw new IllegalStateException();
               }
            } : var6;
            var10000 = this.Xqedii_3nz1o0z;
            var10002 = new Object[1];
            var10002[0] = var20;
            var7 = var10000.invoke(var7, var10002);
            var10000 = this.Xqedii_19rhjiy;
            var10002 = new Object[1];
            var10002[0] = var21;
            var7 = var10000.invoke(var7, var10002);
            Object var22 = this.Xqedii_2i6wjwl.invoke(var7);
            var10000 = this.Xqedii_3ellkhm;
            Object var10001 = this.Xqedii_2biloq5;
            var10002 = new Object[2];
            var10002[0] = var1;
            var10002[1] = var22;
            Object var23 = var10000.invoke(var10001, var10002);
            int var24 = var23 instanceof Boolean var25 && !var25
               ? 0
               : 1;
            this.Xqedii_2mbjsgo(Xqedii_1rx9p9s02otsl((var24 != 0), String.valueOf(var23)));
            return (var24 != 0);
         } catch (Throwable var15) {
            this.Xqedii_1z8fzhi(Xqedii_3v8e7gdjdxz4a(String.valueOf(var15)));
            if (this.Xqedii_2ei87u8 && this.Xqedii_1jbts14 != null) {
               this.Xqedii_1jbts14
                  .log(
                     Level.WARNING,
                     "[UltimateUI/Bedrock] form send stack trace",
                     var15
                  );
            }

            return false;
         }
      } else {
         return false;
      }
   }

   public boolean Xqedii_3qme994(UUID var1) {
      if (var1 != null && this.Xqedii_1148524() && this.Xqedii_3oezx8m != null) {
         try {
            Method var10000 = this.Xqedii_3oezx8m;
            Object var10001 = this.Xqedii_2biloq5;
            Object[] var10002 = new Object[1];
            var10002[0] = var1;
            return ((var10000.invoke(var10001, var10002) instanceof Boolean var3 && !var3
               ? 0
               : 1) != 0);
         } catch (Throwable var4) {
            return false;
         }
      } else {
         return false;
      }
   }

   private static /* synthetic */ String Xqedii_3tbylxi4u9vry(String string) {
        return "[UltimateUI/Bedrock][debug] " + string;
    }

    private static /* synthetic */ String Xqedii_14b3cqn9nra6d(String string) {
        return "Floodgate is installed but its API could not be reached: " + string;
    }

    private static /* synthetic */ String Xqedii_3mp7pxh8thsnc(String string) {
        return "FormImage class org.geysermc.cumulus.util.FormImage -> " + string;
    }

    private static /* synthetic */ String Xqedii_26ddas73w1399(String string, String string2) {
        return "FormImage API resolved: " + string + " / " + string2;
    }

    private static /* synthetic */ String Xqedii_2za1fcc8ex6q2(String string) {
        return "Cumulus form-image API does not match the expected shape - player heads will not render on Bedrock: " + string;
    }

    private static /* synthetic */ String Xqedii_2qz2jux03ak8p(String string, String string2, boolean bl, boolean bl2) {
        return "Cumulus resolved from " + string + " | FloodgateApi from " + string2 + " | closeForm=" + bl + " | buttonWithImage=" + bl2;
    }

    private static /* synthetic */ String Xqedii_18naulg1li3ep(String string) {
        return "Cumulus form API does not match the expected shape - Bedrock UIs are disabled: " + string;
    }

    private static /* synthetic */ String Xqedii_1qpz7r80m6yuf(String string) {
        return "Cumulus custom form API does not match the expected shape - input boxes cannot be typed into on Bedrock: " + string;
    }

    private static /* synthetic */ String Xqedii_axjstvts6ejn(boolean bl, String string) {
        return "input form handed to Floodgate: " + bl + " title='" + string + "'";
    }

    private static /* synthetic */ String Xqedii_3oqhz3ow30hez(String string) {
        return "Failed to send Bedrock input form: " + string;
    }

    private static /* synthetic */ String Xqedii_vl1p8e285h2w(String string, String string2) {
        return string + ", component " + string2;
    }

    private static /* synthetic */ String Xqedii_24185i95ra34t(String string) {
        return "[UltimateUI/Bedrock] " + string;
    }

    private static /* synthetic */ String Xqedii_3namk8eo42sb2(String string, String string2, int n) {
        return "sendSimpleForm uuid=" + string + " title=" + string2 + " buttons=" + n;
    }

    private static /* synthetic */ String Xqedii_1f0f6v7m6l67t(int n, String string, String string2) {
        return "  button[" + n + "] image url=" + string + " (FormImage " + string2 + ")";
    }

    private static /* synthetic */ String Xqedii_2lqxpmytzwu2i(int n, String string) {
        return "Button " + n + " carries an image URL but this Cumulus build has no image API - the player head will stay blank. url=" + string;
    }

    private static /* synthetic */ String Xqedii_1xvaxcthyez9q(int n, String string) {
        return "  button[" + n + "] text='" + string + "'";
    }

    private static /* synthetic */ String Xqedii_1rx9p9s02otsl(boolean bl, String string) {
        return "form handed to Floodgate: " + bl + " (raw=" + string + ")";
    }

    private static /* synthetic */ String Xqedii_3v8e7gdjdxz4a(String string) {
        return "Failed to send Bedrock form: " + string;
    }

    private static /* synthetic */ String Xqedii_21ckwudw5q7s9(String string) {
        return "Failed to read Bedrock form response: " + string;
    }

    private static /* synthetic */ String Xqedii_3l2u50ohtn30j(String string) {
        return "Bedrock rejected the input form answer (" + string + ") - treating it as cancelled.";
    }

    private static /* synthetic */ String Xqedii_3la8wu5w1fwjf(String string) {
        return "Failed to read Bedrock input form response: " + string;
    }
}
