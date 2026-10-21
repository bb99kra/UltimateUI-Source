package dev.xqedii.ultimateUI;

public enum Xqedii_16ud823 {
   SHADERS(
      "shaders",
      "Shader fix",
      "ultimateui.command.shaders",
      "ultimateui.command.shaders.others"
   ),
   CURSOR(
      "cursor",
      "Client-sided cursor",
      "ultimateui.command.cursor",
      "ultimateui.command.cursor.others"
   );

   public static final String Xqedii_2citphlxxtv08 = "";
   public final String label;
   public final String displayName;
   public final String permission;
   public final String othersPermission;

   Xqedii_16ud823(String var3, String var4, String var5, String var6) {
      this.label = var3;
      this.displayName = var4;
      this.permission = var5;
      this.othersPermission = var6;
   }

   static {
      if (((-2096053937 | 1) & 1) == 0) {
         throw new IllegalStateException();
      }
   }
}
