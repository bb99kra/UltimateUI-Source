<div align="center">

# 🖥️ UltimateUI
### **Advanced Dynamic Minecraft GUI/HUD and Layout Engine**

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.20%20--%201.21.x-35A853?style=for-the-badge&logo=minecraft&logoColor=white)](https://papermc.io/)
[![Platform](https://img.shields.io/badge/Platform-Paper%20%7C%20Purpur%20%7C%20Folia%20%7C%20Leaf-4285F4?style=for-the-badge)](https://papermc.io/)
[![Dependency](https://img.shields.io/badge/Dependency-PacketEvents%202.x-orange?style=for-the-badge)](https://github.com/retrooper/packetevents)
[![License](https://img.shields.io/badge/License-Unlocked-brightgreen?style=for-the-badge)](https://github.com/bb99kra/UltimateUI-Source)

---

</div>

## 🌟 Overview

**UltimateUI** is a cutting-edge UI and HUD framework for Minecraft servers. Built on top of **PacketEvents**, it delivers packet-based interactive user interfaces, virtual display entities, camera views, in-game real-time editors, and custom sound effects with zero server-side tick lag.

---

## 🚀 Key Features

* 🎨 **Dynamic GUI & HUD Engine:** Render highly customized, pixel-perfect user interfaces and HUD overlays.
* ⚡ **PacketEvents Virtual Rendering:** Eliminates physical container lag by manipulating entity and packet metadata client-side.
* 🛠️ **In-Game Live Editor:** Create, move, layer, and configure UI components visually in-game.
* 📜 **Full Skript Support:** Native conditions, expressions, and event triggers for Skript developers.
* 📦 **Automatic ResourcePack Generation:** Compiles and serves font glyphs, layout textures, and audio assets on the fly.
* 🔄 **Leaf & Folia Multi-Threading Ready:** Compatible with regional multithreading and modern Paper forks (Leaf, Purpur, Folia 1.21+).

---

## 📋 Commands & Permissions

| Command | Usage | Description | Permission |
| :--- | :--- | :--- | :--- |
| `/uui` | `/uui` | Base command & help menu | `ultimateui.command.use` |
| `/uui open` | `/uui open <name> [hud] [autoclose] [player]` | Open a custom UI for player | `ultimateui.command.open` |
| `/uui create` | `/uui create <name>` | Create a new UI layout | `ultimateui.command.create` |
| `/uui edit` | `/uui edit <name>` | Open the live in-game UI editor | `ultimateui.command.edit` |
| `/uui delete` | `/uui delete <name>` | Delete an existing UI layout | `ultimateui.command.delete` |
| `/uui close` | `/uui close [name]` | Close an active UI | `ultimateui.command.close` |
| `/uui reload` | `/uui reload` | Reload configuration & assets | `ultimateui.command.reload` |

---

## 🔧 Requirements & Installation

1. **Server Software:** Paper, Purpur, Folia, or Leaf (1.20 - 1.21.x with Java 21).
2. **PacketEvents:** Install [`packetevents.jar`](https://github.com/retrooper/packetevents) (v2.7.0 - v2.13.0+) in your server's `plugins/` directory.
3. Place `UltimateUI-1.2.3.jar` into the `plugins/` folder and restart your server.

---

## 💻 Build from Source (Maven)

```bash
# Clone the repository
git clone https://github.com/bb99kra/UltimateUI-Source.git

# Enter repository directory
cd UltimateUI-Source

# Build with Maven
mvn clean package
```

The output JAR file will be available in `target/UltimateUI-1.2.3.jar`.
