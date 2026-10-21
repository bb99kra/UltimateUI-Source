package dev.xqedii.ultimateUI;

import java.lang.runtime.ObjectMethods;
import java.util.List;

public record Xqedii_3vpi3ac(List<String> defines, String switchBody, boolean hideExperience) {

   public String functions(boolean var1) {
      {
         StringBuilder var2 = new StringBuilder();

         for (String var4 : this.defines) {
            var2.append(var4).append((char)10);
         }

         var2.append((char)10);
         var2.append(
            "bool uui_bh_range(float value, float low, float high) {\n"
         );
         var2.append(
            "    return value >= low && value <= high;\n"
         );
         var2.append("}\n\n");
         var2.append(
            "bool uui_betterhud() {\n"
         );
         var2.append(
            "    if (ProjMat[3].x != -1.0) {\n"
         );
         var2.append("        return false;\n");
         var2.append("    }\n");
         var2.append(
            "    vec3 pos = Position;\n"
         );
         var2.append(
            "    vec2 ui = ceil(2.0 / abs(vec2(ProjMat[0][0], ProjMat[1][1])));\n"
         );
         var2.append(
            "    vec2 uiScreen = ui / ScreenSize;\n"
         );
         var2.append(
            "    if (pos.y < ui.y) {\n"
         );
         if (this.hideExperience) {
            var2.append(
               "        vec3 uuiBhExp = vec3(128.0, 255.0, 32.0);\n"
            );
            var2.append(
               "        bool uuiBhExpColor = uui_bh_range(Color.r, uuiBhExp.r / 256.0, uuiBhExp.r / 254.0)\n"
            );
            var2.append(
               "            && uui_bh_range(Color.g, uuiBhExp.g / 256.0, uuiBhExp.g / 254.0)\n"
            );
            var2.append(
               "            && uui_bh_range(Color.b, uuiBhExp.b / 256.0, uuiBhExp.b / 254.0);\n"
            );
            var2.append(
               "        if (uui_bh_range(pos.y, ui.y - 60.0, ui.y - 20.0) && uui_bh_range(pos.x, ui.x / 2.0 - 60.0, ui.x / 2.0 + 60.0)\n"
            );
            var2.append(
               "            && (uuiBhExpColor || Color.rgb == vec3(0.0))) {\n"
            );
            var2.append(
               "            vertexColor = vec4(0.0);\n"
            );
            var2.append("        }\n");
         }

         var2.append("        return false;\n");
         var2.append("    }\n");
         var2.append(
            "    int bit = int(pos.y) >> HEIGHT_BIT;\n"
         );
         var2.append(
            "    if (((bit >> MAX_BIT) & 1) != 1) {\n"
         );
         var2.append("        return false;\n");
         var2.append("    }\n");
         var2.append(
            "    int id = bit - (1 << MAX_BIT);\n"
         );
         var2.append(
            "    pos.x -= 0.5 * ui.x;\n"
         );
         var2.append(
            "    pos.y -= float((bit << HEIGHT_BIT) + ADD_OFFSET + DEFAULT_OFFSET);\n"
         );
         var2.append("    float xGui = 0.0;\n");
         var2.append("    float yGui = 0.0;\n");
         var2.append(
            "    float layer = 0.0;\n"
         );
         var2.append(
            "    float opacity = 1.0;\n"
         );
         var2.append(
            "    bool outline = false;\n"
         );
         var2.append("    int property = 0;\n");
         var2.append("    switch (id) {\n");
         var2.append(this.switchBody).append((char)10);
         var2.append("    }\n");
         if (var1) {
            var2.append(
               "    if (!outline && (pos.z == 0.0 || pos.z == 1000.0 || pos.z == -90.0 || pos.z == 2800.0)) {\n"
            );
            var2.append(
               "        vertexColor = vec4(0.0);\n"
            );
            var2.append("    } else {\n");
            var2.append(
               "        vertexColor = vertexColor * vec4(1.0, 1.0, 1.0, opacity);\n"
            );
            var2.append("    }\n");
         } else {
            var2.append(
               "    vertexColor = vertexColor * vec4(1.0, 1.0, 1.0, opacity);\n"
            );
         }

         var2.append(
            "    if ((property & 1) > 0) {\n"
         );
         var2.append(
            "        pos.y += 4.0 * sin((GameTime * 1200.0 + pos.x / ui.x) * 3.1415 * 2.0);\n"
         );
         var2.append("    }\n");
         var2.append(
            "    if ((property & 2) > 0) {\n"
         );
         var2.append(
            "        int hash = int(pos.x) * int(pos.y);\n"
         );
         var2.append(
            "        float time = GameTime * 1200.0;\n"
         );
         var2.append(
            "        hash = 31 * (hash + int(vertexColor.x + time));\n"
         );
         var2.append(
            "        float r = float(hash % 224 + 32) / 255.0;\n"
         );
         var2.append(
            "        hash = 31 * (hash + int(vertexColor.y + time));\n"
         );
         var2.append(
            "        float g = float(hash % 224 + 32) / 255.0;\n"
         );
         var2.append(
            "        hash = 31 * (hash + int(vertexColor.z + time));\n"
         );
         var2.append(
            "        float b = float(hash % 224 + 32) / 255.0;\n"
         );
         var2.append(
            "        float maxValue = max(max(r, g), b);\n"
         );
         var2.append(
            "        vertexColor = vec4(pow(r / maxValue, 3.0), pow(g / maxValue, 3.0), pow(b / maxValue, 3.0), vertexColor.w);\n"
         );
         var2.append("    }\n");
         var2.append(
            "    if ((property & 4) > 0) {\n"
         );
         var2.append(
            "        int hash = int(pos.x) * int(pos.y);\n"
         );
         var2.append(
            "        float time = GameTime * 1200.0;\n"
         );
         var2.append(
            "        hash = 31 * (hash + int(vertexColor.x + time));\n"
         );
         var2.append(
            "        float r = vertexColor.x + float(hash % 64) / 255.0;\n"
         );
         var2.append(
            "        hash = 31 * (hash + int(vertexColor.y + time));\n"
         );
         var2.append(
            "        float g = vertexColor.y + float(hash % 64) / 255.0;\n"
         );
         var2.append(
            "        hash = 31 * (hash + int(vertexColor.z + time));\n"
         );
         var2.append(
            "        float b = vertexColor.z + float(hash % 64) / 255.0;\n"
         );
         var2.append(
            "        vertexColor = vec4(r, g, b, vertexColor.w);\n"
         );
         var2.append("    }\n");
         var2.append("    pos.x += xGui;\n");
         var2.append("    pos.y += yGui;\n");
         var2.append("    pos.z += layer;\n");
         var2.append(
            "    gl_Position = ProjMat * ModelViewMat * vec4(pos, 1.0);\n"
         );
         var2.append("    return true;\n");
         var2.append("}\n\n");
         return var2.toString();
      }
   }

   @Override

   public String switchBody() {
      {
         return this.switchBody;
      }
   }

   public boolean hideExperience() {
      {
         return this.hideExperience;
      }
   }
}
