package dev.xqedii.ultimateUI;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public final class Xqedii_2hdrgs5 {
   public static final String Xqedii_2tkr0ll = "org.geysermc.geyser.api.GeyserApi";
   public static final long Xqedii_1we2v2j = 65536L;
   public static final int Xqedii_24ij2ry = 32768;
   public static final int[] Xqedii_3ir6u84;
   public final Logger Xqedii_2oqkggo;
   public final Map<Object, Map<String, int[]>> Xqedii_s2wt2ci = Collections.synchronizedMap(new WeakHashMap<>());
   public final Set<String> Xqedii_8k6q8a4 = ConcurrentHashMap.newKeySet();
   public volatile boolean Xqedii_125l6xh;
   public volatile Object Xqedii_3hgpttf;
   public volatile Method Xqedii_99pkk11;

   public Xqedii_2hdrgs5(Logger var1) {
      this.Xqedii_2oqkggo = var1;
   }

   public boolean Xqedii_20bhnal() {
      {
         this.Xqedii_1s74gku();
         return ((this.Xqedii_3hgpttf != null && this.Xqedii_99pkk11 != null
            ? 1
            : 0) != 0);
      }
   }

   public String Xqedii_eleols7(UUID var1, String var2, boolean var3) {
      {
         String var4 = Xqedii_w0anj6v(var2);
         if (var1 != null && !var4.isEmpty()) {
            int[] var5 = this.Xqedii_19ur2ug(var1, var4);
            if (var5 != null
               && var5.length >= 2
               && var5[0] != 0) {
               long var6 = var5[0] * 65536L
                  + Math.max(0, var5[1])
                  + (var3 ? 32768 : 0);
               return Long.toString(var6);
            } else {
               return "0";
            }
         } else {
            return "0";
         }
      }
   }

   public static String Xqedii_w0anj6v(String var0) {
      {
         String var1 = var0 == null ? "" : var0.trim();
         int var2 = var1.indexOf(35);
         if (var2 >= 0) {
            var1 = var1.substring(0, var2).trim();
         }

         var1 = var1.toLowerCase(Locale.ROOT)
            .replace((char)32, (char)95);
         if (var1.isEmpty()) {
            return "";
         } else {
            int var3 = var1.indexOf(58);
            if (var3 < 0) {
               return Xqedii_1fo1vka2etvpb(var1);
            } else {
               String var4 = var1.substring(0, var3);
               String var5 = var1.substring(var3 + 1);
               int var6 = var5.indexOf(58);
               if (var6 >= 0) {
                  var5 = var5.substring(0, var6);
               }

               return var5.isEmpty() ? "" : Xqedii_3s1vxdut7ex8b(var4, var5);
            }
         }
      }
   }

   public int[] Xqedii_19ur2ug(UUID var1, String var2) {
      if (!this.Xqedii_20bhnal()) {
         return null;
      } else {
         try {
            Method var10000 = this.Xqedii_99pkk11;
            Object var10001 = this.Xqedii_3hgpttf;
            Object[] var10002 = new Object[1];
            var10002[0] = var1;
            Object var3 = var10000.invoke(var10001, var10002);
            if (var3 == null) {
               return null;
            } else {
               Object var4 = Xqedii_18jcp45(
                  var3, "getItemMappings"
               );
               if (var4 == null) {
                  return null;
               } else {
                  Map var5 = this.Xqedii_s2wt2ci.computeIfAbsent(var4, var0 -> {
                     {
                        return new ConcurrentHashMap<>();
                     }
                  });
                  int[] var6 = (int[])var5.get(var2);
                  if (var6 != null) {
                     return var6 == Xqedii_3ir6u84 ? null : var6;
                  } else {
                     int[] var7 = Xqedii_1evo4sa(var4, var2);
                     var5.put(var2, var7 == null ? Xqedii_3ir6u84 : var7);
                     if (var7 == null && this.Xqedii_8k6q8a4.add(var2) && this.Xqedii_2oqkggo != null) {
                        this.Xqedii_2oqkggo.warning(Xqedii_lapvr5wo89l2(var2));
                     }

                     return var7;
                  }
               }
            }
         } catch (Throwable var8) {
            if (this.Xqedii_8k6q8a4.add(Xqedii_1kwy2qz8lhzla(var8.getClass().getName())) && this.Xqedii_2oqkggo != null) {
               this.Xqedii_2oqkggo.warning(Xqedii_1povruc7kofdf(String.valueOf(var8)));
            }

            return null;
         }
      }
   }

   public static int[] Xqedii_1evo4sa(Object var0, String var1) throws ReflectiveOperationException {
      {
         String var10001 = "getMapping";
         Class[] var10002 = new Class[1];
         var10002[0] = String.class;
         Object[] var10003 = new Object[1];
         var10003[0] = var1;
         Object var2 = Xqedii_3elxgji(var0, var10001, var10002, var10003);
         if (var2 == null) {
            return null;
         } else {
            Object var3 = Xqedii_18jcp45(
               var2, "getBedrockDefinition"
            );
            Object var4 = var3 == null
               ? null
               : Xqedii_18jcp45(var3, "getRuntimeId");
            Object var5 = Xqedii_18jcp45(
               var2, "getBedrockData"
            );
            if (var4 instanceof Number var6 && var6.intValue() != 0) {
               int[] var10000 = new int[2];
               var10000[0] = var6.intValue();
               var10000[1] = var5 instanceof Number var7
                  ? var7.intValue()
                  : 0;
               return var10000;
            } else {
               return null;
            }
         }
      }
   }

   public static Object Xqedii_18jcp45(Object var0, String var1) throws ReflectiveOperationException {
      {
         return Xqedii_3elxgji(var0, var1, new Class[0]);
      }
   }

   public static Object Xqedii_3elxgji(Object var0, String var1, Class<?>[] var2, Object... var3) throws ReflectiveOperationException {
      {
         Method var4 = var0.getClass().getMethod(var1, var2);

         try {
            var4.setAccessible(true);
         } catch (RuntimeException var6) {
         }

         return var4.invoke(var0, var3);
      }
   }

   public synchronized void Xqedii_1s74gku() {
      if (!this.Xqedii_125l6xh) {
         try {
            Class var1 = Class.forName(
               "org.geysermc.geyser.api.GeyserApi"
            );
            Object var2 = var1.getMethod("api").invoke(null);
            if (var2 == null) {
               return;
            }

            String var10002 = "connectionByUuid";
            Class[] var10003 = new Class[1];
            var10003[0] = UUID.class;
            this.Xqedii_99pkk11 = var1.getMethod(var10002, var10003);
            this.Xqedii_3hgpttf = var2;
            this.Xqedii_125l6xh = true;
         } catch (ClassNotFoundException var3) {
            this.Xqedii_125l6xh = true;
         } catch (Throwable var4) {
            this.Xqedii_3hgpttf = null;
            this.Xqedii_99pkk11 = null;
         }
      }
   }

   static {
      {
         Xqedii_3ir6u84 = new int[0];
      }
   }

   private static /* synthetic */ String Xqedii_1fo1vka2etvpb(String string) {
        return "minecraft:" + string;
    }

    private static /* synthetic */ String Xqedii_3s1vxdut7ex8b(String string, String string2) {
        return string + ":" + string2;
    }

    private static /* synthetic */ String Xqedii_lapvr5wo89l2(String string) {
        return "[UltimateUI/Bedrock] Geyser has no Bedrock item for '" + string + "' - it will not be drawn for Bedrock players.";
    }

    private static /* synthetic */ String Xqedii_1kwy2qz8lhzla(String string) {
        return "error:" + string;
    }

    private static /* synthetic */ String Xqedii_1povruc7kofdf(String string) {
        return "[UltimateUI/Bedrock] Could not read Bedrock item ids from Geyser: " + string;
    }
}
