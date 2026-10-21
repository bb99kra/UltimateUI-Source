package dev.xqedii.ultimateUI;

import java.lang.reflect.Method;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class Xqedii_1ig2hdv {
   public static final String Xqedii_koct9dw = "uui/empty";
   public static final int Xqedii_1oqccw2 = 0;
   public static final int Xqedii_1bkr2bl = 2;
   public static final int Xqedii_3l7ran4 = 7000;
   public static boolean Xqedii_2j50ap1;
   public static Method Xqedii_2r08ihn;
   public static Method Xqedii_bpnqp6l;
   public static Object Xqedii_15jxoj8;
   public static Method Xqedii_6w46yaf;
   public static Method Xqedii_3t8vlke;
   public static Method Xqedii_4xgtm92;
   public static Class<?> Xqedii_3ououc0;
   public static Method Xqedii_1rafb7l;
   public static Method Xqedii_1r0dpc8;
   public static Method Xqedii_29pz87f;
   public static Method Xqedii_2cs39d2;

   public static synchronized void Xqedii_oin15qb() {
      if (!Xqedii_2j50ap1) {
         Xqedii_2j50ap1 = true;

         try {
            Class var0 = Class.forName(
               "org.bukkit.inventory.InventoryView"
            );
            String var10001 = "setItem";
            Class[] var10002 = new Class[2];
            var10002[0] = int.class;
            var10002[1] = ItemStack.class;
            Xqedii_2r08ihn = var0.getMethod(var10001, var10002);
            Xqedii_bpnqp6l = var0.getMethod("getType");
         } catch (Throwable var15) {
         }

         try {
            Class var17 = Class.forName(
               "org.bukkit.inventory.MenuType"
            );
            Xqedii_15jxoj8 = var17.getField("ANVIL").get(null);
            Class[] var10000 = new Class[2];
            var10000[0] = Class.forName(
               "org.bukkit.inventory.MenuType$Typed"
            );
            var10000[1] = var17;
            Class[] var1 = var10000;
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; var3++) {
               Class var4 = var1[var3];
               Method[] var5 = var4.getMethods();
               int var6 = var5.length;

               for (int var7 = 0; var7 < var6; var7++) {
                  Method var8 = var5[var7];
                  if ("create".equals(var8.getName())
                     && var8.getParameterCount() == 2) {
                     Class[] var9 = var8.getParameterTypes();
                     if (var9[0].isAssignableFrom(Player.class)) {
                        if (var9[1] == String.class && Xqedii_3t8vlke == null) {
                           Xqedii_3t8vlke = var8;
                        } else if (var9[1].isAssignableFrom(Component.class) && Xqedii_6w46yaf == null) {
                           Xqedii_6w46yaf = var8;
                        }
                     }
                  }
               }
            }
         } catch (Throwable var16) {
         }

         try {
            String var18 = "openAnvil";
            Class[] var21 = new Class[2];
            var21[0] = Location.class;
            var21[1] = boolean.class;
            Xqedii_4xgtm92 = HumanEntity.class.getMethod(var18, var21);
         } catch (Throwable var14) {
         }

         try {
            Xqedii_3ououc0 = Class.forName(
               "org.bukkit.inventory.view.AnvilView"
            );
            Xqedii_1rafb7l = Xqedii_3ououc0.getMethod(
               "getRenameText"
            );
         } catch (Throwable var13) {
         }

         try {
            Xqedii_1r0dpc8 = Class.forName(
                  "org.bukkit.inventory.AnvilInventory"
               )
               .getMethod("getRenameText");
         } catch (Throwable var12) {
         }

         try {
            String var19 = "setHideTooltip";
            Class[] var22 = new Class[1];
            var22[0] = boolean.class;
            Xqedii_29pz87f = ItemMeta.class.getMethod(var19, var22);
         } catch (Throwable var11) {
         }

         try {
            String var20 = "setItemModel";
            Class[] var23 = new Class[1];
            var23[0] = NamespacedKey.class;
            Xqedii_2cs39d2 = ItemMeta.class.getMethod(var20, var23);
         } catch (Throwable var10) {
         }
      }
   }

   public static boolean Xqedii_2y78c8j() {
      {
         Xqedii_oin15qb();
         return ((Xqedii_2r08ihn == null || Xqedii_6w46yaf == null && Xqedii_3t8vlke == null && Xqedii_4xgtm92 == null
            ? 0
            : 1) != 0);
      }
   }

   public static boolean Xqedii_3uhcrgn(Player var0, String var1, ItemStack var2) {
      {
         Xqedii_oin15qb();
         Object var3 = Xqedii_yb918vj(var0, var1 == null ? "" : var1);
         if (var3 != null) {
            try {
               if (var2 != null && Xqedii_2r08ihn != null) {
                  Method var10000 = Xqedii_2r08ihn;
                  Object[] var10002 = new Object[2];
                  var10002[0] = 0;
                  var10002[1] = var2;
                  var10000.invoke(var3, var10002);
               }

               var0.openInventory((InventoryView)var3);
               if (Xqedii_19p728w(var0)) {
                  return true;
               }
            } catch (Throwable var5) {
            }
         }

         if (Xqedii_4xgtm92 != null) {
            try {
               Method var7 = Xqedii_4xgtm92;
               Object[] var8 = new Object[2];
               var8[0] = null;
               var8[1] = Boolean.valueOf(true);
               var7.invoke(var0, var8);
               return Xqedii_19p728w(var0);
            } catch (Throwable var6) {
            }
         }

         return false;
      }
   }

   public static Object Xqedii_yb918vj(Player var0, String var1) {
      if (Xqedii_15jxoj8 == null) {
         return null;
      } else {
         if (Xqedii_6w46yaf != null) {
            try {
               Method var5 = Xqedii_6w46yaf;
               Object var6 = Xqedii_15jxoj8;
               Object[] var7 = new Object[2];
               var7[0] = var0;
               var7[1] = Component.text(var1);
               return var5.invoke(var6, var7);
            } catch (Throwable var4) {
            }
         }

         if (Xqedii_3t8vlke != null) {
            try {
               Method var10000 = Xqedii_3t8vlke;
               Object var10001 = Xqedii_15jxoj8;
               Object[] var10002 = new Object[2];
               var10002[0] = var0;
               var10002[1] = var1;
               return var10000.invoke(var10001, var10002);
            } catch (Throwable var3) {
            }
         }

         return null;
      }
   }

   public static boolean Xqedii_19p728w(Player var0) {
      {
         Xqedii_oin15qb();
         Object var1 = Xqedii_1xvmtoe(var0);
         if (var1 != null && Xqedii_bpnqp6l != null) {
            try {
               return ((Xqedii_bpnqp6l.invoke(var1) == InventoryType.ANVIL
                  ? 1
                  : 0) != 0);
            } catch (Throwable var3) {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   public static boolean Xqedii_3n9en78(Player var0, ItemStack var1) {
      {
         Xqedii_oin15qb();
         Object var2 = Xqedii_1xvmtoe(var0);
         if (var2 != null && Xqedii_2r08ihn != null) {
            try {
               Method var10000 = Xqedii_2r08ihn;
               Object[] var10002 = new Object[2];
               var10002[0] = 0;
               var10002[1] = var1;
               var10000.invoke(var2, var10002);
               return true;
            } catch (Throwable var4) {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   public static void Xqedii_ycmvisi(Player var0) {
      {
         Xqedii_oin15qb();
         Object var1 = Xqedii_1xvmtoe(var0);
         if (var1 != null && Xqedii_2r08ihn != null) {
            for (int var2 = 0; var2 <= 2; var2++) {
               try {
                  Method var10000 = Xqedii_2r08ihn;
                  Object[] var10002 = new Object[2];
                  var10002[0] = var2;
                  var10002[1] = null;
                  var10000.invoke(var1, var10002);
               } catch (Throwable var4) {
               }
            }

            Xqedii_2lmz55e(var0);
         }
      }
   }

   public static void Xqedii_2lmz55e(Player var0) {
      {
         try {
            var0.setItemOnCursor(null);
         } catch (Throwable var2) {
         }
      }
   }

   public static void Xqedii_mucytq3(Player var0) {
      {
         try {
            var0.setItemOnCursor(Xqedii_1hixj2v());
         } catch (Throwable var2) {
         }
      }
   }

   public static ItemStack Xqedii_1hixj2v() {
      {
         Xqedii_oin15qb();
         ItemStack var0 = new ItemStack(Material.FIRE_CHARGE);
         ItemMeta var1 = var0.getItemMeta();
         if (var1 == null) {
            return var0;
         } else {
            Xqedii_nd6rkr3.Xqedii_2mrz7tr(var1, Component.empty());
            var1.setCustomModelData(7000);
            if (Xqedii_29pz87f != null) {
               try {
                  Method var10000 = Xqedii_29pz87f;
                  Object[] var10002 = new Object[1];
                  var10002[0] = Boolean.valueOf(true);
                  var10000.invoke(var1, var10002);
               } catch (Throwable var3) {
               }
            }

            var0.setItemMeta(var1);
            return var0;
         }
      }
   }

   public static Object Xqedii_1xvmtoe(Player var0) {
      {
         try {
            return var0.getOpenInventory();
         } catch (Throwable var2) {
            return null;
         }
      }
   }

   public static String Xqedii_1pw5jey(InventoryEvent var0) {
      {
         Xqedii_oin15qb();
         if (Xqedii_1rafb7l != null) {
            try {
               InventoryView var1 = var0.getView();
               if (Xqedii_3ououc0.isInstance(var1)) {
                  Object var6 = Xqedii_1rafb7l.invoke(var1);
                  return var6 == null ? "" : var6.toString();
               }
            } catch (Throwable var3) {
            }
         }

         if (Xqedii_1r0dpc8 != null) {
            try {
               Inventory var5 = var0.getInventory();
               Object var2 = Xqedii_1r0dpc8.invoke(var5);
               return var2 == null ? "" : var2.toString();
            } catch (Throwable var4) {
            }
         }

         return null;
      }
   }

   public static ItemStack Xqedii_35vh4et(String var0) {
      {
         Xqedii_oin15qb();
         ItemStack var1 = new ItemStack(Material.PAPER);
         ItemMeta var2 = var1.getItemMeta();
         if (var2 == null) {
            return var1;
         } else {
            Xqedii_nd6rkr3.Xqedii_2mrz7tr(
               var2, Component.text(var0 == null ? "" : var0)
            );
            var2.setCustomModelData(7000);
            if (Xqedii_29pz87f != null) {
               try {
                  Method var10000 = Xqedii_29pz87f;
                  Object[] var10002 = new Object[1];
                  var10002[0] = Boolean.valueOf(true);
                  var10000.invoke(var2, var10002);
               } catch (Throwable var5) {
               }
            }

            if (Xqedii_2cs39d2 != null) {
               try {
                  Method var6 = Xqedii_2cs39d2;
                  Object[] var7 = new Object[1];
                  var7[0] = NamespacedKey.minecraft(
                     "uui/empty"
                  );
                  var6.invoke(var2, var7);
               } catch (Throwable var4) {
               }
            }

            var1.setItemMeta(var2);
            return var1;
         }
      }
   }
}
