package dev.xqedii.ultimateUI;

import dev.xqedii.ultimateUI.api.UltimateUIAPI;
import dev.xqedii.ultimateUI.api.event.UltimateUIBlockClickEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUICloseEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUICursorMoveEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIDragEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIHoverEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIInputSubmitEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIKeyEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIOpenEvent;
import dev.xqedii.ultimateUI.api.event.UltimateUIOpenedEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Xqedii_2b3rrt2 {
   public static final String Xqedii_1v33b58 = "contents/scripts";
   public static final String Xqedii_92kgeok = ".uui";
   public static final String Xqedii_30v19gf = "script-data.yml";
   public static final String Xqedii_1x147qt = "ultimateui.reload";
   public static final long Xqedii_29iwr60 = 6000L;
   public static final long Xqedii_1nngdqp = 10000L;
   public final JavaPlugin Xqedii_1bs4d2x;
   public final File Xqedii_2ie53s9;
   public final File Xqedii_73sb6rc;
   public final List<Xqedii_1kffk3z> Xqedii_g32rhpa = new ArrayList<>();
   public final Map<String, List<Xqedii_3tcqkw2>> Xqedii_1hrsxi5 = new HashMap<>();
   public final List<Xqedii_3tcqkw2> Xqedii_3c71sgp = new ArrayList<>();
   public final Map<String, Xqedii_bflxzg9> Xqedii_2u229x4 = new LinkedHashMap<>();
   public final Map<UUID, Map<String, Object>> Xqedii_18ebdik = new ConcurrentHashMap<>();
   public final Map<UUID, Map<String, Object>> data = new ConcurrentHashMap<>();
   public final Map<String, Object> Xqedii_2gg9i2g = Collections.synchronizedMap(new LinkedHashMap<>());
   public final Map<String, Long> Xqedii_2evhzeq = new ConcurrentHashMap<>();
   public final Listener Xqedii_iulk2h1 = new Listener() {
   };
   public volatile int Xqedii_aj0mxfz;
   public long Xqedii_1zrzxna;
   public boolean Xqedii_140q75q;
   public final Map<UUID, List<Xqedii_2awqdkk>> Xqedii_tr2wpfg = new ConcurrentHashMap<>();
   public boolean Xqedii_2g291o6;
   public Xqedii_1zgvf22 Xqedii_2n5ltd1 = new Xqedii_1zgvf22(
      0, 0, List.of()
   );

   public Xqedii_2b3rrt2(JavaPlugin var1) {
      this.Xqedii_1bs4d2x = var1;
      this.Xqedii_2ie53s9 = new File(
         var1.getDataFolder(), "contents/scripts"
      );
      this.Xqedii_73sb6rc = new File(
         var1.getDataFolder(), "script-data.yml"
      );
   }

   public Plugin Xqedii_3892wz7() {
      {
         return this.Xqedii_1bs4d2x;
      }
   }

   public File Xqedii_2h6quir() {
      {
         return this.Xqedii_2ie53s9;
      }
   }

   public Xqedii_1zgvf22 Xqedii_2cgj5ej() {
      {
         return this.Xqedii_2n5ltd1;
      }
   }

   public synchronized Xqedii_1zgvf22 Xqedii_7murfs0() {
      {
         this.Xqedii_dtoug18();
         this.Xqedii_aj0mxfz = this.Xqedii_aj0mxfz + 1;
         if (!this.Xqedii_2ie53s9.exists()) {
            this.Xqedii_2ie53s9.mkdirs();
            this.Xqedii_198ljgl();
         }

         if (!this.Xqedii_2g291o6) {
            this.Xqedii_2yvqp6m();
            this.Xqedii_2g291o6 = true;
         }

         ArrayList var1 = new ArrayList();
         ArrayList var2 = new ArrayList();
         this.Xqedii_147w9mi(this.Xqedii_2ie53s9, var2);
         var2.sort(null);
         int var3 = 0;

         for (File var5 : (Iterable<File>) (var2)) {

            String var6 = this.Xqedii_2ie53s9
               .toPath()
               .relativize(var5.toPath())
               .toString()
               .replace((char)92, (char)47);

            String var7;
            try {
               var7 = Files.readString(var5.toPath(), StandardCharsets.UTF_8);
            } catch (IOException var12) {
               var1.add(
                  new Xqedii_3jb0tcl(var6, new Xqedii_3vsk7zm(Xqedii_2cpve3e2scybj(var12.getMessage()), 0))
               );
               continue;
            }

            ArrayList var8 = new ArrayList();
            Xqedii_1kffk3z var9 = Xqedii_2thoio4.Xqedii_2owkzy2(var6, var7, var8);

            for (Xqedii_3vsk7zm var11 : (Iterable<Xqedii_3vsk7zm>) (var8)) {

               var1.add(new Xqedii_3jb0tcl(var6, var11));
            }

            if (var8.isEmpty()) {
               this.Xqedii_g32rhpa.add(var9);
               var3 += var9.Xqedii_39uxme9.size();

               for (Xqedii_3tcqkw2 var18 : var9.Xqedii_39uxme9) {
                  if (var18.Xqedii_27kq3c6.equals("every")) {
                     this.Xqedii_3c71sgp.add(var18);
                  } else if (var18.Xqedii_27kq3c6.equals("command")) {
                     this.Xqedii_2u229x4.computeIfAbsent(var18.Xqedii_ne1a52s, var2x -> {
                        {
                           return new Xqedii_bflxzg9(this, var2x, var18);
                        }
                     });
                  } else {
                     this.Xqedii_1hrsxi5.computeIfAbsent(var18.Xqedii_dg8qjon(), var0 -> {
                        {
                           return new ArrayList<>();
                        }
                     }).add(var18);
                  }
               }
            }
         }

         for (Xqedii_1kffk3z var15 : this.Xqedii_g32rhpa) {
            this.Xqedii_3pyerce(var15);
         }

         this.Xqedii_2v12pbw();
         this.Xqedii_2am6bju();
         this.Xqedii_1hvc3pb();
         this.Xqedii_2n5ltd1 = new Xqedii_1zgvf22(this.Xqedii_g32rhpa.size(), var3, List.copyOf(var1));

         for (Xqedii_3tcqkw2 var16 : this.Xqedii_1hrsxi5
            .getOrDefault("load", List.of())) {
            this.run(var16, null, null, null, null);
         }

         return this.Xqedii_2n5ltd1;
      }
   }

   public void Xqedii_147w9mi(File var1, List<File> var2) {
      {
         File[] var3 = var1.listFiles();
         if (var3 != null) {
            File[] var4 = var3;
            int var5 = var3.length;

            for (int var6 = 0; var6 < var5; var6++) {
               File var7 = var4[var6];
               if (!var7.getName().startsWith("-")
                  && !var7.getName().startsWith(".")) {
                  if (var7.isDirectory()) {
                     this.Xqedii_147w9mi(var7, var2);
                  } else if (var7.getName()
                     .toLowerCase(Locale.ROOT)
                     .endsWith(".uui")) {
                     var2.add(var7);
                  }
               }
            }
         }
      }
   }

   public void Xqedii_198ljgl() {
      {
         File var1 = new File(this.Xqedii_2ie53s9, "example.uui");
         String var10000 = "\n";
         CharSequence[] var10001 = new CharSequence[9];
         var10001[0] = "# UltimateUI script. Files in this folder are compiled on /uui reload pages.";
         var10001[1] = "# Rename a file to start with - to turn it off.";
         var10001[2] = "";
         var10001[3] = "on join:";
         var10001[4] = "    data.visits = (data.visits or 0) + 1";
         var10001[5] = "";
         var10001[6] = "command /visits:";
         var10001[7] = "    send \"&aYou joined &f{data.visits}&a times!\"";
         var10001[8] = "";
         String var2 = String.join(var10000, var10001);

         try {
            Files.writeString(var1.toPath(), var2, StandardCharsets.UTF_8);
         } catch (IOException var4) {
         }
      }
   }

   public void Xqedii_3pyerce(Xqedii_1kffk3z var1) {
      {
         Xqedii_1yqm9k3 var2 = new Xqedii_1yqm9k3(this, var1, null, Bukkit.getConsoleSender(), null, null);
         Xqedii_y5pr2ec var3 = new Xqedii_y5pr2ec(
            var2, new Xqedii_3nlck21[0], false
         );

         for (Entry var5 : var1.Xqedii_2buerqn.entrySet()) {
            try {
               var1.Xqedii_3i3ujw9.put((String)var5.getKey(), ((Xqedii_ev3jp8w)var5.getValue()).Xqedii_28g36j3(var3));
            } catch (Xqedii_3vsk7zm var7) {
               this.Xqedii_3kgc99t(var1, var7);
               var1.Xqedii_3i3ujw9.put((String)var5.getKey(), null);
            }
         }
      }
   }

   public synchronized void Xqedii_oe61gxm() {
      {
         this.Xqedii_dtoug18();
         this.Xqedii_1dijqtn();
      }
   }

   public void Xqedii_dtoug18() {
      {
         this.Xqedii_aj0mxfz = this.Xqedii_aj0mxfz + 1;
         HandlerList.unregisterAll(this.Xqedii_iulk2h1);
         this.Xqedii_3d4ydyl();
         this.Xqedii_g32rhpa.clear();
         this.Xqedii_1hrsxi5.clear();
         this.Xqedii_3c71sgp.clear();
         this.Xqedii_tr2wpfg.clear();
         this.Xqedii_140q75q = false;
      }
   }

   public long Xqedii_3b3clqv() {
      {
         return this.Xqedii_1zrzxna;
      }
   }

   public void Xqedii_3uyxvgz(Player var1, Xqedii_2awqdkk var2) {
      {
         while (true) {
            List<Xqedii_2awqdkk> var3 = this.Xqedii_tr2wpfg.computeIfAbsent(var1.getUniqueId(), var0 -> {
               {
                  return new ArrayList<>();
               }
            });
            synchronized (var3) {
               if (this.Xqedii_tr2wpfg.get(var1.getUniqueId()) == var3) {
                  var3.removeIf(
                     (Xqedii_2awqdkk var1x) -> {
                        {
                           return ((var1x.Xqedii_2kw8m64.equals(var2.Xqedii_2kw8m64) && var1x.Xqedii_2dwrrc7.equals(var2.Xqedii_2dwrrc7)
                              ? 1
                              : 0) != 0);
                        }
                     }
                  );
                  var3.add(var2);
                  return;
               }
            }
         }
      }
   }

   public void Xqedii_3032p5i(Player var1, String var2, String... var3) {
      {
         List<Xqedii_2awqdkk> var4 = this.Xqedii_tr2wpfg.get(var1.getUniqueId());
         if (var4 != null) {
            Set var5 = var3.length == 0 ? null : Set.of(var3);
            synchronized (var4) {
               var4.removeIf(
                  (Xqedii_2awqdkk var2x) -> {
                     {
                        return ((var2 != null && !var2x.Xqedii_2kw8m64.equals(var2) || var5 != null && !var5.contains(var2x.Xqedii_2dwrrc7)
                           ? 0
                           : 1) != 0);
                     }
                  }
               );
            }
         }
      }
   }

   public void Xqedii_2vdmdmn(int var1) {
      {
         for (Entry var3 : this.Xqedii_tr2wpfg.entrySet()) {
            Player var4 = Bukkit.getPlayer((UUID)var3.getKey());
            if (var4 != null && var4.isOnline()) {
               List var5 = (List)var3.getValue();
               long var6 = this.Xqedii_1zrzxna;
               Xqedii_38o2zes.Xqedii_1avvt9e(this.Xqedii_1bs4d2x, var4, () -> {
                  if (var1 == this.Xqedii_aj0mxfz && UltimateUIAPI.isAvailable()) {
                     if (!UltimateUIAPI.get().isGuiOpen(var4)) {
                        this.Xqedii_tr2wpfg.remove(var4.getUniqueId(), var5);
                     } else {
                        ArrayList var6x = new ArrayList();
                        synchronized (var5) {
                           Iterator var8 = var5.iterator();

                           while (var8.hasNext()) {
                              Xqedii_2awqdkk var9 = (Xqedii_2awqdkk)var8.next();
                              var6x.add(var9.Xqedii_s2v6yoo(var6));
                              if (var9.Xqedii_3oq00gt(var6)) {
                                 var8.remove();
                              }
                           }

                           if (var5.isEmpty()) {
                              this.Xqedii_tr2wpfg.remove(var4.getUniqueId(), var5);
                           }
                        }

                        if (!var6x.isEmpty()) {
                           UltimateUIAPI.get().applyElementWrites(var4, var6x);
                        }
                     }
                  }
               });
            } else {
               this.Xqedii_tr2wpfg.remove(var3.getKey());
            }
         }
      }
   }

   public boolean Xqedii_3izlism(String var1) {
      {
         return this.Xqedii_1hrsxi5.containsKey(var1);
      }
   }

   public void Xqedii_2v12pbw() {
      {
         if (this.Xqedii_3izlism("opening")) {
            this.Xqedii_euucjux(UltimateUIOpenEvent.class, this::Xqedii_19hqwsk);
         }

         if (this.Xqedii_3izlism("open")) {
            this.Xqedii_euucjux(UltimateUIOpenedEvent.class, this::Xqedii_5qh5412);
         }

         if (this.Xqedii_3izlism("close")) {
            this.Xqedii_euucjux(UltimateUICloseEvent.class, this::Xqedii_y1cxf0z);
         }

         if (this.Xqedii_3izlism("click")
            || this.Xqedii_3izlism("left click")
            || this.Xqedii_3izlism("right click")
            || this.Xqedii_3izlism("scroll")
            || this.Xqedii_3izlism("scroll up")
            || this.Xqedii_3izlism("scroll down")) {
            this.Xqedii_euucjux(UltimateUIBlockClickEvent.class, this::Xqedii_2rb9uqo);
         }

         if (this.Xqedii_3izlism("hover")
            || this.Xqedii_3izlism("unhover")) {
            this.Xqedii_euucjux(UltimateUIHoverEvent.class, this::Xqedii_3hyd70f);
         }

         if (this.Xqedii_3izlism("drag start")
            || this.Xqedii_3izlism("drag")
            || this.Xqedii_3izlism("drop")) {
            this.Xqedii_euucjux(UltimateUIDragEvent.class, this::Xqedii_2lzyz2p);
         }

         if (this.Xqedii_3izlism("cursor move")) {
            this.Xqedii_euucjux(UltimateUICursorMoveEvent.class, this::Xqedii_ot25639);
         }

         if (this.Xqedii_3izlism("key")) {
            this.Xqedii_euucjux(UltimateUIKeyEvent.class, this::Xqedii_2p98a7o);
         }

         if (this.Xqedii_3izlism("input")) {
            this.Xqedii_euucjux(UltimateUIInputSubmitEvent.class, this::Xqedii_2s56q5q);
         }

         if (this.Xqedii_3izlism("join")) {
            this.Xqedii_euucjux(PlayerJoinEvent.class, this::Xqedii_3it2h16);
         }

         this.Xqedii_euucjux(PlayerQuitEvent.class, this::Xqedii_3m8skc3);
      }
   }

   public <T extends Event> void Xqedii_euucjux(Class<T> var1, Consumer<T> var2) {
      {
         Bukkit.getPluginManager().registerEvent(var1, this.Xqedii_iulk2h1, EventPriority.NORMAL, (var2x, var3) -> {
            {
               if (var1.isInstance(var3)) {
                  var2.accept(var1.cast(var3));
               }
            }
         }, this.Xqedii_1bs4d2x, false);
      }
   }

   public Xqedii_36oytze Xqedii_2jdoztn(String var1, UltimateUIEvent var2) {
      {
         Xqedii_36oytze var3 = new Xqedii_36oytze(var1, var2);
         var3.Xqedii_3rt5h0f = var2.getPageName();
         var3.Xqedii_lc1i2g8 = var2.getBlockId();
         var3.Xqedii_vkzqb40 = var2.getPageX();
         var3.Xqedii_yhwhrno = var2.getPageY();
         var3.Xqedii_2rqqsu9 = var2.getCursorX();
         var3.Xqedii_2qxc6az = var2.getCursorY();
         return var3;
      }
   }

   public void Xqedii_19hqwsk(UltimateUIOpenEvent var1) {
      {
         Xqedii_36oytze var2 = this.Xqedii_2jdoztn("opening", var1);
         var2.Xqedii_1zx8kgx = var1.isHudMode();
         this.Xqedii_21ptbt1(
            "opening",
            var1.getPlayer(),
            var2,
            false
         );
      }
   }

   public void Xqedii_5qh5412(UltimateUIOpenedEvent var1) {
      {
         Xqedii_36oytze var2 = this.Xqedii_2jdoztn("open", var1);
         var2.Xqedii_1zx8kgx = var1.isHudMode();
         this.Xqedii_21ptbt1(
            "open",
            var1.getPlayer(),
            var2,
            false
         );
      }
   }

   public void Xqedii_y1cxf0z(UltimateUICloseEvent var1) {
      {
         this.Xqedii_21ptbt1(
            "close",
            var1.getPlayer(),
            this.Xqedii_2jdoztn("close", var1),
            false
         );
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public void Xqedii_2rb9uqo(UltimateUIBlockClickEvent var1) {
      {
         Xqedii_36oytze var2 = this.Xqedii_2jdoztn("click", var1);
         var2.Xqedii_16npco2 = var1.getClickType()
            .name()
            .toLowerCase(Locale.ROOT)
            .replace((char)95, (char)32);
         var2.Xqedii_19v5iy3 = var1.isShiftClick();
         switch (dev.xqedii.ultimateUI.Xqedii_18eapie.Xqedii_1xl6h0t[var1.getClickType().ordinal()]) {
            case 1:
               this.Xqedii_21ptbt1(
                  "click",
                  var1.getPlayer(),
                  var2,
                  true
               );
               this.Xqedii_21ptbt1(
                  "left click",
                  var1.getPlayer(),
                  var2,
                  true
               );
               break;
            case 2:
               this.Xqedii_21ptbt1(
                  "click",
                  var1.getPlayer(),
                  var2,
                  true
               );
               this.Xqedii_21ptbt1(
                  "right click",
                  var1.getPlayer(),
                  var2,
                  true
               );
               break;
            case 3:
               this.Xqedii_21ptbt1(
                  "scroll",
                  var1.getPlayer(),
                  var2,
                  true
               );
               this.Xqedii_21ptbt1(
                  "scroll up",
                  var1.getPlayer(),
                  var2,
                  true
               );
               break;
            case 4:
               this.Xqedii_21ptbt1(
                  "scroll",
                  var1.getPlayer(),
                  var2,
                  true
               );
               this.Xqedii_21ptbt1(
                  "scroll down",
                  var1.getPlayer(),
                  var2,
                  true
               );
         }
      }
   }

   public void Xqedii_3hyd70f(UltimateUIHoverEvent var1) {
      {
         Xqedii_36oytze var2 = this.Xqedii_2jdoztn(
            var1.isEnter()
               ? "hover"
               : "unhover",
            var1
         );
         var2.Xqedii_12aqqzl = Xqedii_3rjux1w(var1.getPreviousBlockId());
         var2.Xqedii_2f19s3n = Xqedii_3rjux1w(var1.getNextBlockId());
         this.Xqedii_21ptbt1(var2.Xqedii_1x9ad7s, var1.getPlayer(), var2, true);
      }
   }

   public void Xqedii_2lzyz2p(UltimateUIDragEvent var1) {
      {
         String var2 = var1.isStart()
            ? "drag start"
            : (
               var1.isEnd()
                  ? "drop"
                  : "drag"
            );
         Xqedii_36oytze var3 = this.Xqedii_2jdoztn(var2, var1);
         var3.Xqedii_17k9l69 = var1.getPhase().name().toLowerCase(Locale.ROOT);
         var3.Xqedii_pxsnga0 = var1.getStartX();
         var3.Xqedii_25xm6yv = var1.getStartY();
         var3.Xqedii_jq9ubxh = var1.getStepX();
         var3.Xqedii_1t9vphc = var1.getStepY();
         var3.Xqedii_3464tdf = Xqedii_3rjux1w(var1.getDropBlockId());
         var3.Xqedii_35rn1ti = Xqedii_3rjux1w(var1.getDropContainerId());
         this.Xqedii_21ptbt1(var2, var1.getPlayer(), var3, true);
      }
   }

   public void Xqedii_ot25639(UltimateUICursorMoveEvent var1) {
      {
         Xqedii_36oytze var2 = this.Xqedii_2jdoztn(
            "cursor move", var1
         );
         var2.Xqedii_jq9ubxh = var1.getPageX() - var1.getPreviousPageX();
         var2.Xqedii_1t9vphc = var1.getPageY() - var1.getPreviousPageY();
         this.Xqedii_21ptbt1(
            "cursor move",
            var1.getPlayer(),
            var2,
            false
         );
      }
   }

   public void Xqedii_2p98a7o(UltimateUIKeyEvent var1) {
      {
         Xqedii_36oytze var2 = this.Xqedii_2jdoztn("key", var1);
         var2.Xqedii_2afs9at = Xqedii_3rjux1w(var1.getKey());
         var2.Xqedii_19v5iy3 = var1.isShift();
         List var3 = this.Xqedii_1hrsxi5.get("key");
         if (var3 != null) {
            for (Xqedii_3tcqkw2 var5 : (Iterable<Xqedii_3tcqkw2>) (var3)) {

               if (var5.Xqedii_21qp861(var2.Xqedii_3rt5h0f) && (var5.Xqedii_2f871ze(var2.Xqedii_2afs9at) || var5.Xqedii_2f871ze(var1.getBaseKey()))) {
                  this.run(var5, var1.getPlayer(), null, var2, null);
               }
            }
         }
      }
   }

   public void Xqedii_2s56q5q(UltimateUIInputSubmitEvent var1) {
      {
         Xqedii_36oytze var2 = this.Xqedii_2jdoztn("input", var1);
         var2.Xqedii_lc1i2g8 = Xqedii_3rjux1w(var1.getInputId());
         var2.Xqedii_a9in0yy = Xqedii_3rjux1w(var1.getValue());
         var2.Xqedii_1p91ipf = Xqedii_3rjux1w(var1.getPreviousValue());
         this.Xqedii_21ptbt1(
            "input",
            var1.getPlayer(),
            var2,
            false
         );
      }
   }

   public void Xqedii_3it2h16(PlayerJoinEvent var1) {
      {
         Xqedii_36oytze var2 = new Xqedii_36oytze("join", var1);

         for (Xqedii_3tcqkw2 var4 : this.Xqedii_1hrsxi5
            .getOrDefault("join", List.of())) {
            this.run(var4, var1.getPlayer(), null, var2, null);
         }
      }
   }

   public void Xqedii_3m8skc3(PlayerQuitEvent var1) {
      {
         Player var2 = var1.getPlayer();
         Xqedii_36oytze var3 = new Xqedii_36oytze("quit", var1);

         for (Xqedii_3tcqkw2 var5 : this.Xqedii_1hrsxi5
            .getOrDefault("quit", List.of())) {
            this.run(var5, var2, null, var3, null);
         }

         this.Xqedii_18ebdik.remove(var2.getUniqueId());
         this.Xqedii_tr2wpfg.remove(var2.getUniqueId());
      }
   }

   public static String Xqedii_3rjux1w(String var0) {
      {
         return var0 == null ? "" : var0;
      }
   }

   public void Xqedii_21ptbt1(String var1, Player var2, Xqedii_36oytze var3, boolean var4) {
      {
         List var5 = this.Xqedii_1hrsxi5.get(var1);
         if (var5 != null && var2 != null) {
            for (Xqedii_3tcqkw2 var7 : (Iterable<Xqedii_3tcqkw2>) (var5)) {

               if (var7.Xqedii_21qp861(var3.Xqedii_3rt5h0f)) {
                  String var8 = this.Xqedii_32nippv(var7, var2, var3, var4);
                  if (var8 != null) {
                     var3.Xqedii_1z2rxkd = var8;
                     this.run(var7, var2, null, var3, null);
                  }
               }
            }
         }
      }
   }

   public String Xqedii_32nippv(Xqedii_3tcqkw2 var1, Player var2, Xqedii_36oytze var3, boolean var4) {
      if (var1.Xqedii_2t32j8y == null) {
         return var3.Xqedii_lc1i2g8;
      } else if (var1.Xqedii_2f871ze(var3.Xqedii_lc1i2g8)) {
         return var3.Xqedii_lc1i2g8;
      } else if (var4 && !var3.Xqedii_lc1i2g8.isEmpty()) {
         if (var3.Xqedii_2f8w5mf == null) {
            ArrayList var5 = new ArrayList();
            if (UltimateUIAPI.isAvailable()) {
               String var6 = var3.Xqedii_lc1i2g8;
               LinkedHashSet var7 = new LinkedHashSet();

               while (var7.add(var6) && var5.size() < 32) {
                  String var8 = UltimateUIAPI.get().getElementParent(var2, var6);
                  if (var8 == null || var8.isEmpty()) {
                     break;
                  }

                  var5.add(var8);
                  var6 = var8;
               }
            }

            var3.Xqedii_2f8w5mf = var5;
         }

         for (String var10 : var3.Xqedii_2f8w5mf) {
            if (var1.Xqedii_2f871ze(var10)) {
               return var10;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   public void run(Xqedii_3tcqkw2 var1, Player var2, CommandSender var3, Xqedii_36oytze var4, List<Object> var5) {
      {
         Xqedii_1yqm9k3 var6 = new Xqedii_1yqm9k3(this, var1.Xqedii_1swqvpn, var2, var3, var4, var5);
         this.Xqedii_10b4lvn(new Xqedii_y5pr2ec(var6, var1.Xqedii_fw60fwr, true), this.Xqedii_aj0mxfz);
      }
   }

   public void Xqedii_10b4lvn(Xqedii_y5pr2ec var1, int var2) {
      if (var2 == this.Xqedii_aj0mxfz) {
         Xqedii_1yqm9k3 var3 = var1.Xqedii_3a9kp91;

         try {
            boolean var4 = var1.run();
            var3.Xqedii_3u9m9cl();
            if (var4) {
               return;
            }

            long var5 = Math.max(1L, var1.Xqedii_1gzo1er);
            Runnable var7 = () -> {
               if (var3.Xqedii_1v4ue4a == null || var3.Xqedii_1v4ue4a.isOnline()) {
                  this.Xqedii_10b4lvn(var1, var2);
               }
            };
            if (var3.Xqedii_1v4ue4a != null) {
               Xqedii_38o2zes.Xqedii_2sj63w1(this.Xqedii_1bs4d2x, var3.Xqedii_1v4ue4a, var7, var5);
            } else {
               Xqedii_38o2zes.Xqedii_3npx6fi(this.Xqedii_1bs4d2x, var7, var5);
            }
         } catch (Xqedii_3vsk7zm var9) {
            try {
               var3.Xqedii_3u9m9cl();
            } catch (RuntimeException var8) {
            }

            this.Xqedii_3kgc99t(var3.Xqedii_2q97y6i, var9);
         } catch (RuntimeException var10) {
            this.Xqedii_3kgc99t(var3.Xqedii_2q97y6i, new Xqedii_3vsk7zm(Xqedii_tcfzmwdb4z5o(String.valueOf(var10)), var1.Xqedii_18k2h4u));
         }
      }
   }

   public void Xqedii_1hvc3pb() {
      {
         this.Xqedii_140q75q = true;
         int var1 = this.Xqedii_aj0mxfz;
         Xqedii_38o2zes.Xqedii_3npx6fi(this.Xqedii_1bs4d2x, () -> {
            {
               this.Xqedii_2z1vkt1(var1);
            }
         }, 1L);
      }
   }

   public void Xqedii_2z1vkt1(int var1) {
      if (this.Xqedii_140q75q && var1 == this.Xqedii_aj0mxfz) {
         this.Xqedii_1zrzxna = this.Xqedii_1zrzxna + 1L;

         for (Xqedii_3tcqkw2 var3 : this.Xqedii_3c71sgp) {
            if (this.Xqedii_1zrzxna % var3.Xqedii_3gg5za6 == 0L) {
               if (var3.Xqedii_nyxmub2 == null) {
                  this.run(
                     var3,
                     null,
                     Bukkit.getConsoleSender(),
                     new Xqedii_36oytze("every", null),
                     null
                  );
               } else {
                  for (Player var5 : Bukkit.getOnlinePlayers()) {
                     Xqedii_38o2zes.Xqedii_1avvt9e(
                        this.Xqedii_1bs4d2x,
                        var5,
                        () -> {
                           if (var1 == this.Xqedii_aj0mxfz && UltimateUIAPI.isAvailable()) {
                              String var4 = UltimateUIAPI.get().getOpenGuiName(var5);
                              if (var4 != null && var4.equalsIgnoreCase(var3.Xqedii_nyxmub2)) {
                                 Xqedii_36oytze var5x = new Xqedii_36oytze(
                                    "every", null
                                 );
                                 var5x.Xqedii_3rt5h0f = var4;
                                 this.run(var3, var5, null, var5x, null);
                              }
                           }
                        }
                     );
                  }
               }
            }
         }

         if (!this.Xqedii_tr2wpfg.isEmpty()) {
            this.Xqedii_2vdmdmn(var1);
         }

         if (this.Xqedii_1zrzxna % 6000L
            == 0L) {
            this.Xqedii_2ux70w3();
         }

         Xqedii_38o2zes.Xqedii_3npx6fi(this.Xqedii_1bs4d2x, () -> {
            {
               this.Xqedii_2z1vkt1(var1);
            }
         }, 1L);
      }
   }

   public String Xqedii_14ikmfz(Player var1, String var2) {
      {
         return var1 != null && var2 != null && var2.indexOf(37) >= 0 && UltimateUIAPI.isAvailable()
            ? UltimateUIAPI.get().resolvePlaceholders(var1, var2)
            : var2;
      }
   }

   public Map<String, Object> Xqedii_1u89z52(Player var1) {
      {
         return this.Xqedii_18ebdik.computeIfAbsent(var1.getUniqueId(), var0 -> {
            {
               return new LinkedHashMap<>();
            }
         });
      }
   }

   public Map<String, Object> Xqedii_nm0vadd(Player var1) {
      {
         return this.data.computeIfAbsent(var1.getUniqueId(), var0 -> {
            {
               return new LinkedHashMap<>();
            }
         });
      }
   }

   public Map<String, Object> Xqedii_2e6s6rg() {
      {
         return this.Xqedii_2gg9i2g;
      }
   }

   public void Xqedii_34ytbys(Xqedii_1kffk3z var1, int var2, String var3) {
      {
         this.Xqedii_1bs4d2x.getLogger().info(Xqedii_1e6i7in93boo2(var1.Xqedii_1gnbwtp, var2, var3));
      }
   }

   public void Xqedii_3kgc99t(Xqedii_1kffk3z var1, Xqedii_3vsk7zm var2) {
      {
         String var3 = var1 == null ? "script" : var1.Xqedii_1gnbwtp;
         String var4 = Xqedii_8pdvollsugc1(var3, var2.Xqedii_2ei61uy(), var2.getMessage());
         long var5 = System.currentTimeMillis();
         Long var7 = this.Xqedii_2evhzeq.get(var4);
         if (var7 == null || var5 - var7 >= 10000L) {
            this.Xqedii_2evhzeq.put(var4, var5);
            if (this.Xqedii_2evhzeq.size() > 256) {
               this.Xqedii_2evhzeq.clear();
            }

            this.Xqedii_1bs4d2x.getLogger().warning(Xqedii_a0xrmet1n0d8(var3, var2.Xqedii_2ei61uy(), var2.getMessage()));

            for (Player var9 : Bukkit.getOnlinePlayers()) {
               if (var9.hasPermission(
                  "ultimateui.reload"
               )) {
                  String var10001 = "Script error in {0} line {1}: {2}";
                  Object[] var10002 = new Object[3];
                  var10002[0] = var3;
                  var10002[1] = var2.Xqedii_2ei61uy();
                  var10002[2] = var2.getMessage();
                  Xqedii_3ul4hqj.Xqedii_2dtmver(var9, var10001, var10002);
               }
            }
         }
      }
   }

   public void Xqedii_1wahnsk(CommandSender var1, Xqedii_1zgvf22 var2) {
      if (!var2.errors().isEmpty()) {
         ArrayList var3 = new ArrayList();
         int var4 = 0;

         for (Xqedii_3jb0tcl var6 : var2.errors()) {
            if (var4++ >= 12) {
               String var7 = "...and {0} more, see the console!";
               Object[] var9 = new Object[1];
               var9[0] = var2.errors().size() - 12;
               var3.add(Xqedii_3ul4hqj.Xqedii_l0arb10(var7, var9));
               break;
            }

            String var10001 = "{0} line {1}: {2}";
            Object[] var10002 = new Object[3];
            var10002[0] = var6.file();
            var10002[1] = var6.error().Xqedii_2ei61uy();
            var10002[2] = var6.error().getMessage();
            var3.add(Xqedii_3ul4hqj.Xqedii_l0arb10(var10001, var10002));
         }

         String var8 = "Your scripts have {0} errors!";
         Object[] var10 = new Object[1];
         var10[0] = var2.errors().size();
         Xqedii_3ul4hqj.Xqedii_wmipi15(var1, Xqedii_3ul4hqj.Xqedii_l0arb10(var8, var10), var3);
      }
   }

   public void Xqedii_woa4jl4(Xqedii_1zgvf22 var1) {
      {
         for (Xqedii_3jb0tcl var3 : var1.errors()) {
            this.Xqedii_1bs4d2x
               .getLogger()
               .warning(
                  Xqedii_2qf972rv0xc2m(
                     var3.file(),
                     var3.error().Xqedii_2ei61uy(),
                     var3.error().Xqedii_1rkxiyu() > 0
                        ? Xqedii_2p5wcdd3xgzr5(var3.error().Xqedii_1rkxiyu())
                        : "",
                     var3.error().getMessage()
                  )
               );
         }
      }
   }

   public void Xqedii_2yvqp6m() {
      if (this.Xqedii_73sb6rc.isFile()) {
         YamlConfiguration var1 = YamlConfiguration.loadConfiguration(this.Xqedii_73sb6rc);
         ConfigurationSection var2 = var1.getConfigurationSection(
            "players"
         );
         if (var2 != null) {
            for (String var4 : var2.getKeys(false)) {
               try {
                  ConfigurationSection var5 = var2.getConfigurationSection(var4);
                  if (var5 != null) {
                     this.data.put(UUID.fromString(var4), this.Xqedii_j4z1k5h(var5));
                  }
               } catch (IllegalArgumentException var6) {
               }
            }
         }

         ConfigurationSection var7 = var1.getConfigurationSection(
            "global"
         );
         if (var7 != null) {
            this.Xqedii_2gg9i2g.putAll(this.Xqedii_j4z1k5h(var7));
         }
      }
   }

   public Map<String, Object> Xqedii_j4z1k5h(ConfigurationSection var1) {
      {
         LinkedHashMap var2 = new LinkedHashMap();

         for (String var4 : var1.getKeys(false)) {
            var2.put(var4, this.Xqedii_gnyai36(var1.get(var4)));
         }

         return var2;
      }
   }

   public Object Xqedii_gnyai36(Object var1) {
      if (var1 instanceof ConfigurationSection var8) {
         return this.Xqedii_j4z1k5h(var8);
      } else if (var1 instanceof Number var7) {
         return var7.doubleValue();
      } else if (var1 instanceof List var6) {
         ArrayList var9 = new ArrayList(var6.size());

         for (Object var11 : var6) {
            var9.add(this.Xqedii_gnyai36(var11));
         }

         return var9;
      } else if (!(var1 instanceof Map var2)) {
         return var1;
      } else {
         LinkedHashMap var3 = new LinkedHashMap();

         for (Entry var5 : (Iterable<Entry>) (var2.entrySet())) {

            var3.put(String.valueOf(var5.getKey()), this.Xqedii_gnyai36(var5.getValue()));
         }

         return var3;
      }
   }

   public YamlConfiguration Xqedii_255w5ti() {
      {
         YamlConfiguration var1 = new YamlConfiguration();

         for (Entry var3 : this.data.entrySet()) {
            if (!((Map)var3.getValue()).isEmpty()) {
               var1.createSection(Xqedii_1t2fhcem4d4u3(String.valueOf(var3.getKey())), this.Xqedii_r38f8rh((Map<String, Object>)var3.getValue()));
            }
         }

         synchronized (this.Xqedii_2gg9i2g) {
            if (!this.Xqedii_2gg9i2g.isEmpty()) {
               var1.createSection(
                  "global", this.Xqedii_r38f8rh(this.Xqedii_2gg9i2g)
               );
            }

            return var1;
         }
      }
   }

   public Map<String, Object> Xqedii_r38f8rh(Map<String, Object> var1) {
      {
         for (int var2 = 0; var2 < 3; var2++) {
            try {
               return (Map<String, Object>)Xqedii_z4wg071.Xqedii_3ujolw5(var1);
            } catch (ConcurrentModificationException var4) {
            }
         }

         return new LinkedHashMap<>();
      }
   }

   public void Xqedii_1dijqtn() {
      if (!this.data.isEmpty() || !this.Xqedii_2gg9i2g.isEmpty() || this.Xqedii_73sb6rc.exists()) {
         try {
            this.Xqedii_255w5ti().save(this.Xqedii_73sb6rc);
         } catch (IOException var2) {
            this.Xqedii_1bs4d2x.getLogger().warning(Xqedii_3bkbf3sf0gbil(var2.getMessage()));
         }
      }
   }

   public void Xqedii_2ux70w3() {
      if (!this.data.isEmpty() || !this.Xqedii_2gg9i2g.isEmpty()) {
         YamlConfiguration var1 = this.Xqedii_255w5ti();
         Xqedii_38o2zes.Xqedii_u043a3q(this.Xqedii_1bs4d2x, () -> {
            {
               try {
                  var1.save(this.Xqedii_73sb6rc);
               } catch (IOException var3) {
                  this.Xqedii_1bs4d2x.getLogger().warning(Xqedii_ep70tsrdheuz(var3.getMessage()));
               }
            }
         });
      }
   }

   public CommandMap Xqedii_1cr6e10() {
      {
         try {
            Server var1 = Bukkit.getServer();
            return var1.getClass()
                  .getMethod("getCommandMap")
                  .invoke(var1) instanceof CommandMap var3
               ? var3
               : null;
         } catch (RuntimeException | ReflectiveOperationException var4) {
            return null;
         }
      }
   }

   public void Xqedii_2am6bju() {
      if (!this.Xqedii_2u229x4.isEmpty()) {
         CommandMap var1 = this.Xqedii_1cr6e10();
         if (var1 == null) {
            this.Xqedii_1bs4d2x
               .getLogger()
               .warning(
                  "Could not register script commands: the command map is not available"
               );
         } else {
            for (Xqedii_bflxzg9 var3 : this.Xqedii_2u229x4.values()) {
               var1.register("ultimateui", var3);
            }

            this.syncCommands();
         }
      }
   }

   public void Xqedii_3d4ydyl() {
      if (!this.Xqedii_2u229x4.isEmpty()) {
         CommandMap var1 = this.Xqedii_1cr6e10();

         for (Xqedii_bflxzg9 var3 : this.Xqedii_2u229x4.values()) {
            if (var1 == null) {
               break;
            }

            var3.unregister(var1);

            try {
               if (var1.getClass()
                  .getMethod("getKnownCommands")
                  .invoke(var1) instanceof Map var5) {
                  var5.values()
                     .removeIf(
                        var1x -> {
                           {
                              return ((var1x == var3
                                 ? 1
                                 : 0) != 0);
                           }
                        }
                     );
               }
            } catch (RuntimeException | ReflectiveOperationException var6) {
            }
         }

         this.Xqedii_2u229x4.clear();
         this.syncCommands();
      }
   }

   public void syncCommands() {
      {
         try {
            Server var1 = Bukkit.getServer();
            var1.getClass()
               .getMethod("syncCommands")
               .invoke(var1);
         } catch (RuntimeException | ReflectiveOperationException var5) {
         }

         for (Player var2 : Bukkit.getOnlinePlayers()) {
            try {
               var2.updateCommands();
            } catch (RuntimeException var4) {
            }
         }
      }
   }

   private static /* synthetic */ String Xqedii_2cpve3e2scybj(String string) {
        return "Could not read the file: " + string;
    }

    private static /* synthetic */ String Xqedii_tcfzmwdb4z5o(String string) {
        return "Internal error: " + string;
    }

    private static /* synthetic */ String Xqedii_1e6i7in93boo2(String string, int n, String string2) {
        return "[" + string + ":" + n + "] " + string2;
    }

    private static /* synthetic */ String Xqedii_8pdvollsugc1(String string, int n, String string2) {
        return string + ":" + n + ":" + string2;
    }

    private static /* synthetic */ String Xqedii_a0xrmet1n0d8(String string, int n, String string2) {
        return "Script error in " + string + " line " + n + ": " + string2;
    }

    private static /* synthetic */ String Xqedii_2p5wcdd3xgzr5(int n) {
        return ":" + n;
    }

    private static /* synthetic */ String Xqedii_2qf972rv0xc2m(String string, int n, String string2, String string3) {
        return "Script error in " + string + " line " + n + string2 + ": " + string3;
    }

    private static /* synthetic */ String Xqedii_1t2fhcem4d4u3(String string) {
        return "players." + string;
    }

    private static /* synthetic */ String Xqedii_3bkbf3sf0gbil(String string) {
        return "Could not save script-data.yml: " + string;
    }

    private static /* synthetic */ String Xqedii_ep70tsrdheuz(String string) {
        return "Could not save script-data.yml: " + string;
    }
}
