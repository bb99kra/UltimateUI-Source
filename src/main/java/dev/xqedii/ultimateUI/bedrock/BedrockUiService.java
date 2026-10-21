package dev.xqedii.ultimateUI.bedrock;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.Xqedii_1cydrw2;
import dev.xqedii.ultimateUI.Xqedii_1lbievo;
import dev.xqedii.ultimateUI.Xqedii_1nc5hgr;
import dev.xqedii.ultimateUI.BedrockPackExportResult;
import dev.xqedii.ultimateUI.Xqedii_1tuv3qh;
import dev.xqedii.ultimateUI.Xqedii_23ssj1h;
import dev.xqedii.ultimateUI.Xqedii_2hdrgs5;
import dev.xqedii.ultimateUI.Xqedii_2kb1ns6;
import dev.xqedii.ultimateUI.Xqedii_2pn9w2s;
import dev.xqedii.ultimateUI.Xqedii_38o2zes;
import dev.xqedii.ultimateUI.Xqedii_3nmychi;
import dev.xqedii.ultimateUI.Xqedii_3vb5gzo;
import dev.xqedii.ultimateUI.Xqedii_too58w6;
import dev.xqedii.ultimateUI.Xqedii_v7p1hqx;
import dev.xqedii.ultimateUI.Xqedii_wq55eud;
import dev.xqedii.ultimateUI.api.event.UltimateUIInputSubmitEvent;
import dev.xqedii.ultimateUI.service.input.InputFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.runtime.ObjectMethods;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

public final class BedrockUiService {
   public static final String Xqedii_2lazzov9h9m2o = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";
   public static final String Xqedii_12l9hua = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("KmtF0V3hkYjOK7HiYf/esncBBgNovoLt7YWuOf9urYWCA3NbFPW2tTxI");
   public static final String Xqedii_2dzgjku = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
      "HnFEsh0SOa769gfoaoCehZrmDvXRwGnjk0dKgAUZ27HgQw+ur27PepALckgLyuQsu0Fr4w+pm+Y3i2KFUo5rTZ92YJmlnBzQy63BnbvCvfQJ91fZLOXwf8DbmO2nWFUPn4XVXUn4MubJZ5nNQ6NS/TghdBo1aA=="
   );
   public static final String Xqedii_1p839t2 = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
      "fHU3dm9euUyXNpThTieUoXcvtcMKtHaC+Kf6D+/dFstjfixbMeBhSsq+UKbnEuNUFILYKBpykX692OoO5s6F7SsIuuF+pAIT8ky6mEfK4hrDWAQz0V17n3suYAGPGB/dgxjoMw=="
   );
   public static final String Xqedii_1jbp7so = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
      "rU9NhluVlD4pXql2nkjrFqsV6JYN/9EX/7b8k8ot+PirYWS8qkofuie0PezzMjevPMwWl1PqGJ3BiXqQaffPJL3UTLM0yu1cG2hZt4upTiSUEubcmsViXCRnCrqqeyzl"
   );
   public static final double Xqedii_323ec5m = 854.0;
   public static final double Xqedii_3t42cbv = 480.0;
   public static final int Xqedii_2ey5448 = 50;
   public static final double Xqedii_1skqjso = 0.62;
   public static final String Xqedii_2t2p3oh = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("OKHwq98FtBl1bGUPgSOt/3k5jpvXT49IPyS26Z52");
   public static final Pattern Xqedii_ad4hxn1;
   public final UltimateUI Xqedii_5tfok30;
   public final Xqedii_2pn9w2s Xqedii_ipfyrwr;
   public final Xqedii_1nc5hgr Xqedii_1o6ffne = new Xqedii_1nc5hgr();
   public final Xqedii_too58w6 Xqedii_31t5ofi;
   public final Xqedii_2hdrgs5 Xqedii_3pmue8y;
   public volatile Map<String, Xqedii_2kb1ns6> Xqedii_2vtp3jf = new LinkedHashMap<>();
   public final Map<UUID, String> Xqedii_2rr4o3m = new ConcurrentHashMap<>();
   public final Map<UUID, Xqedii_23ssj1h> Xqedii_1ebrd9y = new ConcurrentHashMap<>();
   public final Map<UUID, AtomicInteger> Xqedii_1dxcxs6 = new ConcurrentHashMap<>();
   public volatile boolean Xqedii_1tms53a;
   public volatile boolean Xqedii_21hm206;
   public volatile boolean Xqedii_337zgsp;
   public final Map<String, String> Xqedii_3b4x69h = new ConcurrentHashMap<>();

   public BedrockUiService(UltimateUI var1) {
      this.Xqedii_5tfok30 = var1;
      this.Xqedii_ipfyrwr = new Xqedii_2pn9w2s(var1.getLogger());
      this.Xqedii_3pmue8y = new Xqedii_2hdrgs5(var1.getLogger());
      this.Xqedii_31t5ofi = new Xqedii_too58w6(var1, this.Xqedii_1o6ffne, new Xqedii_3vb5gzo(this));
   }

   public void reload() {
      if ((582402527 * 582402527 + 582402527 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_1tms53a = this.Xqedii_vuzthgm(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("roy+q1e47aVX1par4dDWEguN3ZQYzT1yH0Mv47krwSJnJLXCPTb5nIpQ"),
            (Xqedii_1lbievo.Xqedii_3evul6ygeglfz(14746351, 19) != 0)
         );
         this.Xqedii_337zgsp = this.Xqedii_vuzthgm(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("TqMeRPaDu6qKgSWIjPyJ4Vq8PzTQog6mSOPaYEi5CeHoKoAInQA="),
            (Xqedii_1lbievo.Xqedii_321mxj7tclmi0(198407256, 3) != 0)
         );
         this.Xqedii_3b4x69h.clear();
         this.Xqedii_ipfyrwr.Xqedii_211piqv(this.Xqedii_337zgsp);
         if (!this.Xqedii_1tms53a) {
            this.Xqedii_2vtp3jf = new LinkedHashMap<>();
         } else {
            this.Xqedii_3apxe0j();
            ArrayList var1 = new ArrayList();
            LinkedHashMap var2 = new LinkedHashMap();

            try {
               for (Xqedii_2kb1ns6 var4 : (Iterable<Xqedii_2kb1ns6>) (this.Xqedii_2y67ssd().Xqedii_2s9ccrs(var1))) {

                  var2.put(var4.Xqedii_3j6s705.toLowerCase(Locale.ROOT), var4);
               }
            } catch (RuntimeException var6) {
               this.Xqedii_5tfok30.getLogger().warning(Xqedii_36mwllnyw4pmz(String.valueOf(var6)));
            }

            this.Xqedii_2vtp3jf = var2;
            int var7 = Xqedii_1lbievo.Xqedii_3evul6ygeglfz(98435538, 4);

            for (String var5 : (Iterable<String>) (var1)) {

               if (var7++ >= Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1849993805, 18)) {
                  this.Xqedii_5tfok30.getLogger().warning(Xqedii_nag059ssg9fg(var1.size() - Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851976269, 14)));
                  break;
               }

               this.Xqedii_5tfok30.getLogger().warning(Xqedii_42xumw2ml31q(var5));
            }

            this.Xqedii_ti10yn9();
            this.Xqedii_1jd4n0q();
            if (this.Xqedii_ipfyrwr.Xqedii_29ttl5r() && !this.Xqedii_ipfyrwr.Xqedii_1148524()) {
               this.Xqedii_5tfok30
                  .getLogger()
                  .warning(
                     Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                        "0ECiYjp/aBiHHiTQY6m5lsbvVB7ThMRDwoqdL59R1ro4ya20nW8eXQNDC82xc+dN/lnPPMhsNC2pn86U5xOomHxcYeBdSB0/co5gvh+RwTohq+wfXhVxOmBUMMG52BczKNIezyxv4h0IpeRRfNTT0pSlkglTyJA3VDAp+YZFOcT8blZBAPrROtNKUvcRcgoONvYlHrnpD2YHqUVZXtg9ECxjg5QxFGzLoJsV0FrkDAD3/yV5fAYQleEmNuSH5GKzeozvC4NL8zzib+iAAZQTgxDY1XdlW9kADNDyeT7zzjE19USJbOdIjaj+EC4="
                     )
                  );
            }
         }
      }
   }

   public void Xqedii_2e0aui3(String var1) {
      if (((515088417 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         if (this.Xqedii_337zgsp) {
            this.Xqedii_5tfok30.getLogger().info(Xqedii_68qud8chb4tc(var1));
         }
      }
   }

   public void Xqedii_3apxe0j() {
      if ((1710765637 * (1710765637 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (this.Xqedii_337zgsp) {
         this.Xqedii_2e0aui3(
            Xqedii_wblv6ur653vf(
               this.Xqedii_1tms53a,
               this.Xqedii_vuzthgm(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("zVSSeROy1Q7JCqEg+VQ6VBxU7HIVmcfifmufwkJGjX6rFH/yUNfwP41Bb/wsjxron6lPHYsLJGa2AMUKjdRXwPly"),
                  (Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-373673979, 10) != 0)
               ),
               this.Xqedii_kpr1s2c(),
               this.Xqedii_12acaqj(),
               this.Xqedii_vuzthgm(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("3CmWzQe9legdwsz+qw1MGSP15lJexLydWCRQ76x+ERI7EJIiIWIuCa4uCM3Ccg=="),
                  (Xqedii_1lbievo.Xqedii_3evul6ygeglfz(1887532928, 26) != 0)
               ),
               this.Xqedii_vuzthgm(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("smGPkWue3FEbvsmFEV5rplzxwstp3Isx8GAM2HefQevNwYdEm7ZCy6KyoQDYpfjppJ4+QQj8HSE+M4TmUfid16TY+aizSQ=="),
                  (Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-519901440, 27) != 0)
               )
            )
         );
         this.Xqedii_2e0aui3(
            Xqedii_2qfdolqpps3qo(
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("nVoAIIhOQKMuse4rAxVebW6r/C9gtaK4LjDi9rOGR8ryS71BjNBihrd5Gi24s+9sloCGMw=="),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_27dd9ncy36b8z(-7030697949702792143L, 31))
               ),
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("tw1/9CL1PF57xoUMkquYR+51r32bWpkBCTaM4V9Iv+XktpqnwzZO2OSxguTbViKVBtC0u4AC"),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(-6998014657269209601L, 44))
               ),
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("XK38mMCqNmtWspiM1685TMV2eZMfr90gFTEYSrKV1z6pCL+r5lKecS8q8+nUAbn76rMmxp5k3u8xevkJKhQGfg=="),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_qbzqr1exc6v5(5678337269991285311L, 26))
               ),
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("9hV0lZ97weCmAIQo5tCWZnlH5rXPq/xnB6W7wPu2RBc/oNpWBy1ZTspGScSLTYAUUmzA6LjZ6BiO+LCOmTK5m34t"),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(-7493415053098621445L, 4))
               ),
               this.Xqedii_vuzthgm(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("DyT7wS+ff3uWoTUKs6NvxoVLnqQxtLEApob8R876WH1NkRvkDZxOAKyKKRlqeRFDt7qW0HyKVOfUVyYBrjwpfbJlMFD+DiCg"),
                  (Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 29) != 0)
               )
            )
         );
         this.Xqedii_2e0aui3(
            Xqedii_1fq3nrpvuo2oh(
               this.Xqedii_ipfyrwr.Xqedii_29ttl5r(),
               this.Xqedii_ipfyrwr.Xqedii_1148524(),
               this.Xqedii_ipfyrwr.Xqedii_2h9n0cx(),
               this.Xqedii_ipfyrwr.Xqedii_2m5qhgo(),
               this.Xqedii_ipfyrwr.Xqedii_1z3dg26()
            )
         );
      }
   }

   public boolean Xqedii_vuzthgm(String var1, boolean var2) {
      if ((-395098059 * -395098059 - -395098059 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_5tfok30.getConfig().getBoolean(Xqedii_3kfbzi40to3h2(var1), var2);
      }
   }

   public double Xqedii_2j0qwhq(String var1, double var2) {
      if ((-183501008 ^ -183501008) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_5tfok30.getConfig().getDouble(Xqedii_1oa3xvl5psns9(var1), var2);
      }
   }

   public String Xqedii_2p2kfh0(String var1, String var2) {
      if ((1596397687 ^ 1596397687) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_5tfok30.getConfig().getString(Xqedii_botq99zzaj0n(var1), var2);
      }
   }

   public String Xqedii_kpr1s2c() {
      if ((-1626623004 * -1626623004 + -1626623004 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var1 = this.Xqedii_2p2kfh0(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("h1+Pt8jGmKad1CGZcufDlAhhQq9aPqU7JmR6VY/PR8xiv2wVgsP+w5aql1Kf4hS2Lc8ZbrAqR8UjO4zdgAHY9zE99/nYsEfI1JzPSA=="),
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
               "fHU3dm9euUyXNpThTieUoXcvtcMKtHaC+Kf6D+/dFstjfixbMeBhSsq+UKbnEuNUFILYKBpykX692OoO5s6F7SsIuuF+pAIT8ky6mEfK4hrDWAQz0V17n3suYAGPGB/dgxjoMw=="
            )
         );
         return var1 != null
               && !var1.isBlank()
               && !Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                     "HnFEsh0SOa769gfoaoCehZrmDvXRwGnjk0dKgAUZ27HgQw+ur27PepALckgLyuQsu0Fr4w+pm+Y3i2KFUo5rTZ92YJmlnBzQy63BnbvCvfQJ91fZLOXwf8DbmO2nWFUPn4XVXUn4MubJZ5nNQ6NS/TghdBo1aA=="
                  )
                  .equalsIgnoreCase(var1.trim())
            ? var1.trim()
            : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
               "fHU3dm9euUyXNpThTieUoXcvtcMKtHaC+Kf6D+/dFstjfixbMeBhSsq+UKbnEuNUFILYKBpykX692OoO5s6F7SsIuuF+pAIT8ky6mEfK4hrDWAQz0V17n3suYAGPGB/dgxjoMw=="
            );
      }
   }

   public String Xqedii_12acaqj() {
      if ((2017047547 ^ 2017047547) != 0) {
         throw new IllegalStateException();
      } else {
         String var1 = this.Xqedii_2p2kfh0(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("x7LPZdP21yZlG7qk9vWpjrwDxbX7PwyG122JPIbmsf7IyhbC4ueZ4h0R0P/mjIW1lY00WkBdE3TsLfDsO2ulgcacZZI+6lAbTgMzBg=="),
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
               "rU9NhluVlD4pXql2nkjrFqsV6JYN/9EX/7b8k8ot+PirYWS8qkofuie0PezzMjevPMwWl1PqGJ3BiXqQaffPJL3UTLM0yu1cG2hZt4upTiSUEubcmsViXCRnCrqqeyzl"
            )
         );
         return var1 != null && !var1.isBlank()
            ? var1.trim()
            : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
               "rU9NhluVlD4pXql2nkjrFqsV6JYN/9EX/7b8k8ot+PirYWS8qkofuie0PezzMjevPMwWl1PqGJ3BiXqQaffPJL3UTLM0yu1cG2hZt4upTiSUEubcmsViXCRnCrqqeyzl"
            );
      }
   }

   public Xqedii_wq55eud Xqedii_2y67ssd() {
      if (((286078451 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return new Xqedii_wq55eud(this.Xqedii_5tfok30.getDataFolder())
            .Xqedii_22sacy4(
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("nVoAIIhOQKMuse4rAxVebW6r/C9gtaK4LjDi9rOGR8ryS71BjNBihrd5Gi24s+9sloCGMw=="),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(-6926410100794071553L, 58))
               ),
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("tw1/9CL1PF57xoUMkquYR+51r32bWpkBCTaM4V9Iv+XktpqnwzZO2OSxguTbViKVBtC0u4AC"),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_27dd9ncy36b8z(8037720574939729139L, 42))
               )
            )
            .Xqedii_12rq19l(
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("RA+IHOLcLhnSac/FZKEM5ycJTI+zm3t/lE5XNr7fGygRCRShzf9nCt/AC3RjryOcSFm97CQWnHNg+KWNjCKqCFSf0t8="),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(-6998019094087875102L, 15))
               )
            )
            .Xqedii_5gekgz5(
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("1pTa6TOSU/dPVs8xGLpk+q87HnuY4VmGFDSJYOxxKMU8ApiqstEcxBqyFqY/UpG0VCZRqYrY1WFvfi+DM72NhP8E"),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(-7027292491665775106L, 2))
               ),
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("NLVEon5B/Va7n5rdBuBqSGCvLPSAcFKxVV4nZb3bRlCWjw1bOr1QiP1yUf7LO9Wb9XBgmQC9dvPsRjgaQXSIYuNxmUBT6sMLoQc="),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_27dd9ncy36b8z(-328544546721506270L, 9))
               )
            );
      }
   }

   public Xqedii_1cydrw2 Xqedii_pl0jemr() {
      if ((1917509263 * 1917509263 - 1917509263 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         double var1 = this.Xqedii_2j0qwhq(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("XK38mMCqNmtWspiM1685TMV2eZMfr90gFTEYSrKV1z6pCL+r5lKecS8q8+nUAbn76rMmxp5k3u8xevkJKhQGfg=="),
            Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_27dd9ncy36b8z(8961532366645127067L, 45))
         );
         double var3 = this.Xqedii_2j0qwhq(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("9hV0lZ97weCmAIQo5tCWZnlH5rXPq/xnB6W7wPu2RBc/oNpWBy1ZTspGScSLTYAUUmzA6LjZ6BiO+LCOmTK5m34t"),
            this.Xqedii_2j0qwhq(
               Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("JftJw22MlB1mCRT8VkuAIA3mScHdE7om3bTpZrteON4DpAm5jy49l6nDKolxVVNscUU/FbCoidAFX/IjqN2GMzue7gxIgMj7Y/+Vlw=="),
               Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_m201bjb6pkf2(3443156771242325743L, 43))
            )
         );
         if (var1 == Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_27dd9ncy36b8z(8110353196928917271L, 59))
            && var3 == Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(2225352942766912992L, 14))) {
            var1 = Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_27dd9ncy36b8z(3961122049662523599L, 38));
            var3 = Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_27dd9ncy36b8z(7416892914585353092L, 6));
         }

         return new Xqedii_1cydrw2(this.Xqedii_5tfok30.getDataFolder())
            .Xqedii_2vnohf3(var1, var3)
            .Xqedii_1fyw0cu(
               this.Xqedii_2j0qwhq(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("uso1xSP+RXWtIYrNGk70Tqtox8vEmlAwKpxbzH7d+idoLUeeU2adpCS8F/m/Ud9a/uhweNWpWVWOl0i+gkZjI2d0og7p0Q=="),
                  Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_qbzqr1exc6v5(-99753178153968111L, 19))
               )
            )
            .Xqedii_32wrkf8(
               this.Xqedii_vuzthgm(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("dkuok2hQmMZ/rUm0bzqzMpYrqXBgZ7IEVAraGOvJ8NjTTe4rY89Gft3wL+DAvkcpBAhyc4PFJCFH2byB"),
                  (Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 5) != 0)
               )
            )
            .Xqedii_tc5jj8a(this.Xqedii_2r92e6t())
            .Xqedii_qa67ajl(this.Xqedii_337zgsp, var1x -> {
               if ((388935058 * 388935058 - 388935058 & 1) != 0) {
                  throw new IllegalStateException();
               } else {
                  this.Xqedii_5tfok30.getLogger().info(Xqedii_3tokgscxwa20n(var1x));
               }
            })
            .Xqedii_ek6kcym(
               this.Xqedii_2p2kfh0(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("VGQIvQ0qTi5QJ4TxTD4m0Wmf5WWaWGjrreVo4RLx1hed9zZJ97zHuT239K3onA=="),
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("HTTwX2hmTZLWBbSDaWyCDiF4LkInRFkt3mJZjfkzq8HdG9EhT0PJL9ROApQ1YjOi")
               )
            );
      }
   }

   public boolean Xqedii_2r92e6t() {
      if ((-1104494982 * -1104494982 + -1104494982 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_vuzthgm(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("aOqYcHTZPZEZxFMRzTiqk89+D2BzkWC6ihlulCez5WmblLPKK9qEZyp+c0DkwsZ9oJKkwg=="),
            (Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1494695913, 12) != 0)
         );
      }
   }

   public boolean Xqedii_1c0bq4z(Xqedii_2kb1ns6 var1) {
      if (((1895446989 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return ((this.Xqedii_2r92e6t() && var1 != null && var1.Xqedii_3w4yc84() && this.Xqedii_ipfyrwr.Xqedii_2s5vyvr()
            ? Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-373673979, 10)
            : Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-373675003, 10)) != 0);
      }
   }

   public void Xqedii_1jd4n0q() {
      if (((-1972512597 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (this.Xqedii_21hm206
         && !this.Xqedii_2vtp3jf.isEmpty()
         && this.Xqedii_ipfyrwr.Xqedii_29ttl5r()
         && this.Xqedii_vuzthgm(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Pc/TkTq90EjEmCYJjRtjUh6tIs7cBBfWRP3RSjD9yDC4P4oShgzle84+OK3m3qoT"),
            (Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1120451072, 7) != 0)
         )) {
         this.Xqedii_5tfok30
            .getLogger()
            .info(
               Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                  "VV3UIHP1FSL5HSlGFRxjuSGgIsO94hzlmD6i2OjP1iku6JHtliwSmo5wj31tD3dbzmi/XI5KhGZwqBD695mi/Xfq8yusspHsCPwt0kSsReAa43rYNaW+QtzBmhQkm0mwAsuQshsASgMLkeB+dmfbcOPfDDqM2XQ/s896nNl0cwggA6ss2nnatmzJK69f2hwDSKzhDe11mBiFHZxbaU4EKRfQTiBrewVvZMxUD+A20Mo8WVQdiZuvoV484nCnE2MLFS1vUpt8ErwHozRQpZM="
               )
            );
         this.buildPackAsync(var1 -> {
            if (((-1515060999 | 1) & 1) == 0) {
               throw new IllegalStateException();
            } else {
               if (var1.success()) {
                  this.Xqedii_5tfok30.getLogger().info(Xqedii_2tgtqkbeigcjw(var1.message()));
               } else {
                  this.Xqedii_5tfok30.getLogger().warning(Xqedii_mhr3hyq8k9bn(var1.message()));
               }
            }
         });
      }
   }

   public void Xqedii_ti10yn9() {
      if ((1380557744 * 1380557744 + 1380557744 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Map var1 = this.Xqedii_pl0jemr().Xqedii_25ymzyo();
         if (var1.isEmpty()) {
            this.Xqedii_21hm206 = ((!this.Xqedii_2vtp3jf.isEmpty()
               ? Xqedii_1lbievo.Xqedii_321mxj7tclmi0(402846941, 25)
               : Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-1140612085, 13)) != 0);
         } else {
            int var2 = Xqedii_1lbievo.Xqedii_3evul6ygeglfz(196871076, 5);

            for (Xqedii_2kb1ns6 var4 : this.Xqedii_2vtp3jf.values()) {
               int[] var5 = (int[])var1.get(var4.Xqedii_3j6s705.toLowerCase(Locale.ROOT));
               if (var5 == null
                  || var5[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(1166064951, 23)] != var4.Xqedii_2kz3xjg
                  || var5[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-285155070, 11)] != var4.Xqedii_b0fv9fz
                  || var5[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(542026565, 21)] != var4.Xqedii_1ei4hmh
                  || var5[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-1005863933, 29)] != var4.Xqedii_1jqflox
                  || var5[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(542026565, 20)] != var4.Xqedii_12px6oi()
                  || var5[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-186834942, 9)] != var4.Xqedii_sytzb79
                  || var5[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(2013710359, 14)]
                     != (
                        this.Xqedii_2r92e6t() && var4.Xqedii_3w4yc84()
                           ? Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(1611574085, 30)
                           : Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-391112794, 20)
                     )) {
                  var2 = Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 22);
                  break;
               }
            }

            this.Xqedii_21hm206 = (var2 != 0);
         }
      }
   }

   public void handleQuit(UUID var1) {
      if (((1405018790 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         if (var1 != null) {
            this.Xqedii_2rr4o3m.remove(var1);
            this.Xqedii_1ebrd9y.remove(var1);
            this.Xqedii_1dxcxs6.remove(var1);
            this.Xqedii_1o6ffne.Xqedii_z4f1nd9(var1);
         }
      }
   }

   public boolean isBedrockPlayer(Player var1) {
      if ((-1343102607 * -1343102607 - -1343102607 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return this.Xqedii_ipfyrwr.Xqedii_30xe33f(var1);
      }
   }

   public boolean isActive() {
      if ((1382218138 * 1382218138 - 1382218138 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((this.Xqedii_1tms53a && this.Xqedii_ipfyrwr.Xqedii_1148524()
            ? Xqedii_1lbievo.Xqedii_3evul6ygeglfz(1574968352, 8)
            : Xqedii_1lbievo.Xqedii_3evul6ygeglfz(1954641792, 26)) != 0);
      }
   }

   public BedrockUiService.OpenResult tryOpen(Player var1, String var2, boolean var3) {
      if ((-1088239042 * -1088239042 - -1088239042 & 1) != 0) {
         throw new IllegalStateException();
      } else if (!this.Xqedii_1tms53a || var1 == null || var2 == null || var2.isBlank()) {
         return BedrockUiService.OpenResult.NOT_HANDLED;
      } else if (!this.Xqedii_ipfyrwr.Xqedii_30xe33f(var1)) {
         return BedrockUiService.OpenResult.NOT_HANDLED;
      } else if (var3) {
         return BedrockUiService.OpenResult.REFUSED_HUD;
      } else if (!this.Xqedii_ipfyrwr.Xqedii_1148524()) {
         return BedrockUiService.OpenResult.FAILED;
      } else {
         return this.openPage(var1, var2) ? BedrockUiService.OpenResult.OPENED : BedrockUiService.OpenResult.FAILED;
      }
   }

   public boolean openPage(Player var1, String var2) {
      if ((-423239824 * -423239824 + -423239824 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null && var2 != null) {
         String var3 = var2.trim().toLowerCase(Locale.ROOT);
         Xqedii_2kb1ns6 var4 = this.Xqedii_2vtp3jf.get(var3);
         if (var4 == null) {
            this.Xqedii_5tfok30.getLogger().warning(Xqedii_2xh49ei7dq7tk(var2));
            return (Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 25) != 0);
         } else {
            Xqedii_23ssj1h var5 = new Xqedii_23ssj1h(var3);

            for (Xqedii_v7p1hqx var7 : var4.Xqedii_ggpkjmc) {
               if (var7.Xqedii_3mu4xzh) {
                  var5.Xqedii_3czh0z5.add(this.Xqedii_2rkzwr5(var7));
               }
            }

            this.Xqedii_1ebrd9y.put(var1.getUniqueId(), var5);
            this.Xqedii_1o6ffne.Xqedii_1anj7is(var1, var4.Xqedii_du21gc3);
            this.Xqedii_1o6ffne.Xqedii_1smopsn(var1, var4.Xqedii_2gjw6qt);
            return this.Xqedii_300821r(var1, var4, var5);
         }
      } else {
         return (Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 10) != 0);
      }
   }

   public boolean Xqedii_3isfvhk(Player var1, String var2) {
      if (((-824127543 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_23ssj1h var3 = this.Xqedii_1ebrd9y.get(var1.getUniqueId());
         Xqedii_2kb1ns6 var4 = this.Xqedii_2vtp3jf.get(var2);
         return var3 != null && var4 != null && var3.Xqedii_3fdq4vw.equals(var2) ? this.Xqedii_300821r(var1, var4, var3) : this.openPage(var1, var2);
      }
   }

   public boolean Xqedii_300821r(Player var1, Xqedii_2kb1ns6 var2, Xqedii_23ssj1h var3) {
      if ((366433550 * 366433550 + 366433550 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_2e0aui3(
            Xqedii_3ucr8fy9dc8e1(
               var2.Xqedii_3j6s705,
               var1.getName(),
               String.valueOf(var1.getUniqueId()),
               Xqedii_2pn9w2s.Xqedii_2q861q9(var1.getUniqueId()),
               var2.Xqedii_2kz3xjg,
               var2.Xqedii_b0fv9fz,
               var2.Xqedii_1ei4hmh,
               var2.Xqedii_1jqflox,
               var2.Xqedii_sytzb79 >= 0
                  ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("TA2fvDIzQdUUziRNDx3tOuQCV1+Ugfyc2WrBEnvKYqTiArjuo4uxvA==")
                  : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg=="),
               this.Xqedii_21hm206
            )
         );
         ArrayList var4 = new ArrayList(var2.Xqedii_1mt8sib());

         for (int var5 = Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-267481042, 15); var5 < var2.Xqedii_b0fv9fz; var5++) {
            Xqedii_v7p1hqx var6 = var2.Xqedii_3fy8fnn(var5);
            var4.add(
               Xqedii_3nmychi.text(
                  var6 != null && !var6.Xqedii_221jxyb()
                     ? this.Xqedii_1o6ffne.Xqedii_1kcsuri(var1, var6.Xqedii_2zm1if4)
                     : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==")
               )
            );
         }

         Xqedii_v7p1hqx[] var9 = var2.Xqedii_3kvxaef();

         for (int var10 = Xqedii_1lbievo.Xqedii_3evul6ygeglfz(2004907137, 10); var10 < var9.length; var10++) {
            var4.add(Xqedii_3nmychi.text(this.Xqedii_3nocluy(var1, var9[var10], var2.Xqedii_b0fv9fz + var10)));
         }

         for (Xqedii_v7p1hqx var7 : this.Xqedii_1xpy8cc(var2)) {
            String var8 = this.Xqedii_i0ikycg(var1, var7);
            this.Xqedii_2e0aui3(
               Xqedii_19gx74qk3qvq5(
                  var7.Xqedii_3rdwuj0,
                  var7.Xqedii_272zqk2,
                  var8.isBlank() ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("szMikWKpc4+I5Nt41Ngq0AyNORFQzoalKGIoo6Y9jdAB7JJUP2XcgC+n") : var8
               )
            );
            this.Xqedii_1v03g02(var8);
            var4.add(Xqedii_3nmychi.image(var8));
         }

         if (var2.Xqedii_sytzb79 >= 0) {
            String var12 = this.Xqedii_3jte3ed(var1, var2, var3);
            this.Xqedii_2e0aui3(Xqedii_3pxhxylmqlsyb(var2.Xqedii_sytzb79, var12));
            var4.add(Xqedii_3nmychi.text(var12));
         }

         if (var4.size() != var2.Xqedii_1mt8sib()) {
            this.Xqedii_5tfok30.getLogger().warning(Xqedii_38mn3zt2qaw8w(var2.Xqedii_3j6s705, var2.Xqedii_1mt8sib(), var4.size()));
         }

         String var13 = var2.Xqedii_3j6s705.toLowerCase(Locale.ROOT);
         this.Xqedii_2rr4o3m.put(var1.getUniqueId(), var13);
         this.Xqedii_1dxcxs6.computeIfAbsent(var1.getUniqueId(), var0 -> {
            if ((-283071289 ^ -283071289) != 0) {
               throw new IllegalStateException();
            } else {
               return new AtomicInteger();
            }
         }).incrementAndGet();
         boolean var14 = this.Xqedii_ipfyrwr
            .Xqedii_5mpbqew(
               var1.getUniqueId(), var2.Xqedii_s8xbs95(), Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg=="), var4, var3x -> {
                  if ((1611075891 * (1611075891 + 1) & 1) != 0) {
                     throw new IllegalStateException();
                  } else {
                     this.Xqedii_2n8p825(var1, var13, var3x);
                  }
               }, () -> {
                  if (((-53024413 << 1 | 1) & 1) == 0) {
                     throw new IllegalStateException();
                  } else {
                     this.Xqedii_29z68m1(var1, var13);
                  }
               }
            );
         if (!var14) {
            this.Xqedii_2rr4o3m.remove(var1.getUniqueId());
         }

         return var14;
      }
   }

   public List<Xqedii_v7p1hqx> Xqedii_1xpy8cc(Xqedii_2kb1ns6 var1) {
      if (((-1557786710 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_v7p1hqx[] var2 = new Xqedii_v7p1hqx[var1.Xqedii_1jqflox];
         int var3 = var1.Xqedii_b0fv9fz + var1.Xqedii_1ei4hmh;

         for (Xqedii_v7p1hqx var5 : var1.Xqedii_ggpkjmc) {
            int var6 = var5.Xqedii_3rdwuj0 - var3;
            if (var5.Xqedii_3rdwuj0 >= 0 && var6 >= 0 && var6 < var2.length) {
               var2[var6] = var5;
            }
         }

         ArrayList var9 = new ArrayList(var2.length);
         Xqedii_v7p1hqx[] var10 = var2;
         int var11 = var2.length;

         for (int var7 = Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-373675003, 10); var7 < var11; var7++) {
            Xqedii_v7p1hqx var8 = var10[var7];
            if (var8 != null) {
               var9.add(var8);
            }
         }

         if (var9.size() != var1.Xqedii_1jqflox) {
            this.Xqedii_5tfok30.getLogger().warning(Xqedii_d0tme6a1ija6(var1.Xqedii_3j6s705, var1.Xqedii_1jqflox, var9.size()));
         }

         return var9;
      }
   }

   public void Xqedii_29z68m1(Player var1, String var2) {
      if ((1084555400 * (1084555400 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         if (var2.equals(this.Xqedii_2rr4o3m.get(var1.getUniqueId()))) {
            this.Xqedii_2rr4o3m.remove(var1.getUniqueId());
         }
      }
   }

   public String Xqedii_3nocluy(Player var1, Xqedii_v7p1hqx var2, int var3) {
      if (((-215118995 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var2 == null) {
         return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
      } else if (var2.Xqedii_210a5o9()) {
         String var6 = this.Xqedii_1o6ffne.Xqedii_1kcsuri(var1, var2.Xqedii_36gp88t);
         String var5 = this.Xqedii_3pmue8y.Xqedii_eleols7(var1.getUniqueId(), var6, var2.Xqedii_3gjia31);
         this.Xqedii_2e0aui3(Xqedii_827rxo8wshfm(var3, var6, var5));
         return var5;
      } else if (var2.Xqedii_221jxyb()) {
         String var4 = this.Xqedii_zxozaqu(var1, var2);
         if (var3 == var2.Xqedii_2hjfin7) {
            return var4.isEmpty()
               ? Xqedii_1nc5hgr.Xqedii_2jht1ka(this.Xqedii_1o6ffne.Xqedii_1kcsuri(var1, var2.Xqedii_19ezjp5))
               : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
         } else {
            return this.Xqedii_29jnb42(var2, var4);
         }
      } else {
         return Xqedii_1nc5hgr.Xqedii_2jht1ka(this.Xqedii_1o6ffne.Xqedii_1kcsuri(var1, var2.Xqedii_2zm1if4));
      }
   }

   public String Xqedii_zxozaqu(Player var1, Xqedii_v7p1hqx var2) {
      if (((-2036082544 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (this.Xqedii_1o6ffne.Xqedii_1fgvpt1(var1, var2.Xqedii_2ub1lix)) {
         return this.Xqedii_1o6ffne.Xqedii_15lmxhq(var1, var2.Xqedii_2ub1lix);
      } else if (this.Xqedii_1o6ffne.Xqedii_1fgvpt1(var1, var2.Xqedii_2okaiwk)) {
         return this.Xqedii_1o6ffne.Xqedii_15lmxhq(var1, var2.Xqedii_2okaiwk);
      } else {
         return var2.Xqedii_23ednv5.isBlank()
            ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==")
            : this.Xqedii_1o6ffne.Xqedii_1kcsuri(var1, var2.Xqedii_23ednv5);
      }
   }

   public String Xqedii_29jnb42(Xqedii_v7p1hqx var1, String var2) {
      if ((625259813 * 625259813 + 625259813 & 1) != 0) {
         throw new IllegalStateException();
      } else if (var2 != null && !var2.isEmpty()) {
         double var3 = Math.max(
            Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(-6998019094087853313L, 20)),
            var1.Xqedii_wnnmnvv * Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(3528130855464010936L, 37))
         );
         int var5 = Math.max(Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-2144383535, 29), (int)Math.floor(var1.Xqedii_3b8346h / var3));
         int var6 = var2.codePointCount(Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1171520023, 18), var2.length());
         return var6 <= var5 ? var2 : var2.substring(var2.offsetByCodePoints(Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 2), var6 - var5));
      } else {
         return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
      }
   }

   public String Xqedii_2rkzwr5(Xqedii_v7p1hqx var1) {
      if (((962670961 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return var1.Xqedii_11ww0b3 != null && !var1.Xqedii_11ww0b3.isBlank() ? var1.Xqedii_11ww0b3 : Xqedii_3p81wlur2vacl(var1.Xqedii_3mzx85f);
      }
   }

   public void Xqedii_3k25vjd(Player var1, String var2, boolean var3) {
      if (((2087907041 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_23ssj1h var4 = this.Xqedii_1ebrd9y.get(var1.getUniqueId());
         Xqedii_2kb1ns6 var5 = var4 == null ? null : this.Xqedii_2vtp3jf.get(var4.Xqedii_3fdq4vw);
         if (var5 != null && var2 != null && !var2.isBlank()) {
            ArrayList var6 = new ArrayList();

            for (Xqedii_v7p1hqx var8 : var5.Xqedii_ggpkjmc) {
               if (var2.trim().equals(var8.Xqedii_oruxzxc)) {
                  var6.add(this.Xqedii_2rkzwr5(var8));
               }
            }

            for (Xqedii_v7p1hqx var14 : var5.Xqedii_ggpkjmc) {
               String var9 = this.Xqedii_2rkzwr5(var14);
               int var10 = Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1564451173, 22);

               for (String var12 : (Iterable<String>) (var6)) {

                  if (var9.equals(var12)
                     || !var12.startsWith(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("QfdXUn7Z6QsaErOyuP9L+0OmFm78HW24vzBHmoDm"))
                        && var9.startsWith(Xqedii_1cy184kbwidxg(var12))) {
                     var10 = Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832260, 0);
                     break;
                  }
               }

               if (var10 != 0) {
                  if (var3) {
                     var4.Xqedii_3czh0z5.remove(var9);
                  } else {
                     var4.Xqedii_3czh0z5.add(var9);
                  }
               }
            }
         }
      }
   }

   public String Xqedii_3jte3ed(Player var1, Xqedii_2kb1ns6 var2, Xqedii_23ssj1h var3) {
      if ((-1197684119 * -1197684119 + -1197684119 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         LinkedHashMap var4 = new LinkedHashMap();
         StringBuilder var5 = new StringBuilder(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("OKHwq98FtBl1bGUPgSOt/3k5jpvXT49IPyS26Z52"));

         for (Xqedii_v7p1hqx var7 : var2.Xqedii_ggpkjmc) {
            if (var7.Xqedii_33uodds) {
               boolean var8 = var3.Xqedii_3czh0z5.contains(this.Xqedii_2rkzwr5(var7));
               if (!var8 && !var7.Xqedii_3mv9hk2.isBlank()) {
                  var8 = !this.Xqedii_24ra4ev(var1, var7.Xqedii_3mv9hk2, var7.Xqedii_1q4a4o6);
               }

               if (!var8 && !var7.Xqedii_2v720ms.isBlank()) {
                  var8 = !this.Xqedii_35gw4y2(var1, var2, var7, var4);
               }

               if (var8) {
                  var5.append(var7.Xqedii_8os42uu(), Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537799493, 15), var7.Xqedii_8os42uu().length());
               }
            }
         }

         return var5.toString();
      }
   }

   public boolean Xqedii_35gw4y2(Player var1, Xqedii_2kb1ns6 var2, Xqedii_v7p1hqx var3, Map<String, String> var4) {
      if ((947981581 * 947981581 + 947981581 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_1tuv3qh var5 = var2.Xqedii_i5hlcvn.get(var3.Xqedii_2v720ms);
         if (var5 == null) {
            return (Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537307973, 19) != 0);
         } else {
            String var6 = var4.computeIfAbsent(var5.Xqedii_rr7d9qc, var4x -> {
               if (((-949756374 << 1 | 1) & 1) == 0) {
                  throw new IllegalStateException();
               } else {
                  return this.Xqedii_1ubtw9b(var1, var2, var5);
               }
            });
            return var5.Xqedii_2j1j3tr(var5.Xqedii_21whwet.get(var3.Xqedii_1tl99gi), var6);
         }
      }
   }

   public String Xqedii_1ubtw9b(Player var1, Xqedii_2kb1ns6 var2, Xqedii_1tuv3qh var3) {
      if (((-1447798606 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_v7p1hqx var4 = var2.Xqedii_vxmqvhm(var3.input);
         if (var4 != null) {
            return this.Xqedii_zxozaqu(var1, var4);
         } else {
            return this.Xqedii_1o6ffne.Xqedii_1fgvpt1(var1, var3.input)
               ? this.Xqedii_1o6ffne.Xqedii_15lmxhq(var1, var3.input)
               : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
         }
      }
   }

   public boolean Xqedii_24ra4ev(Player var1, String var2, boolean var3) {
      if ((934454093 ^ 934454093) != 0) {
         throw new IllegalStateException();
      } else {
         int var4 = var2.lastIndexOf(Xqedii_1lbievo.Xqedii_321mxj7tclmi0(928514237, 15));
         if (var4 >= 0 && var4 != var2.length() - Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-780128435, 21)) {
            String var5 = var2.substring(Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1171520023, 18), var4 + Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 30));
            Matcher var6 = Xqedii_ad4hxn1.matcher(var2.substring(var4 + Xqedii_1lbievo.Xqedii_321mxj7tclmi0(805693882, 26)).trim());
            if (!var6.matches()) {
               return (Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 28) != 0);
            } else {
               String var7 = var6.group(Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1946060178, 24));
               double var8 = Double.parseDouble(var6.group(Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851894349, 16)));
               double var10 = Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_qbzqr1exc6v5(-3118136273250326848L, 49));
               double var12 = Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_27dd9ncy36b8z(-7574618121084094140L, 10));
               double var14 = Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_qbzqr1exc6v5(5450051849123425293L, 53));
               String var16 = var5.substring(
                  Xqedii_1lbievo.Xqedii_321mxj7tclmi0(805693882, 26), var5.length() - Xqedii_1lbievo.Xqedii_321mxj7tclmi0(12400454, 31)
               );
               int var17 = var16.indexOf(Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1207184523, 27));
               if (var17 >= 0) {
                  String[] var18 = var16.substring(var17 + Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1072191768, 28))
                     .split(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("jgNBwZLNSO5eNyoH06mdQ2Nf0q7mKRtRCIs74xp/"));
                  int var19 = var18.length;

                  for (int var20 = Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 23); var20 < var19; var20++) {
                     String var21 = var18[var20];
                     String[] var22 = var21.split(
                        Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("hpvreRKYHRdcnWiEUTQx9ziNvwbaqLhFOnXUebOg"), Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851894349, 16)
                     );
                     if (var22.length == Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537848645, 13)) {
                        double var23 = Xqedii_2ms2rft(
                           var22[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(787484176, 7)].trim(),
                           Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(-6998019085680384513L, 34))
                        );
                        String var25 = var22[Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 19)].trim().toUpperCase(Locale.ROOT);
                        int var26 = Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851796044, 15);
                        switch (var25.hashCode()) {
                           case -2032180703:
                              if (var25.equals(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("M+7XUlsOMtQ/mJu7sJ0VGaM2oA6nzUPEV0Oiz2h4MWe6XjxoDjTuoquE"))) {
                                 var26 = Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(542026565, 21);
                              }
                              break;
                           case 76100:
                              if (var25.equals(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("eljUURx71QDs7fs6x5ZARpOaZgBrK5WrPvQ2nayJluKGYQ=="))) {
                                 var26 = Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1560256869, 22);
                              }
                              break;
                           case 76338:
                              if (var25.equals(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("JNXLgKSWY2Ofun0wDeF71A+0jLOmD/7EMkCc5k5vOFfQ/A=="))) {
                                 var26 = Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-2139848329, 18);
                              }
                        }

                        switch (var26) {
                           case 0:
                              var10 = var23;
                              break;
                           case 1:
                              var12 = var23;
                              break;
                           case 2:
                              var14 = var23;
                        }
                     }
                  }

                  var5 = Xqedii_15bvy7jgfagte(var16.substring(Xqedii_1lbievo.Xqedii_3evul6ygeglfz(98435538, 4), var17));
               }

               double var27 = Xqedii_2ms2rft(this.Xqedii_1o6ffne.Xqedii_1kcsuri(var1, var5).trim(), var14);
               if (!Double.isFinite(var27)) {
                  return (Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 6) != 0);
               } else {
                  if (Double.isFinite(var10)) {
                     var27 = Math.max(var10, var27);
                  }

                  if (Double.isFinite(var12)) {
                     var27 = Math.min(var12, var27);
                  }

                  int var29 = Xqedii_1lbievo.Xqedii_321mxj7tclmi0(1342952308, 27);
                  switch (var7.hashCode()) {
                     case 1921:
                        if (var7.equals(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0XEwY9UllghFeT9SAjPgSDUrwQrWCH/yE/ASoaOnx5U="))) {
                           var29 = Xqedii_1lbievo.Xqedii_321mxj7tclmi0(1182842167, 23);
                        }
                        break;
                     case 1951:
                        if (var7.equals(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("uCwE1WEwSZuPxhS9/uWJsowEX7EhQiPtM21KimvoCC8="))) {
                           var29 = Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-2122874760, 2);
                        }
                        break;
                     case 1953:
                        if (var7.equals(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("uUFqYjCeKQHOQBknrsRHflGeYRVj6JQmml+Q3oxxNlc="))) {
                           var29 = Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537897797, 16);
                        }
                        break;
                     case 1983:
                        if (var7.equals(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("dhHOQtIfTLMw2AvIktFhfb5m0rY3NapFKWCl3HqaqSE="))) {
                           var29 = Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 2);
                        }
                  }
                  int var28 = switch (var29) {
                     case 0, 1 -> var27 >= var8 - Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(-6775599303904614702L, 0))
                        ? Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 31)
                        : Xqedii_1lbievo.Xqedii_3evul6ygeglfz(393742152, 6);
                     case 2, 3 -> var27 <= var8 + Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_m201bjb6pkf2(-8262699376531632180L, 29))
                        ? Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537831749, 9)
                        : Xqedii_1lbievo.Xqedii_3evul6ygeglfz(1954641792, 26);
                     default -> Math.abs(var27 - var8) < Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_qbzqr1exc6v5(-3890463195323016172L, 6))
                        ? Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832269, 3)
                        : Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 26);
                  };
                  return (var3 == (var28 != 0));
               }
            }
         } else {
            return (Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-1070055237, 17) != 0);
         }
      }
   }

   public static double Xqedii_2ms2rft(String var0, double var1) {
      if ((1852853714 ^ 1852853714) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            return Double.parseDouble(var0);
         } catch (RuntimeException var4) {
            return var1;
         }
      }
   }

   public String Xqedii_i0ikycg(Player var1, Xqedii_v7p1hqx var2) {
      if ((-1535798700 ^ -1535798700) != 0) {
         throw new IllegalStateException();
      } else if (Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("3qL6tzTjhpYqvY8jrQRyJUlj7qDaMWaBaUyaZ784FUURpmRg").equals(var2.Xqedii_272zqk2)) {
         return this.Xqedii_2rz7wnm(var1, var2.Xqedii_687jy1w, this.Xqedii_12acaqj());
      } else {
         return !this.Xqedii_vuzthgm(
               Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("zVSSeROy1Q7JCqEg+VQ6VBxU7HIVmcfifmufwkJGjX6rFH/yUNfwP41Bb/wsjxron6lPHYsLJGa2AMUKjdRXwPly"),
               (Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-2122874768, 2) != 0)
            )
            ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==")
            : this.Xqedii_2rz7wnm(var1, var2.Xqedii_20r4t4p, this.Xqedii_kpr1s2c());
      }
   }

   public String Xqedii_2rz7wnm(Player var1, String var2, String var3) {
      if ((2008983004 * 2008983004 - 2008983004 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var4 = this.Xqedii_1o6ffne
            .Xqedii_1kcsuri(var1, var2 == null ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==") : var2)
            .trim();
         if (var4.isBlank()) {
            var4 = var1.getName();
         }

         Player var5 = var4.equalsIgnoreCase(var1.getName()) ? var1 : Bukkit.getPlayerExact(var4);
         UUID var6 = var5 != null ? var5.getUniqueId() : Xqedii_37a339x(var4);
         if (var5 == null && var6 != null) {
            var5 = Bukkit.getPlayer(var6);
         }

         String var7 = var5 != null ? var5.getName() : var4;
         String var8 = var5 == null ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==") : Xqedii_d5a1c17(var5);
         String var9 = var6 != null && Xqedii_2pn9w2s.Xqedii_2q861q9(var6)
            ? Long.toUnsignedString(var6.getLeastSignificantBits())
            : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
         String var10 = var7;

         while (
            !var10.isEmpty()
               && !Character.isLetterOrDigit(var10.charAt(Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 31)))
               && var10.charAt(Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 5)) != Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1682276258, 14)
         ) {
            var10 = var10.substring(Xqedii_1lbievo.Xqedii_3evul6ygeglfz(1574968352, 8));
         }

         String var11 = var6 != null && !Xqedii_2pn9w2s.Xqedii_2q861q9(var6)
            ? var6.toString()
            : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
         String var12 = !var8.isBlank() ? var8 : (!var11.isBlank() ? var11 : var10);
         String var13 = var3.replace(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("q8OLMqUWay0FpBlDJ0K2yqzb+ALzw2iCduWZBML7fgWwNldy"), var12)
            .replace(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("ERnf8/NsLHg5gMD73Fd2Mifx9XzAumy8t6kY8y0GlS7zE9A7KLuNs+YNCexLHQ=="), var8.isBlank() ? var12 : var8)
            .replace(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("u8GzihbupoQYG37wb+7pPqzHouQNNJER4J2VY98xSd1n3FO8ODe9Id0RY3ioRZMOE/RGgvkSpBCNeA=="), var10)
            .replace(
               Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("tlH4gCWjh31lUSFKFqtucS5sp20cqh5CQgyOEMmVA+fWRbVoGzj70fXaGXUw2xH1ioV79+PsPo9MZg=="),
               (var11.isBlank() ? var12 : var11)
                  .replace(
                     Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("/jGT/iuNoKLt2cfgCpHFcjWVgDItNOR2SNGt9LMJ"),
                     Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==")
                  )
            )
            .replace(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("NBbAImIvZ3Nq+tDXSWWWtkbPeaOX0W0Wk4Ubsqbeo/PYhen34s++6w=="), var11.isBlank() ? var12 : var11)
            .replace(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("+whcfsZQHw+4pcH+VUXrpTYPtw9qMl0fDo8nrKFD34W704G1L8OUbw=="), var9)
            .replace(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Lhi1+wigvbk+VwDsCjQD3NoxM4wDAajiF5T2+ItK29ZQYQz9yeyUkw=="), var7);
         this.Xqedii_2e0aui3(Xqedii_354wtt6digpvu(var2, var7, var8, var11, var9, var13));
         return var13;
      }
   }

   public static UUID Xqedii_37a339x(String var0) {
      if ((673552223 * (673552223 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            return var0.length() >= Xqedii_1lbievo.Xqedii_3evul6ygeglfz(1574976800, 8)
               ? UUID.fromString(
                  var0.contains(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("/jGT/iuNoKLt2cfgCpHFcjWVgDItNOR2SNGt9LMJ"))
                     ? var0
                     : var0.replaceFirst(
                        Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                           "vIMQJAJlmpjxiFOqTjtOChcv8vIF2czhtgBMZS8LnBjXq+I0kRrRpnpxO32YWt1L/yHHUfj4I6e/BIcJ3n3lPMEo0LbrgNg3depbo3/oKCUb5DSDvS3NoGt3cHYQCtMoV80eKWftg7sHeJEUUnQpZ7Ykwe+mnSRFi+LFHjpgqeWbffu8WrvLht9M3UID+T9oVw/w9Gpn2uYizTqmLeRDjBbJqJcWFwLFwrwytMOM"
                        ),
                        Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("3sjhBtWq5uh6DNXw89nuCWwvxJtPj6ivLqkegjsBbMBLN6lSJmMiv3YC/AFZrtsCqAY9xCEM+HQ=")
                     )
               )
               : null;
         } catch (RuntimeException var2) {
            return null;
         }
      }
   }

   public static String Xqedii_d5a1c17(Player var0) {
      if ((104731189 ^ 104731189) != 0) {
         throw new IllegalStateException();
      } else {
         try {
            PlayerProfile var1 = Xqedii_38o2zes.Xqedii_a93o0le(var0);
            PlayerTextures var2 = var1 == null ? null : var1.getTextures();
            URL var3 = var2 == null ? null : var2.getSkin();
            if (var3 == null) {
               return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
            } else {
               String var4 = var3.getPath();
               int var5 = var4.lastIndexOf(Xqedii_1lbievo.Xqedii_3evul6ygeglfz(105122748, 21));
               String var6 = var5 >= 0 ? var4.substring(var5 + Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537307973, 19)) : var4;
               return var6.matches(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("fHy5tl/FN1l8jTCtGkmtCVqiAYVdLr8mw+ePBfhTBJ4GbHZ9TH++YPJRgAmC6KnYEES7BmWyHY7Azpep"))
                  ? var6
                  : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
            }
         } catch (Throwable var7) {
            return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
         }
      }
   }

   public void Xqedii_1v03g02(String var1) {
      if ((-1328849994 * -1328849994 - -1328849994 & 1) != 0) {
         throw new IllegalStateException();
      } else if (this.Xqedii_337zgsp
         && var1 != null
         && !var1.isBlank()
         && this.Xqedii_vuzthgm(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("smGPkWue3FEbvsmFEV5rplzxwstp3Isx8GAM2HefQevNwYdEm7ZCy6KyoQDYpfjppJ4+QQj8HSE+M4TmUfid16TY+aizSQ=="),
            (Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537307973, 19) != 0)
         )) {
         if (this.Xqedii_3b4x69h.putIfAbsent(var1, Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("P+4D3KGGfXapz5mtIkjcuOF2qKgbM7Z2VbU9vl4GYesOIVl5Iqj4wVhz")) == null) {
            Xqedii_38o2zes.Xqedii_u043a3q(
               this.Xqedii_5tfok30,
               () -> {
                  if ((-858544488 * (-858544488 + 1) & 1) != 0) {
                     throw new IllegalStateException();
                  } else {
                     String var2;
                     try {
                        HttpURLConnection var3 = (HttpURLConnection)URI.create(var1).toURL().openConnection();
                        var3.setRequestMethod(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("FyBxIxzC58NXByB2NUqqGQ9wOfAJ4pFpoUT6On09RPIzOA=="));
                        var3.setConnectTimeout(Xqedii_1lbievo.Xqedii_321mxj7tclmi0(637728004, 25));
                        var3.setReadTimeout(Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1918937603, 26));
                        var3.setInstanceFollowRedirects((Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-535027619, 16) != 0));
                        var3.setRequestProperty(
                           Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("luQ3cG03MTw+GbQTORNdBtp47+weBHpwllWSGYAk4+XcHYX2lDcwbJ3nnI3fqhu+"),
                           Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                              "yWERr8UjvAy0PothfDl7tVFH8K51LYQcnPB9bF/9Sa4Fepgp75ZCobdU7FqySGMMTj/98vumr8QEqwY75uLCrwohWq6vYDLpxx0RqA=="
                           )
                        );
                        int var4 = var3.getResponseCode();
                        String var5 = String.valueOf(var3.getContentType());
                        int var6 = var3.getContentLength();
                        String var7 = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("OP0xUFoitOR2cXerKEo6eAW/k3/owIhcxlSYUJMYvxuHk3Pg3jM2hKqz/smIzFmp8qCSmAC1");
                        if (var4 >= Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-2091613833, 18) && var4 < Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-1996296439, 27)) {
                           try (InputStream var8 = var3.getInputStream()) {
                              BufferedImage var9 = ImageIO.read(var8);
                              var7 = var9 == null
                                 ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                                    "M/fmGyi2bavJl9Oh0JWWvFr/fovXdCj58QgZDdSHQsWu0TCUBcObjaWdXFc9c39P01VbTf6cD+tTin7D5EAQCuLQWTodIxn6qefWM02V"
                                 )
                                 : Xqedii_2o0wpf0jk3kq2(var9.getWidth(), var9.getHeight());
                           }
                        }

                        var3.disconnect();
                        var2 = Xqedii_2xvrk4i2y4r7x(var4, var5, var6, var7);
                        if (var4 >= Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537869125, 9) && var4 < Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-1144888768, 9)) {
                           this.Xqedii_5tfok30.getLogger().info(Xqedii_3pzukx8coymm(var1, var2));
                        } else {
                           this.Xqedii_5tfok30.getLogger().warning(Xqedii_9bje5fgmf7xq(var1, var2));
                        }
                     } catch (Throwable var13) {
                        var2 = Xqedii_3r4wrljr32vsu(String.valueOf(var13));
                        this.Xqedii_5tfok30.getLogger().warning(Xqedii_30aqn9apwdqb(var1, String.valueOf(var13)));
                     }

                     this.Xqedii_3b4x69h.put(var1, var2);
                  }
               }
            );
         }
      }
   }

   public void Xqedii_2n8p825(Player var1, String var2, int var3) {
      if ((2015550496 * 2015550496 - 2015550496 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         this.Xqedii_2rr4o3m.remove(var1.getUniqueId());
         Xqedii_2kb1ns6 var4 = this.Xqedii_2vtp3jf.get(var2);
         this.Xqedii_2e0aui3(
            Xqedii_231ny943g1jco(
               var2,
               var1.getName(),
               var3,
               String.valueOf(var4 == null ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("xKD0cEW0WtyGSAYmGcwW5s8NPrRb0Y+uzh17E+xH") : var4.Xqedii_b0fv9fz)
            )
         );
         if (var4 != null && var3 >= 0 && var3 < var4.Xqedii_b0fv9fz) {
            Xqedii_v7p1hqx var5 = var4.Xqedii_3fy8fnn(var3);
            if (var5 != null) {
               int var6 = this.Xqedii_1gd6pmb(var1);
               if (var5.Xqedii_221jxyb()) {
                  this.Xqedii_mqop2nv(
                     var1,
                     var2,
                     var5.Xqedii_o5zonyl,
                     this.Xqedii_1c0bq4z(var4) ? var5 : null,
                     var5x -> {
                        if (((1330066704 << 1 | 1) & 1) == 0) {
                           throw new IllegalStateException();
                        } else {
                           List var6x;
                           if (Boolean.TRUE.equals(var5x)) {
                              var6x = Xqedii_too58w6.Xqedii_3r36mrz(
                                 var5.Xqedii_o5zonyl, Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("6L/jPIjo707rH0yWzsv1+IJc3LRpUIcsLcrwWqKWez7ZqFwt8ulx21faOeOzOQ==")
                              );
                              if (var6x.isEmpty()) {
                                 var6x = var5.Xqedii_3o2c6jz;
                              }
                           } else if (Boolean.FALSE.equals(var5x)) {
                              var6x = Xqedii_too58w6.Xqedii_3r36mrz(
                                 var5.Xqedii_o5zonyl, Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("X23MDehCUH3uJcvslcP2Ttd49y3/Axbmo9si/Qsi3QBWV0mNmQPSlfHxFT6N1g==")
                              );
                           } else {
                              var6x = List.of();
                           }

                           this.Xqedii_31t5ofi.run(var1, var6x, var4xx -> {
                              if ((1563360112 * 1563360112 - 1563360112 & 1) != 0) {
                                 throw new IllegalStateException();
                              } else {
                                 this.Xqedii_1goft0d(var1, var2, var4xx, var6);
                              }
                           });
                        }
                     }
                  );
               } else {
                  this.Xqedii_31t5ofi.run(var1, var5.Xqedii_3o2c6jz, var4x -> {
                     if (((724369538 << 1 | 1) & 1) == 0) {
                        throw new IllegalStateException();
                     } else {
                        this.Xqedii_1goft0d(var1, var2, var4x, var6);
                     }
                  });
               }
            }
         }
      }
   }

   public int Xqedii_1gd6pmb(Player var1) {
      if ((-1250355381 * -1250355381 + -1250355381 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         AtomicInteger var2 = this.Xqedii_1dxcxs6.get(var1.getUniqueId());
         return var2 == null ? Xqedii_1lbievo.Xqedii_321mxj7tclmi0(369292509, 25) : var2.get();
      }
   }

   public void Xqedii_1goft0d(Player var1, String var2, boolean var3, int var4) {
      if ((-606204045 * (-606204045 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (!var3
         && var1.isOnline()
         && this.Xqedii_vuzthgm(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("3CmWzQe9legdwsz+qw1MGSP15lJexLydWCRQ76x+ERI7EJIiIWIuCa4uCM3Ccg=="),
            (Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 23) != 0)
         )) {
         if (this.Xqedii_1gd6pmb(var1) == var4 && !this.Xqedii_2rr4o3m.containsKey(var1.getUniqueId())) {
            Xqedii_38o2zes.Xqedii_2sj63w1(this.Xqedii_5tfok30, var1, () -> {
               if ((-1665109294 * (-1665109294 + 1) & 1) != 0) {
                  throw new IllegalStateException();
               } else if (var1.isOnline() && this.Xqedii_1gd6pmb(var1) == var4 && !this.Xqedii_2rr4o3m.containsKey(var1.getUniqueId())) {
                  InventoryType var4x = var1.getOpenInventory().getType();
                  if (var4x == InventoryType.CRAFTING || var4x == InventoryType.CREATIVE) {
                     this.Xqedii_3isfvhk(var1, var2);
                  }
               }
            }, Xqedii_1lbievo.Xqedii_m201bjb6pkf2(-3443434210828826352L, 48));
         }
      }
   }

   public List<String> Xqedii_2fbvovw(Player var1, Xqedii_2kb1ns6 var2) {
      if ((1349502155 * 1349502155 - 1349502155 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var3 = new ArrayList();
         Xqedii_v7p1hqx[] var4 = var2.Xqedii_3kvxaef();

         for (int var5 = Xqedii_1lbievo.Xqedii_321mxj7tclmi0(1587258048, 6); var5 < var4.length; var5++) {
            var3.add(this.Xqedii_3nocluy(var1, var4[var5], var2.Xqedii_b0fv9fz + var5));
         }

         if (var2.Xqedii_sytzb79 >= 0) {
            Xqedii_23ssj1h var6 = this.Xqedii_1ebrd9y.get(var1.getUniqueId());
            var3.add(var6 == null ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("OKHwq98FtBl1bGUPgSOt/3k5jpvXT49IPyS26Z52") : this.Xqedii_3jte3ed(var1, var2, var6));
         }

         return var3;
      }
   }

   public void Xqedii_mqop2nv(Player var1, String var2, Map<String, Object> var3, Xqedii_v7p1hqx var4, Consumer<Boolean> var5) {
      if ((-308771897 ^ -308771897) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var10000 = new String[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(393742024, 6)];
         var10000[Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 27)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("AfbL6NMUPjiHozTuiIz/9azgL6yaUTcdDgSHXETSmPtiqVMUHOHPKqIDW0I="))
         );
         var10000[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832293, 5)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("CM0aOMZtvD/Np93QdJKJvzdW9wA1axlvL3lBGE+AiDB+gxql0UtK/L61"))
         );
         var10000[Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1868606029, 24)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("1L+8VHQG3Ju+9a1wwWHrT4uodzeoVOmRexu5TDUjs6E="))
         );
         var10000[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(196871108, 5)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("xbLaluHEoWIB0LHxfTQPdacMmK9c+JT9TFbuXw5jUHKR1WJWIHlhk2P5aEU="))
         );
         var10000[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(538880837, 18)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("jzDcHmUe9vqX8xvCxWOCqzgSxSAj5zNu1zObb18981oaqXdK"))
         );
         var10000[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537668421, 15)] = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
            "ohVCZzVson8iuEN3d87xp7wN/Caq3IVanPzLzRpEHTGVDu0wTyE="
         );
         String var6 = Xqedii_5m2bpnc(var10000).trim();
         var10000 = new String[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1494691817, 12)];
         var10000[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-1875972082, 31)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("xbLaluHEoWIB0LHxfTQPdacMmK9c+JT9TFbuXw5jUHKR1WJWIHlhk2P5aEU="))
         );
         var10000[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832263, 1)] = var6;
         String var7 = Xqedii_5m2bpnc(var10000).trim();
         String var19;
         if (this.Xqedii_1o6ffne.Xqedii_1fgvpt1(var1, var7)) {
            var19 = this.Xqedii_1o6ffne.Xqedii_15lmxhq(var1, var7);
         } else if (this.Xqedii_1o6ffne.Xqedii_1fgvpt1(var1, var6)) {
            var19 = this.Xqedii_1o6ffne.Xqedii_15lmxhq(var1, var6);
         } else {
            Xqedii_1nc5hgr var20 = this.Xqedii_1o6ffne;
            String[] var10002 = new String[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(927367357, 15)];
            var10002[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 12)] = Xqedii_2czjggr(
               var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("l8WgHun+TMdEhyqf531QsDo+uOhRTc+I7gBwXpoGzublhq45jnwZfNpE"))
            );
            var10002[Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 14)] = Xqedii_2czjggr(
               var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("3XODKHtT8y0Gmoi4uHovOvU54jc7TGoqPsGAey6L9cnW1GbtFcM="))
            );
            var19 = var20.Xqedii_1kcsuri(var1, Xqedii_5m2bpnc(var10002));
         }

         String var8 = var19;
         Xqedii_1nc5hgr var21 = this.Xqedii_1o6ffne;
         String[] var24 = new String[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-2011727865, 30)];
         var24[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1340627224, 28)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("DnbSIhGa5IFKCubvqSxn314Ic6+7aOF2xIsUGwVmeM+/5U/2/3A="))
         );
         var24[Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 6)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("RhfD+uJezmtirvAc9KvF9OspX9b3G1mDrsZQeTPtlZjaMDS6IT6MxA=="))
         );
         var24[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(2013775895, 14)] = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
            "bUbCNb8TuJNTG+V7xOIZFceKCpi5x7OvPXmq8dDmuvrqcQ0y+Oante5+WgWrJomD"
         );
         String var9 = Xqedii_1nc5hgr.Xqedii_2jht1ka(var21.Xqedii_1kcsuri(var1, Xqedii_5m2bpnc(var24)));
         Xqedii_1nc5hgr var22 = this.Xqedii_1o6ffne;
         var24 = new String[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(261107440, 23)];
         var24[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(49217769, 3)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("8BSjeG9zQOcDGGMHcERlrhtz6CNFJ7WY8U86v2UsM/18axeJGINjiSHlF2p41SuJpSk="))
         );
         var24[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1120451072, 7)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("BxMXaG3M9IZbArSspc4zGNNPCjPgCkaCp1JvzpmV31HnUz1V"))
         );
         String var10 = Xqedii_1nc5hgr.Xqedii_2jht1ka(var22.Xqedii_1kcsuri(var1, Xqedii_5m2bpnc(var24)));
         long var23 = Xqedii_1lbievo.Xqedii_27dd9ncy36b8z(-7030697945949873103L, 31);
         long var10001 = Xqedii_1lbievo.Xqedii_m201bjb6pkf2(-3019814370879289072L, 53);
         var24 = new String[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1120450816, 7)];
         var24[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-585760012, 17)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Lvkh8orhbRhCVzeC6sVR9xvl8KuxZZiJJtRO1sVT1T7iDWbxd6MnMxlsKGbwvXBM"))
         );
         var24[Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 31)] = Xqedii_2czjggr(
            var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("2vcZHXUk2SvZgC6nc7XiGgNwbZpyiIPJ9bLR/6Jp7hkynaQQMJetWJKym+5eQA=="))
         );
         var24[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(130553720, 22)] = String.valueOf(Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832225, 1));
         int var11 = (int)Math.max(
            var23,
            Math.min(
               var10001,
               Math.round(Xqedii_2ms2rft(Xqedii_5m2bpnc(var24), Double.longBitsToDouble(Xqedii_1lbievo.Xqedii_3mz0vn9f94ba7(-7015970820434565633L, 56))))
            )
         );
         InputFilter var12 = InputFilter.fromId(
            Xqedii_2czjggr(var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("XTLmD0fNIX3h01lgEJmyH91yfrnQtveP2dGm4aQaKiuz7iStUMz2Hg==")))
         );
         boolean var13 = (!Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("jht59pqgVRJUzFbwB3kkyUfmWgU7MsyZfv9TlSrRoAO2jueQugw=")
               .equalsIgnoreCase(
                  Xqedii_2czjggr(var3.get(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("7zX9zt5RshMeCQ8Xp6/6IOynrtJ2HSzjEcVw7CuZ6wpy/9LUL2swtb58+klaGdzLg5g="))).trim()
               )
            ? Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537701189, 17)
            : Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1683832738, 14)) != 0;
         if (!this.Xqedii_ipfyrwr.Xqedii_2m5qhgo()) {
            this.Xqedii_5tfok30.getLogger().warning(Xqedii_3p1q1jq8wl06(var6));
            Xqedii_38o2zes.Xqedii_1avvt9e(this.Xqedii_5tfok30, var1, () -> {
               if (((-1925701794 | 1) & 1) == 0) {
                  throw new IllegalStateException();
               } else {
                  var5.accept(null);
               }
            });
         } else {
            Xqedii_2kb1ns6 var14 = var4 != null && var4.Xqedii_3ma30bi >= 0 ? this.Xqedii_2vtp3jf.get(var2) : null;
            String var15 = var14 == null ? var9 : Xqedii_1dnp2tdnfrx79(var14.Xqedii_33bg1mh(), Xqedii_2kb1ns6.Xqedii_2jnvi3m(var4.Xqedii_3ma30bi));
            List var16 = var14 == null ? List.of() : this.Xqedii_2fbvovw(var1, var14);
            if (var14 != null) {
               this.Xqedii_2e0aui3(Xqedii_bmv7npo7ycmz(var6, var2, var4.Xqedii_3ma30bi, var16.size()));
            }

            boolean var17 = this.Xqedii_ipfyrwr
               .Xqedii_34o28ib(
                  var1.getUniqueId(), var15, Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg=="), var10, var8, var16, var10x -> {
                     if (((-1384204176 | 1) & 1) == 0) {
                        throw new IllegalStateException();
                     } else {
                        Xqedii_38o2zes.Xqedii_1avvt9e(this.Xqedii_5tfok30, var1, () -> {
                           if (((-1697775936 << 1 | 1) & 1) == 0) {
                              throw new IllegalStateException();
                           } else {
                              String var11x = Xqedii_2omqwyq(var12.sanitize(var10x), var11);
                              if (!var13 && var11x.isBlank()) {
                                 var5.accept(Boolean.valueOf((Xqedii_1lbievo.Xqedii_321mxj7tclmi0(1951927251, 19) != 0)));
                              } else {
                                 UltimateUIInputSubmitEvent var12x = new UltimateUIInputSubmitEvent(var1, var2, var6, var7, var8, var11x);
                                 Bukkit.getPluginManager().callEvent(var12x);
                                 String var13x = Xqedii_2omqwyq(var12.sanitize(var12x.getValue()), var11);
                                 if (!var12x.isCancelled() && (var13 || !var13x.isBlank())) {
                                    this.Xqedii_1o6ffne.Xqedii_1dxyjeo(var1, var6, var13x);
                                    if (!var7.equals(var6)) {
                                       this.Xqedii_1o6ffne.Xqedii_1dxyjeo(var1, var7, var13x);
                                    }

                                    var5.accept(Boolean.valueOf((Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-267513810, 15) != 0)));
                                 } else {
                                    var5.accept(null);
                                 }
                              }
                           }
                        });
                     }
                  }, () -> {
                     if (((-15106675 << 1 | 1) & 1) == 0) {
                        throw new IllegalStateException();
                     } else {
                        Xqedii_38o2zes.Xqedii_1avvt9e(this.Xqedii_5tfok30, var1, () -> {
                           if (((1311411132 | 1) & 1) == 0) {
                              throw new IllegalStateException();
                           } else {
                              var5.accept(Boolean.valueOf((Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 10) != 0)));
                           }
                        });
                     }
                  }
               );
            if (!var17) {
               Xqedii_38o2zes.Xqedii_1avvt9e(this.Xqedii_5tfok30, var1, () -> {
                  if (((969190013 << 1 | 1) & 1) == 0) {
                     throw new IllegalStateException();
                  } else {
                     var5.accept(null);
                  }
               });
            }
         }
      }
   }

   public static String Xqedii_2omqwyq(String var0, int var1) {
      if ((671839163 ^ 671839163) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = var0 == null ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==") : var0;
         return var2.codePointCount(Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 25), var2.length()) <= var1
            ? var2
            : var2.substring(
               Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 23), var2.offsetByCodePoints(Xqedii_1lbievo.Xqedii_3evul6ygeglfz(61082556, 21), var1)
            );
      }
   }

   public static String Xqedii_2czjggr(Object var0) {
      if ((532632664 * 532632664 + 532632664 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return var0 == null ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==") : String.valueOf(var0);
      }
   }

   public static String Xqedii_5m2bpnc(String... var0) {
      if ((-1819366472 * (-1819366472 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String[] var1 = var0;
         int var2 = var0.length;

         for (int var3 = Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 4); var3 < var2; var3++) {
            String var4 = var1[var3];
            if (var4 != null && !var4.isBlank()) {
               return var4;
            }
         }

         return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
      }
   }

   public void closeScreen(Player var1) {
      if ((-1378901572 * (-1378901572 + 1) & 1) != 0) {
         throw new IllegalStateException();
      } else if (var1 != null) {
         this.Xqedii_2rr4o3m.remove(var1.getUniqueId());
         this.Xqedii_1ebrd9y.remove(var1.getUniqueId());
         this.Xqedii_1o6ffne.Xqedii_3czb6zz(var1.getUniqueId());
         this.Xqedii_ipfyrwr.Xqedii_3qme994(var1.getUniqueId());
      }
   }

   public boolean hasOpenScreen(Player var1) {
      if ((-1869676980 * -1869676980 - -1869676980 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return ((var1 != null && this.Xqedii_2rr4o3m.containsKey(var1.getUniqueId())
            ? Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828813, 8)
            : Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 22)) != 0);
      }
   }

   public String openScreenName(Player var1) {
      if (((-2130538763 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         return var1 == null ? null : this.Xqedii_2rr4o3m.get(var1.getUniqueId());
      }
   }

   public void buildPackAsync(Consumer<BedrockUiService.BuildReport> var1) {
      if ((-1848121085 ^ -1848121085) != 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_38o2zes.Xqedii_u043a3q(this.Xqedii_5tfok30, () -> {
            if ((-1645311314 ^ -1645311314) != 0) {
               throw new IllegalStateException();
            } else {
               BedrockUiService.BuildReport var2 = this.buildPack();
               Xqedii_38o2zes.Xqedii_34mipix(this.Xqedii_5tfok30, () -> {
                  if ((-1462088824 * (-1462088824 + 1) & 1) != 0) {
                     throw new IllegalStateException();
                  } else {
                     if (var1 != null) {
                        var1.accept(var2);
                     }
                  }
               });
            }
         });
      }
   }

   public BedrockUiService.BuildReport buildPack() {
      if (((-1320970264 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var1 = new ArrayList();

         try {
            List var2 = this.Xqedii_2y67ssd().Xqedii_2s9ccrs(var1);
            if (var2.isEmpty()) {
               return new BedrockUiService.BuildReport(
                  (Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 3) != 0),
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                     "5UEsGzj7WfQiuvGCONby5KAVPuzCC/Gv7orqiMT4xATWXzC34hXCxSH27pw7ImLnQtg/24jC29yTwXKc1uCbydX83F9AMUBt271/Mhp+gyGVop8HfJ8FcufKoXIKt2VxJ06984oklr1/t+S8RiXrnsJ27gw="
                  ),
                  var1,
                  null
               );
            } else {
               BedrockPackExportResult var3 = this.Xqedii_pl0jemr().Xqedii_29hipmc(var2, var1);
               LinkedHashMap var4 = new LinkedHashMap();

               for (Xqedii_2kb1ns6 var6 : (Iterable<Xqedii_2kb1ns6>) (var2)) {

                  var4.put(var6.Xqedii_3j6s705.toLowerCase(Locale.ROOT), var6);
               }

               this.Xqedii_2vtp3jf = var4;
               this.Xqedii_21hm206 = (Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-1069924165, 17) != 0);
               String var8 = this.Xqedii_39vcz5y(var3.mcpack());
               return new BedrockUiService.BuildReport(
                  (Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832260, 0) != 0),
                  Xqedii_3rsl5s14f6q63(var3.pages(), var3.elements(), var3.textures(), var3.mcpack().getName(), var3.version(), var8),
                  var1,
                  var3.mcpack()
               );
            }
         } catch (RuntimeException | IOException var7) {
            return new BedrockUiService.BuildReport(
               (Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 26) != 0), Xqedii_ggfl8ln9yaq(String.valueOf(var7)), var1, null
            );
         }
      }
   }

   public String Xqedii_39vcz5y(File var1) {
      if (((-541125304 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else if (var1 != null
         && var1.isFile()
         && this.Xqedii_vuzthgm(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("DyT7wS+ff3uWoTUKs6NvxoVLnqQxtLEApob8R876WH1NkRvkDZxOAKyKKRlqeRFDt7qW0HyKVOfUVyYBrjwpfbJlMFD+DiCg"),
            (Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832277, 4) != 0)
         )) {
         File var2 = this.Xqedii_1zfwgrh();
         if (var2 == null) {
            return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
               "HIPCj2DEUZCQZHr74YD6VuIg7QFcuen4fdC9vtoLKRnWOeaREaCvWNQ8mkEpDisvqzaVFQVIM1+6nSFCqA3kommDohd+F9JqfI+alOb9efINBR+BixnH/dLUmULb5JZ4gR7hWZCh6qVzTojjq86vURrrvmJtsJbKq9XIjwQTD7DluRYdJsI8y+i9ltfym38dmnYWo9MYnaW/D2ToPLblBQ=="
            );
         } else {
            try {
               if (!var2.isDirectory() && !var2.mkdirs()) {
                  return Xqedii_7xc4bm4ta0ai(String.valueOf(var2));
               } else {
                  Path var10000 = var1.toPath();
                  Path var10001 = new File(var2, var1.getName()).toPath();
                  CopyOption[] var10002 = new CopyOption[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(2004906113, 10)];
                  var10002[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1340627224, 28)] = StandardCopyOption.REPLACE_EXISTING;
                  Files.copy(var10000, var10001, var10002);
                  return Xqedii_bdpo0xq9uqfx(String.valueOf(var2));
               }
            } catch (IOException var4) {
               return Xqedii_j1wxd0rp6jeo(var4.getMessage());
            }
         }
      } else {
         return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
      }
   }

   public File Xqedii_1zfwgrh() {
      if ((654177342 * 654177342 - 654177342 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var1 = this.Xqedii_2p2kfh0(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("x17U1IBRyMMNwOrTbVXSqR/Vt9jPQnAaIiXutdYKCsFsyR0VhKCQ4TgQdodfiWJwFjGy25AKrOmFZ4VBaIAGBLxH"),
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==")
         );
         if (var1 != null && !var1.isBlank()) {
            return new File(var1);
         } else {
            File var2 = this.Xqedii_5tfok30.getDataFolder().getParentFile();
            if (var2 == null) {
               return null;
            } else {
               String[] var10000 = new String[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(571386693, 23)];
               var10000[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-285153022, 11)] = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                  "hIaM1LIHEzNkOpipz6xkwlmsCmrGW8ukEAwoWE2KR3LzkKfhFq86L5v6IHEylfbXfbb/+gwU"
               );
               var10000[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537701189, 17)] = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                  "oJ/8jm4aHR6jkSsMX/+US2mfmcFbjCXRaM2TeigNoq6H4VYI3e7qE19h2ZD0YLwtpkPvVsJE3kMAF+TeSd4="
               );
               var10000[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(1854734714, 16)] = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                  "/+Xu6jzmw7wp+RKzM8Bl7CNf19Cih1UPvCAdZcMyvAg6ktZozjjVuedbDX/HTmgOpwe6YRJ8LGtZww=="
               );
               var10000[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(2021750656, 26)] = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                  "L9ANEILA1lB2ikGbzBSFmszYimmtMtljCyKpZpsq7w0k9sPE5ToflA=="
               );
               String[] var3 = var10000;
               int var4 = var3.length;

               for (int var5 = Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-771367423, 28); var5 < var4; var5++) {
                  String var6 = var3[var5];
                  File var7 = new File(var2, var6);
                  if (var7.isDirectory()) {
                     return new File(var7, Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("cJxccm8prth9l87qxGgfg4PKoRb/Xn+2RCB9/Jjxbk09DcxlcWY="));
                  }
               }

               return null;
            }
         }
      }
   }

   public List<String> status() {
      if ((940325646 * 940325646 - 940325646 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var1 = new ArrayList();
         var1.add(Xqedii_2uen26aue7e7u(this.Xqedii_1tms53a));
         var1.add(
            Xqedii_271xxlecad8pl(
               this.Xqedii_ipfyrwr.Xqedii_29ttl5r()
                  ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("zirwVvgNMl3lxWNlxPZwZkkg0TIFcrjHorod1yP6fJOgtM7nxTDo7tRo")
                  : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("HFib+AvMWPb7PbvQsP5nJNwWJYf7U2kmo6FpQStNHLNkNMmd7SjC9MaC")
            )
         );
         var1.add(Xqedii_7dgfnhin79xu(this.Xqedii_ipfyrwr.Xqedii_1148524()));
         var1.add(Xqedii_3d6ez036719ex(this.Xqedii_ipfyrwr.Xqedii_2h9n0cx()));
         var1.add(Xqedii_e0v6no66ylf6(this.Xqedii_ipfyrwr.Xqedii_2m5qhgo()));
         var1.add(
            Xqedii_36hq4sl7yfka5(
               ((this.Xqedii_2r92e6t() && this.Xqedii_ipfyrwr.Xqedii_2s5vyvr()
                  ? Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-2140110473, 18)
                  : Xqedii_1lbievo.Xqedii_3evul6ygeglfz(787484304, 7)) != 0)
            )
         );
         var1.add(Xqedii_1du2ww1ycoacz(this.Xqedii_3pmue8y.Xqedii_20bhnal()));
         var1.add(Xqedii_2rjvg4e9xlthv(this.Xqedii_ipfyrwr.Xqedii_1z3dg26()));
         var1.add(Xqedii_11ae00syuck5b(this.Xqedii_kpr1s2c()));
         var1.add(Xqedii_zmw37qy1onp3(this.Xqedii_12acaqj()));
         var1.add(
            Xqedii_1hl8o9743rffg(
               this.Xqedii_vuzthgm(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("3CmWzQe9legdwsz+qw1MGSP15lJexLydWCRQ76x+ERI7EJIiIWIuCa4uCM3Ccg=="),
                  (Xqedii_1lbievo.Xqedii_321mxj7tclmi0(1305575471, 13) != 0)
               )
            )
         );
         var1.add(Xqedii_3scvemqjtcdrt(this.Xqedii_337zgsp));

         for (Entry var3 : this.Xqedii_3b4x69h.entrySet()) {
            var1.add(Xqedii_2cy19j73yxnlx((String)var3.getKey(), (String)var3.getValue()));
         }

         var1.add(Xqedii_1wizv73nty15c(this.Xqedii_2vtp3jf.size()));
         var1.add(
            Xqedii_31gu5p7inskyv(
               ((!this.Xqedii_21hm206 ? Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1560256869, 22) : Xqedii_1lbievo.Xqedii_3evul6ygeglfz(1209497607, 30)) != 0)
            )
         );
         return var1;
      }
   }

   public List<String> pageNames() {
      if ((-253426060 * -253426060 - -253426060 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         return new ArrayList<>(this.Xqedii_2vtp3jf.keySet());
      }
   }

   public List<String> inspect(String var1) {
      if ((1940790774 * 1940790774 - 1940790774 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         ArrayList var2 = new ArrayList();
         Xqedii_2kb1ns6 var3 = this.Xqedii_2vtp3jf
            .get(var1 == null ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==") : var1.trim().toLowerCase(Locale.ROOT));
         if (var3 == null) {
            var2.add(Xqedii_qfpinil2ryrp(var1, String.valueOf(this.Xqedii_2vtp3jf.keySet())));
            return var2;
         } else {
            var2.add(
               Xqedii_sfaz4rx6ymf7(
                  var3.Xqedii_3j6s705,
                  var3.Xqedii_2kz3xjg,
                  Xqedii_3mriom7(var3.Xqedii_12g62h3),
                  Xqedii_3mriom7(var3.Xqedii_r5a1fhj),
                  Xqedii_3mriom7(var3.Xqedii_2g46lck),
                  Xqedii_3mriom7(var3.Xqedii_1m61jit),
                  var3.Xqedii_b0fv9fz,
                  var3.Xqedii_1ei4hmh,
                  var3.Xqedii_1jqflox,
                  var3.Xqedii_sytzb79 >= 0
                     ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("TA2fvDIzQdUUziRNDx3tOuQCV1+Ugfyc2WrBEnvKYqTiArjuo4uxvA==")
                     : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg=="),
                  var3.Xqedii_3ow3583.size(),
                  var3.Xqedii_i5hlcvn.size(),
                  var3.Xqedii_p5zrifg.size()
               )
            );
            LinkedHashSet var4 = new LinkedHashSet();

            for (Xqedii_v7p1hqx var6 : var3.Xqedii_ggpkjmc) {
               var4.clear();
               if (var6.Xqedii_2sh7mex >= 0) {
                  var4.add(Xqedii_56vw8l5vp7b(var6.Xqedii_2sh7mex));
               }

               if (var6.Xqedii_21n4stt >= 0) {
                  var4.add(Xqedii_11b2tpzparxxh(var6.Xqedii_21n4stt));
               }

               if (var6.Xqedii_2hjfin7 >= 0) {
                  var4.add(Xqedii_xqx07lybtok5(var6.Xqedii_2hjfin7));
               }

               if (var6.Xqedii_3rdwuj0 >= 0) {
                  var4.add(Xqedii_308kpx4p8jm1g(var6.Xqedii_3rdwuj0));
               }

               if (var6.Xqedii_33uodds) {
                  var4.add(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("ceCwcaNRuIweGpuCxJcAfGJDHtFr5FbcphXE6dxR9MThVJGmCOben30p"));
               }

               if (!var6.Xqedii_76cq2mh.isBlank()) {
                  var4.add(Xqedii_1s6vu6khw341b(var6.Xqedii_76cq2mh));
               }

               if (var6.Xqedii_350upak != null) {
                  var4.add(Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("lQW9usFKPOIGz+PrDWCdR2Vaa7ualPe/xpasStqYRaTUU32cHfhQ5BD9"));
               }

               Locale var10001 = Locale.ROOT;
               String var10002 = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
                  "cImpJVvKa/HkuXD2fCYOhdJ+E0Jd8f6mOmbp5mKJdkMZ24sJMfBrhFSdHmgs3XCz6l6eBDQxxtEJRszkN/9mDWIM1sCjgaH643Z5C8tBY868/cHKI44lvZHpxoggOqEeGlEnrNW9iAWKZ4F48HsshpDwpmw="
               );
               Object[] var10003 = new Object[Xqedii_1lbievo.Xqedii_djsfar3sty2z(-1851828814, 29)];
               var10003[Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 12)] = Xqedii_1n40dkn(
                  var6.Xqedii_oruxzxc, Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-373662715, 10)
               );
               var10003[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(14746351, 19)] = var6.Xqedii_272zqk2;
               var10003[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(1611574085, 29)] = Xqedii_3mriom7(var6.Xqedii_3l6f7ti);
               var10003[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(793629120, 5)] = Xqedii_3mriom7(var6.Xqedii_10bvdlm);
               var10003[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(-1067541597, 30)] = Xqedii_3mriom7(var6.Xqedii_35yqe6o);
               var10003[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-267644882, 15)] = Xqedii_3mriom7(var6.Xqedii_zgu3xeu);
               var10003[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(-1609651388, 30)] = Xqedii_3mriom7(var6.Xqedii_prfwkef);
               var10003[Xqedii_1lbievo.Xqedii_321mxj7tclmi0(24800914, 0)] = var6.Xqedii_3mu4xzh
                  ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("/9qsvQQmAmQ2bPXrrmtYMABd9QPhwyvpFlKfttqvhX758ofsTLBRoVcc")
                  : Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==");
               var10003[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(22152670, 20)] = String.join(
                  Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("2C92BPpXTZDGvNvCDvYvHHHRjMcf+wZxVN/uHrwv"), var4
               );
               var2.add(String.format(var10001, var10002, var10003));
            }

            return var2;
         }
      }
   }

   public static String Xqedii_3mriom7(double var0) {
      if ((-973276019 * -973276019 - -973276019 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var10000;
         if (var0 == Math.rint(var0)) {
            var10000 = String.valueOf((long)var0);
         } else {
            Locale var2 = Locale.ROOT;
            String var10001 = Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("BxDD5bPIS6LrxB5MXVbjwZqgon8wIp3MO78ONeQkY2jaBwmF");
            Object[] var10002 = new Object[Xqedii_1lbievo.Xqedii_3evul6ygeglfz(-1145030592, 9)];
            var10002[Xqedii_1lbievo.Xqedii_3uwcly88jo2ym(537832261, 2)] = var0;
            var10000 = String.format(var2, var10001, var10002);
         }

         return var10000;
      }
   }

   public static String Xqedii_1n40dkn(String var0, int var1) {
      if ((-1789381505 * -1789381505 + -1789381505 & 1) != 0) {
         throw new IllegalStateException();
      } else {
         String var2 = var0 == null ? Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("0hUwHvvg1IEP0m3FCavv45xjUVA35FNBuO6Clg==") : var0;
         return var2.length() <= var1 ? var2 : var2.substring(Xqedii_1lbievo.Xqedii_djsfar3sty2z(1851828812, 31), var1);
      }
   }

   static {
      if (((-1998974521 << 1 | 1) & 1) == 0) {
         throw new IllegalStateException();
      } else {
         Xqedii_ad4hxn1 = Pattern.compile(
            Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z(
               "gEW3/zAvC/IkPB8t+XB905D5oPqy8I+LQ78AJqbatyWULWykaRMjzNLcPzWttUT6zDMnLk0KAWFt5eLL39ulKAfY2+t12ueg+p7gwkQh490XeHrcy7o5p6qVmEmWT3nSR8c8QZK4RzplEKkt+GedSacJoi8vElWcFPRSZXfb"
            )
         );
      }
   }

   public record BuildReport(boolean success, String message, List<String> warnings, File mcpack) {
      public static final String Xqedii_3jpoos6o7zczj = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

      @Override

      public String message() {
         if (((958389517 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
         } else {
            return this.message;
         }
      }

      public List<String> warnings() {
         if (((1927610069 << 1 | 1) & 1) == 0) {
            throw new IllegalStateException();
         } else {
            return this.warnings;
         }
      }

      public File mcpack() {
         if ((-524776364 * -524776364 - -524776364 & 1) != 0) {
            throw new IllegalStateException();
         } else {
            return this.mcpack;
         }
      }
   }

   public static enum OpenResult {
      NOT_HANDLED,
      OPENED,
      FAILED,
      REFUSED_HUD;
      public static final String Xqedii_2jizcjsqh9289 = "\n//\n// well well well, Hello!\n// Contact: https://xqedii.dev/dc\n//\n";

   }

   private static /* synthetic */ String Xqedii_36mwllnyw4pmz(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("ayRW19Rh2kTYTHf1UsEAOwAj59eDJYtjuNvJ1+7qZkWnjyaNYb+AFzi+8WuaZXPomm7jptgT0WvItKJV91Y/U/oJb2JUEPoNtesWS1SWp9Fed20cpEBWmQqxGGOOvOHSSpjnMsd7IaXFLCHha51E0RP0") + string;
    }

    private static /* synthetic */ String Xqedii_nag059ssg9fg(int n) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("r1qp90TXBDZkvUtMpjqjSVBIgI40JxV90hAi/2v5xWkbYPsmeTBHT4Zb49JDEgsKmttlJIQVmKUuyea5dyjJX6yD3kOdfsaySuiA4DiPcR0kZoB0DLI=") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("AeZIaCrhW3BGv1E4PxxSVGtZ49gntH1xhlPWbrwImExl8ny8xQqwfToT7xiwXPxVHIWDRc4mf8C3tGmFmdGQ7r09uhplDdKBpTppfkrPqGSDOuexetVBEwTxv8ItZLxpafJCFTRHsCWKnTtSjnBEDxuq6FWJeU6gH7b2LBCEQhZNmCFtm858EbvKeTgU+A==");
    }

    private static /* synthetic */ String Xqedii_42xumw2ml31q(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("wDTo0SOoo+VHL0YSO44ruONeTsNBIDCqhtR5RonkBBqEvSH27arVl/7Jj+5jXtnfR/KG5aejcMR3UarYJaeeYKU1gjHDvg==") + string;
    }

    private static /* synthetic */ String Xqedii_68qud8chb4tc(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("OpTdB4JfP2ALJDU2IBUE/RX+3kxAkyX7LEIJ0kcoALvmrfXzrWzS2eV6NUeBaCVp2iKnszkwVQj0WXfpUmJut1Ka4ArJrx2sfIXaxpOjC9nqGpix") + string;
    }

    private static /* synthetic */ String Xqedii_wblv6ur653vf(boolean bl, boolean bl2, String string, String string2, boolean bl3, boolean bl4) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("fE9/xzulgusXRJoef6jNjTrv6M5HpEmj8JBqEX6RWlUayVw6LOC4653PMko0hITRTBWQcvD8D04pmygw") + bl + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("OTWfThmD3UHGqk+dpH/9bhuJiSMvgTVmcX5txBL4zgKTL9r2fMBfmuhesUW80s5Ggy9q1ypa") + bl2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("JPprI3rQh5uHLQwVl+s5Kcr1HXvcNSQN+nCDTb0b0HowqioKe825dpKKKLgrbE71bwMfGlfYdug=") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("wceKHX1pcS04CnwUejTSOiDBSptzo1d4d4J57Mlg7oBNKchFielXRGeDD6ExIUBFdLZf8T1kJBw=") + string2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("m/59XoFFwRh4sQ+FU8wjALzBtKxwyIKGrQserH7huIiU0DU0C9OSyJnlmlolBVQZ") + bl3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("JWzPXqGjtGRYrXJJpD7CdCjWIX6Pjszz0hR/J4PViIk6J1WJ/1wBwV9lwSgAqIua") + bl4;
    }

    private static /* synthetic */ String Xqedii_2qfdolqpps3qo(double d, double d2, double d3, double d4, boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("gPjCQRw4BGJBlzKH4GDjDZezahxJeQwGB2JRZZHKNbSnHpvSRBtt1a+9EL8zRkq/91hpTYi+PKswig==") + d + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("yJZFBGd8UnrEIBIRV3kC7gf8Ybv43NOE46vhviEP") + d2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("jI70uVLE9ENLp99fjmIK1ajWOCCuFIexSN3BY5AmdTUZ1C2a9AIJ6dSsUN3ZdeJoy4A=") + d3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("yJZFBGd8UnrEIBIRV3kC7gf8Ybv43NOE46vhviEP") + d4 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("etsxwofo5FW3BmtzUHn6b7aZJEvNnEGX+XR5W9iMw5ByQfOyk0O9J8rKkqNzTCN9l+qiXLb4") + bl;
    }

    private static /* synthetic */ String Xqedii_1fq3nrpvuo2oh(boolean bl, boolean bl2, boolean bl3, boolean bl4, boolean bl5) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("oHaVMNMzwTAnMsiCiVpOrcGUInEk1S1cQPNJUZ2Fgf7Lv+6gQPdIXwo75UUQAV+xHsQUDtB5XR503YhqO+ekw08B") + bl + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("jAH8oQ2jwWw3wk+Fnxz0CBq2fGs7AItrb8dH1qqY6GktnuHq05IE4dIc") + bl2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("4KnTHHDnxGm4b3JsVxTjLJN0CTL9pnTcsga/sZQPA5ftat/Fw0c6+scDjbkxvniAMvdv9g==") + bl3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("syUbgpqjzFvLbdAjnvd8cawO43BsnYXOse2q4f5qPLpAdylFj4o34xtWxQPt3imqjg5Lrw==") + bl4 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("bGwcnUAKXKAy1J1B84lLaciiqV3bdxTigDxSBCNmu8dhJYdaDGLgDbJcjvE9All9PPvY2A==") + bl5;
    }

    private static /* synthetic */ String Xqedii_3kfbzi40to3h2(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("rSOF1DBzlQin369NxvPMmnR/zhUrMPfSSP3SAfu0qtCsK5JXAN3BP7RMScM=") + string;
    }

    private static /* synthetic */ String Xqedii_1oa3xvl5psns9(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("rSOF1DBzlQin369NxvPMmnR/zhUrMPfSSP3SAfu0qtCsK5JXAN3BP7RMScM=") + string;
    }

    private static /* synthetic */ String Xqedii_botq99zzaj0n(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("rSOF1DBzlQin369NxvPMmnR/zhUrMPfSSP3SAfu0qtCsK5JXAN3BP7RMScM=") + string;
    }

    private static /* synthetic */ String Xqedii_2xh49ei7dq7tk(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("hpCz7igqdbe9Yqsqm8xUMM+mylrQJSa90rpkHBvbkEcd9WQLyjKqWP7kQ4q/6mwLFZYJ7NWg37BC/ygU37PmNuvjKOkglB58LB8m2gesGCWt0g==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("ughaCCadru26/He0QhSiUP7nuvu8ubiQXWW7prSRDaPOhRuDUC6NvfhyKQ5g2dH7n5Cjx3XIslkh3lnRDnHepLrqkZGVr5jeZvC/PQ0YzMvhr22STgT5tcg7uT/hBg==");
    }

    private static /* synthetic */ String Xqedii_3ucr8fy9dc8e1(String string, String string2, String string3, boolean bl, int n, int n2, int n3, int n4, String string4, boolean bl2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("2bYjJ3qrqWleqCRYjYtZDE13sfCqiu2p6lY2oJvXy9pLp/rfC5aghw==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("SmKRk8O7T2UM5SjWzCoKk4qJ4ZhH0xYVFuNm5D/E8dlbazddnZjMVA==") + string2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("YPDsyQr+9mateHxdwNsv09dumaW0PguuhNQ5AbAPYJPxrVJB4NOv3abj") + string3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("h64kJ7fg23Ld02o5B5F83vQXJ9GDMkl7e9gzk2kSz/74PAkeOuHswZtv5rEbqO9nr+KG0fIrRjA=") + bl + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("mQr6ecw68PqS/JFmaiBU+fBirLO045rTffbtgbYu1tZdFhox6An9rd46pqo=") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("1i2yiiBO/K8QfBAeWYPldOtFrJ+Ovr/NSoK/1TIMbBLDEz1OHrTRzojF3wQvtA==") + n2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("P3zkh734kPxGb4mrIkzWUAXzRBuZRrpOWUiuKHNN") + n3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("P3zkh734kPxGb4mrIkzWUAXzRBuZRrpOWUiuKHNN") + n4 + string4 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("lNXtDzGKpgoLBN/PkgM3JzwFc5WyTX3X4ovmRkA2sgWcT/II8+q4zOB+SarsQwLZY0Q=") + bl2;
    }

    private static /* synthetic */ String Xqedii_19gx74qk3qvq5(int n, String string, String string2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("2pLgKCsVElFH4EWndBUg3r/3atuJ4pjTaWfExI4Bg5rglJMrDwTNy14O051eVGuErNQNESK9") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("YVVzVGiPVYQZzqY/XGJwS6TfArHJyVoULDU45RyiAbjLj0yMCX/8YA==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("/F0Ap2qx/2/Vs4WRV9JAFwf+ShGqs+1DOSrm7DAq5GbqzKN8") + string2;
    }

    private static /* synthetic */ String Xqedii_3pxhxylmqlsyb(int n, String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("3NwHYet8DowK8H4HqG93hJPVh/nsTD8uqZ22dkWZDwvOyMZWbwgKKPLAkoHkYMk3LG0fDNVc") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("/F0Ap2qx/2/Vs4WRV9JAFwf+ShGqs+1DOSrm7DAq5GbqzKN8") + string;
    }

    private static /* synthetic */ String Xqedii_38mn3zt2qaw8w(String string, int n, int n2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("SeYlg6CdaKxp34bf5dpIhPCbOuz9vb3Aj8OzbIaXhLTJS+BwogkXHYCTYSU8uIlO67WaA7lP0TKUGJf3LXvQt4B0/HXi1xS5F7NArA0VvOtSAg==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("4lPc1Li+98T0CgthmAgd8Y6F5boRE0d9x7Ve3sQM548a61kaqmUzFKATejVldQ+S24M=") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Wzo3FY8XphIvJ3pr6pK6hTACP5Dd/8XWVyXCmP9SA59lZZZ0yWCCzVnqpQ7Db3LE+wzlJQpaNUt0X7agkuMBK65asEudPBTKUGnLWg==") + n2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("73EYS1D4mhSn8y3QS2JjGRC6WPX7s5mDtYcFevf47OdobCpQZVJlRv1mlgSVMmWx0lJ1JaS6a58Js3he5twVZwA8dVwjCKA6PzfK+Qkka41qOupxOT4txw479j4WSBdW2O+84huiDT80vGtaf7rJFW7HC/KXvutj0rUwemcCfeNCaRk5LsRfVjKlMiMGfQ==");
    }

    private static /* synthetic */ String Xqedii_d0tme6a1ija6(String string, int n, int n2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("SeYlg6CdaKxp34bf5dpIhPCbOuz9vb3Aj8OzbIaXhLTJS+BwogkXHYCTYSU8uIlO67WaA7lP0TKUGJf3LXvQt4B0/HXi1xS5F7NArA0VvOtSAg==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("i9pFBHbAQHtH9m+sbqdiiy0cet1rQrWgTn/TZGZQS/JkA3UkjqxWMqSdvI1GWCGoYT0=") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("fOxEK1mBPWBZlZCJKT4pNO/fJ9DqiOAeFR+Ntzw8cf1Z3p6tCt5w4LxRjI4gD6VogGqQuDIw/+B5aeYmAhjnONTJjEspOAs6") + n2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("yHzWqaMQYrozwb8URQxrkk2qe+qJsuQwijlNWmflFeun9GMZIHrujP9aXMEopFUHzOFI7VrJ2UKbuS1Xz7dZwXGGVbdD2CIgPWUODocCQbahmOAZ/Lk+7x9/i1xxZokiu7v3dGk+evWdUvwnoQR5yoWZRLsdx4EVtQttDQ==");
    }

    private static /* synthetic */ String Xqedii_827rxo8wshfm(int n, String string, String string2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("m9ePKh45FttkFrpjh58RqDtv2BDKCqJ9gjLGG0SRM3IjlJNLIKlNsnu2lXGDx+1xSwGW0A==") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Oyg8u5w8sa97TynA/8aPVYmnj8A4jujBiLtDUj5GMY4=") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("CaHs5xGLvUcvUhtoex7SVRbQjUKdzN4H8lm0eIOep9/oyLLSKTc=") + string2;
    }

    private static /* synthetic */ String Xqedii_3p81wlur2vacl(int n) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("QfdXUn7Z6QsaErOyuP9L+0OmFm78HW24vzBHmoDm") + n;
    }

    private static /* synthetic */ String Xqedii_1cy184kbwidxg(String string) {
        return string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("g7xcJMpmprTS/ujkVvZwMg7YOR6NVgCJisshCO7x");
    }

    private static /* synthetic */ String Xqedii_15bvy7jgfagte(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Pd4aKSA45VtdAw44EMcBu1cCGPsKu3uDNVTp+QGE") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Pd4aKSA45VtdAw44EMcBu1cCGPsKu3uDNVTp+QGE");
    }

    private static /* synthetic */ String Xqedii_354wtt6digpvu(String string, String string2, String string3, String string4, String string5, String string6) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("LxZMPJftbkMDqav3aKDX9aETbzp2w1058jsi+BCP5orFCgAVq08EF+FcjU/JUNwEhnC8uCrRUi4ctwNm61fYS80vRXZTZD0Xm/N4O2V5") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("iGCKj9oXDAcn1/wfgFJkVxlsVdzVigaZsmf0dOFxI9lNw6/GH78/j2wiYfnvnA==") + string2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("d+rwR7YRCMUBxubzUB5kaVTRfsz2bxn5KauVK1rpTurHwGzZktn82wCgh85wfWp90NQ=") + string3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("mieXqNM8Xvz/f5CABUAbOpGFnJFhfMDZoVmwXsrOOWCk/7t6ffAU2h10P5g=") + string4 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("3rvgj5QOTxU5haUXoLULtBexXHp3dI0qRFUgEN6a7fkPa3dxhVa5RdqZVsY=") + string5 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("CaHs5xGLvUcvUhtoex7SVRbQjUKdzN4H8lm0eIOep9/oyLLSKTc=") + string6;
    }

    private static /* synthetic */ String Xqedii_231ny943g1jco(String string, String string2, int n, String string3) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("GMWBXcTxNLvAztke0X2G7H/NCvWb2AyqoSnqCzQT7La3bz/RdjZOl+d8PMWiYRFp") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("hNB4uOU6jlTOtMz7p3Lc0/Ioru62voJmaEKigheJxvD/GAcS3IM=") + string2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("sxy+I289YbRvR10VUG+88CnrsqhO4fNmjASSjKznGg32tdN0kkjvXkyP") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("4wz2SoegEL5f6msUc1GQ0LFpfPXzkeipCHJEGr/GC44wleC//bej06sbvOm1/vvqgc8hoDcPIt4=") + string3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("oL8GCWMctHaZ7EMgXPqsS/Fa8pf86IS5+v7x01ES");
    }

    private static /* synthetic */ String Xqedii_3p1q1jq8wl06(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("y7G2D1Y2rsLQ1RQ6NZghuuRhTe+8Pw2bBVzjFZ+LXumdVGBV6MfNTFDpT9/L8NgBYvHWaUeLj6NhRXYtGMz25ckSZOVdqO9vVwvTB9Nk33YMAb2x") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("8CcRItfL0ZtfjjSFCVBgmIP0wXKiOaXEa5smSmQFHa8Uc+6fy+WsVtFntd6lrHecq+HrN3rLZ7I0N1IdAZxViOElC6vpb/HFgC8a4iNM8TBFoUpvJ2hE8GtMHJxrUkSQrECRSvXy5nllJ6+zj9xQc0PFgyiPvxQm4+hA7zOdRaVl9g7ZJMI77x8zJXJ8mJ7S91Oy+dwIDiaBmmb1o1zPhd1acRwTXY1t6AucR790OAf1qsBs");
    }

    private static /* synthetic */ String Xqedii_1dnp2tdnfrx79(String string, String string2) {
        return string + string2;
    }

    private static /* synthetic */ String Xqedii_bmv7npo7ycmz(String string, String string2, int n, int n2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("bSSXkNWmZXVcFDi4muKv6jqH/s5Z+f/pJKHaBZNh2xU+xPHigc86SP7xLq+h1TG8GQ1aIRzZzzbEVQ==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Ipl2Bh8zzMEcAEWO3U1GK2KpQtOmREnRnQ+R/W3OI2035DOvDvy2/w==") + string2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("fZowtW/dmlOClvu4Li8+/cEtd7mHaXOlhuK7gfS8o09W8cin8QBhxe+wCxo=") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("FE9VEv52RrQqSUPIsrEEooYPazYYPcQPLLAxmC3mlHyaRZ6boXueDnt4If0=") + n2;
    }

    private static /* synthetic */ String Xqedii_3rsl5s14f6q63(int n, int n2, int n3, String string, String string2, String string3) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("o8pYW7SClWgTzJBwDeS5Gl1itTAVwzFkoXmg3OA6jsvBM9V9dTtuSzGjQI8R7A==") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("6QpRB5M7YrN3nJlBAW4jiIr9gI0JpHG6QbZPYtPq13x4cc3YKv+64b3ozz8yFYC1") + n2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("MvEwbjDmnUUz+hTBzB/o3GTgmx2GtJgwBfcy5VB8+15nvX3GGgEjGusH2QFuANB5h2sL0BkM") + n3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("2lTT44lDKexLH/PrshyD1C6lCbaY77C9WPgZSKWmZvaA2faHsXDTk6X3W3c5CvhbBUC3wNWMsTpYDw==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("YKBanPLfJXk/zF4vEi+bkj0RcWdFOOhUduhLoDWeu3JtWiBlUXPSvUYKuFipTPHm92Qtpt7XVaq0qw==") + string2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("oL8GCWMctHaZ7EMgXPqsS/Fa8pf86IS5+v7x01ES") + string3;
    }

    private static /* synthetic */ String Xqedii_ggfl8ln9yaq(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("F44vcc/4xkomnP4GRgUu3+zsAyjPHEcVSiBmLjAaKCEJyjrDLgwF8kRm7R6SIdefQeDrA4e5Ljy11YcFHK8wU/gddqO/X9l49uTezMj1zY4QiA==") + string;
    }

    private static /* synthetic */ String Xqedii_7xc4bm4ta0ai(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("gyRJEaELYWEPDgI+ck95v8xjsAPs0Ahs0f9/GWzNT/A59BG44R0Yu08zemkCPNvNak4eHU/h80BF59ClYsY7IhNV") + string;
    }

    private static /* synthetic */ String Xqedii_bdpo0xq9uqfx(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("uKqS/51PrS972rWoEtM78fxGLOEelW725wpfv2P2dOoEJUyx5Txue61fdjk+PFTk9/JtQUwKMUnWdthwPRU=") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("uzhu1FVGJvAaAyVQVJvUjdoRp+To7gAcDk3rL6duncBGSIXQE2XDQrBNFAja7Y9EHoxHD4eO6aFqBOrNCIRcIt+vsu7Bx4jewv2w9z7+tECbFw==");
    }

    private static /* synthetic */ String Xqedii_j1wxd0rp6jeo(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("bAAvOekDhq3lHNoT0XfCLKw7LJI+Xf2/v4YSdSi+r25qPEufR+R+KR7/lncGP3TMBoVo/bce5l/2lLsdVs7ILbMm6cHKlWsMCRo8EZ9UOztXrnYkWIoAmslUlsfBbQ==") + string;
    }

    private static /* synthetic */ String Xqedii_2uen26aue7e7u(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("6txy/F2R1ySrcflhHHbP23sV6j9fMkcKwlOUqKSxvj+aj2eQh99JbH+2u5qodQ==") + bl;
    }

    private static /* synthetic */ String Xqedii_271xxlecad8pl(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("8UIOsa829c0gQnqS6ZKOoi5bgSBiFkZp4Zgw2gvN16jOwRKjuqsVKvBQ0i+BH9zVOrw=") + string;
    }

    private static /* synthetic */ String Xqedii_7dgfnhin79xu(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("O9pObFmpURRFSMKoqWhpboVtQ267NqmaaxAYFxUnJw7uf2OgVdu2bMvgveKlaC3CHm2yeUXdolw=") + bl;
    }

    private static /* synthetic */ String Xqedii_3d6ez036719ex(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("HkwHnl2fMMo6wVR2msei27ObSMwamVA2pL+WdmDrkPoeoeKvYF2DSyYQSpHFZSi0LCpHawucq9+wa24yU1TypGT8p5PtF11K1nI/1ToBnDlOn72OFte4hvEakqRLsQcQThNrbg==") + bl;
    }

    private static /* synthetic */ String Xqedii_e0v6no66ylf6(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("YUCRu79gcqSBBI/y8rEa4Uezdhr461+qgX0k3ign5czz8fPYBPn7ny30fxEx/yLRhu8BuVrG+RPZ+q/XFAkWWo+XzsMg3R4d9+OvQB45cp6KbA==") + bl;
    }

    private static /* synthetic */ String Xqedii_36hq4sl7yfka5(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("IGJtMD253ymzPh7sfEWmmb1S89dmtavvW+vsGoaDLGvNlbbNgrTMr4v1QJ9MDLcJhrLquaHabhaFrG2fxMC+hc8MBUM=") + bl;
    }

    private static /* synthetic */ String Xqedii_1du2ww1ycoacz(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("brMtdUxpSIHB6iNHagh8o9hb3B4/F7EvfzugxBNT1jXEKY+m3yFbRkCz4n7WMyAg3IXyvtfNJC+7Tsx7uAHXrR//3gpoI4Jv") + bl;
    }

    private static /* synthetic */ String Xqedii_2rjvg4e9xlthv(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("2GQOLi48CDbOsoPuDCMLGtgUE8Wt1r/GmrUeT5ei5qJeHPtdlsDUclVAG78J3RmqMMPOgHlfIXLWRS1ERjchEHsoNcfoKL7l9qY=") + bl;
    }

    private static /* synthetic */ String Xqedii_11ae00syuck5b(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("2rjY+BojfpaqMTPK7mm4kWa0j/uhPwnHQemYyBmS+Yn8mCeBqJ1Y0iKbh2MkKZAxyW3B0dJ/lEkl+Jzdw9Ne8+uUs4M+X45wtINYB3URQlY=") + string;
    }

    private static /* synthetic */ String Xqedii_zmw37qy1onp3(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("WFJql4stiQe5fdLChFblH4pbRu6aE5cNiLN+dKiBW94sAKwcuufN5oF6yZbz21RhOu07is2LLqNj5vc+Cudfn3NQSzbhK/dVSlQ1xfq6OCY=") + string;
    }

    private static /* synthetic */ String Xqedii_1hl8o9743rffg(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("8ZWRG2yjmuVHERKU3uBCfIkF+a+mGVneJngdy2cMP/LGMtKTau/hKNnAAUp+4t4ItdT9+nIdnJUgCqAYwWCrWvTXsoV9o29wtdR6fA==") + bl;
    }

    private static /* synthetic */ String Xqedii_3scvemqjtcdrt(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("eGV6XIhSRyA6U9hSdjTLL0swA19vLV5WxHJYsrQeo59Huv81heflupbQvNN2UNo4jUhX+DeJJK3mdg==") + bl;
    }

    private static /* synthetic */ String Xqedii_2cy19j73yxnlx(String string, String string2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("F/mTFNej9nuej5lEbzjDRc1RUCJ3/A0FtQRp30/pDTVhU2LHr3j72FG5c+M=") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("/F0Ap2qx/2/Vs4WRV9JAFwf+ShGqs+1DOSrm7DAq5GbqzKN8") + string2;
    }

    private static /* synthetic */ String Xqedii_1wizv73nty15c(int n) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("F9os7IhfjYoDptTbuV1AoFT51Fylxu7RdsbUYdL9W38nn+I5q9dtK9fS8Bhg/P7s2HPsoEDBNelitGozzRwXONwWrEjVURFq6kg=") + n;
    }

    private static /* synthetic */ String Xqedii_31gu5p7inskyv(boolean bl) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Zi0k4fM9jBGLPsvu4P+G+4CcAtZ+wiGgIbUVaNUiVhFeaUtvQd3MlZHq0txp4XVG8DeeLIYOLpS1zVNRUGA=") + bl;
    }

    private static /* synthetic */ String Xqedii_qfpinil2ryrp(String string, String string2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("FJeYfyRPLx51dpd714wlRSOeZ66ijuXSR1firxaji5qg2O40XWkOry8/eYqEh0VYua7XgIHXmMtLTTAiuJAh+dWgk8rAKmsVTRtY+A==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("Fh3fANt2LmqOkzlp+cw7WeQNdBS/WY5Oyo6bb94X7J2DUlhurEXUGgxOIcCKHhfr") + string2;
    }

    private static /* synthetic */ String Xqedii_sfaz4rx6ymf7(String string, int n, String string2, String string3, String string4, String string5, int n2, int n3, int n4, String string6, int n5, int n6, int n7) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("GtLKSLDfnDwoRCkSzHOmEoP/HnWTTbw7JiynRS344KNi80bAKqHpzA==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("d5du1Ru9jN0ZgTYEpMIgot7Q0Ke9EUhz9Z3saYk/yP6LUn7H5U1nomoeOEuqIw==") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("i3zKw9iOaMAW/BqsBrt+fjB7rDv+N2N8eOEwymtaHoV5EvF1nteaXRjHDpxzpQ==") + string2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("yJZFBGd8UnrEIBIRV3kC7gf8Ybv43NOE46vhviEP") + string3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("nKHhqNZnxjpejrNd/7OIL3DCfPRr8zGsTggNpW8EtQav/1neSFqS86NSOHOeAA==") + string4 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("jgNBwZLNSO5eNyoH06mdQ2Nf0q7mKRtRCIs74xp/") + string5 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("qvwKsw8Xd6xO6Y1CRu3XRvEFpQNkC1pZnBrloHVldL/9THkJuntCeZdqpcOthDnh") + n2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("P3zkh734kPxGb4mrIkzWUAXzRBuZRrpOWUiuKHNN") + n3 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("P3zkh734kPxGb4mrIkzWUAXzRBuZRrpOWUiuKHNN") + n4 + string6 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("WUCJrb94IFy/25I1vQtMIGFLmUupqSuM2UhVWuGSHsGN5RolC8p0xJBSyoEpObEuyU9M4Nu4oVA=") + n5 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("QccD1ns+i9Z/0InjcQhELMNlIhxSxktkW79E51V9Y0U/GzhQoieDbiF0AUEIbUR4") + n6 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("GwRF6xkhRxIiGo3z7IPdJBM5ldphKEkQAJhmw1t3BEgfmBugw4SN8xVPmCHKbCbdLXNLWQ==") + n7;
    }

    private static /* synthetic */ String Xqedii_56vw8l5vp7b(int n) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("gFlY3Y56V/gIyRJU1+jNvKp6bnHyunzQ+I/j8k4iJGB4+djUbKrugQ==") + n;
    }

    private static /* synthetic */ String Xqedii_11b2tpzparxxh(int n) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("3+3p+yXddlID0C1E0k/xrKGLVrdg15NqUwuJYUSjiOsfLQ11GOk=") + n;
    }

    private static /* synthetic */ String Xqedii_xqx07lybtok5(int n) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("4Ad37KMIFKfL/mLTQg67tC5EzqpT04bC2FrBnHDT6rQmee93w/o=") + n;
    }

    private static /* synthetic */ String Xqedii_308kpx4p8jm1g(int n) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("JQiI76GodWGOWxVPc7GnhQuCXkWgHXsRxj+1ifqFwW1bceDikyypEw==") + n;
    }

    private static /* synthetic */ String Xqedii_1s6vu6khw341b(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("+B8piZ73wijItNSmr+oPkufmQRyWeAUgbUud3BWiOn/Kgg==") + string;
    }

    private static /* synthetic */ String Xqedii_2o0wpf0jk3kq2(int n, int n2) {
        return n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("yJZFBGd8UnrEIBIRV3kC7gf8Ybv43NOE46vhviEP") + n2;
    }

    private static /* synthetic */ String Xqedii_2xvrk4i2y4r7x(int n, String string, int n2, String string2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("n7b925RSnzXoeqkcSnQLI3x9odRSpYpEfJ1G2ofeznt3WpUm+Gs=") + n + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("YVVzVGiPVYQZzqY/XGJwS6TfArHJyVoULDU45RyiAbjLj0yMCX/8YA==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("y6yK6amUZv93rBSoAfjpTyK6PKljOcOqGbZBfIlhwABkjGz36mYC+Mnc/nY=") + n2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("pJes47lREli0ApmKZdTvxaKK2knAARlH47BhFupJy10qaEREa7DoWF6r") + string2;
    }

    private static /* synthetic */ String Xqedii_9bje5fgmf7xq(String string, String string2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("fCLyAyajwGQ4na4XIkrbniJWglF3u8gA29pn//W2YYQv0ZVU1cAy6mAh7BvkFHt9sUDqufif8PmHtr2374+aw2yOzqzdES1SvTL/H2mthcbF9K6vpyvebWQEMYIgISzh0+tjWInPgX80y4sA+Qyjpvj+k9kOtg==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("/F0Ap2qx/2/Vs4WRV9JAFwf+ShGqs+1DOSrm7DAq5GbqzKN8") + string2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("bDvhtCXUnXHqePxsm6Gm0n4N+XpsgdHsvCfA/p93ccnnVM5D7FfSLbz4yLPcDpuXGVD/c/KSLKqHtqSQieELDvBHVz9lVhwfe5EfPPQLYK9mRR73TYd7K4Z32YUpJ+JLnRhyIIdlJEnGjUl3inrV+CLlHDBflf9H+DsX9hEpzdvscp/Ia4MdutEA");
    }

    private static /* synthetic */ String Xqedii_3pzukx8coymm(String string, String string2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("JfSc9VmiHEreoET3qeBtgrbSytc99DUlZXAavzBpjFnpExUOFGpXQQj3m6EQ8+JgKG3mMWUUmEZ6HqFA8CeTg+XixdIPZNQXocpMxtrjE1NzXwDje9w6pV1idmdbq67cPB1WkYqy4auiQ6KQUaIsdctFn5TDgLz8oJ5Qkw==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("/F0Ap2qx/2/Vs4WRV9JAFwf+ShGqs+1DOSrm7DAq5GbqzKN8") + string2;
    }

    private static /* synthetic */ String Xqedii_3r4wrljr32vsu(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("hJbhDY0Isqx0I81LHUbLnNCzu1YRv2CKdNHZzXTh3T2WSNj7WkAFFPjm9RBdN5SCp1pu8J7S") + string;
    }

    private static /* synthetic */ String Xqedii_30aqn9apwdqb(String string, String string2) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("bZC1bUsT0fqypS/GKzVNWC0wV1rzKlOcCQbrlnoSCAsQwzralPCpSuo5ogFx7UeMEC3CQVo7cgi9c2JZMfXQQQvMdzww+Ylg/xVfk6rrIas8qNOCMJM0cKykDerwfgvDc0ypI16k4IGIePbvvEqMd/KQ9zziG+t6lZUnCv69l/2Dz0TQhvw=") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("/F0Ap2qx/2/Vs4WRV9JAFwf+ShGqs+1DOSrm7DAq5GbqzKN8") + string2 + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("f1Qqy2mA/8O3bxPblmw8455MnhGKXmxZiZUAvwW+xUpfH3DVZpGQ3Q+kwlRv7q6Bzt/NKMlMSDkiOlADv17MwlEC7CrSjr+SWqIRD/JaWUNnLAJjIEsFuUJRbuzkj8T4bHUNjaGIpGY1XCDx/W/XIMdz4Rc9pXe1mgU3RJUE9DGGtZzhaNs+b4koMzxgCKvJGkOu9erme4RYqjkb");
    }

    private static /* synthetic */ String Xqedii_2tgtqkbeigcjw(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("wDTo0SOoo+VHL0YSO44ruONeTsNBIDCqhtR5RonkBBqEvSH27arVl/7Jj+5jXtnfR/KG5aejcMR3UarYJaeeYKU1gjHDvg==") + string + Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("gSQjEtRDgny4RsGJ+R6BZSfWMN9Z3x+psP9xvYOikK+NZ6SrrF9nuaWer9+sWTspq3tXh/UHri7iv+UOjNrHbGHrsEp9zgWnMspun8hWUh8Qs0LWJyfElgZ7c+GqIj4g+7IjZ6NhgMDm/P51phbLDIhxE0tRCTGoq5+f9YbThX8Y3CoFvlwBWIbs0AWH57bX4TsgPqgIlB1f7xFs6YiVwm35ruoFq4atc0e+1iYyj6RJIN17+LnX3Nqh/zm2YiWBmjMt+DLrsCfyF0dG9sAcIACtQ066QTiSyBmV7hqs6W+ANHsUUUvn6qF+u2Y67oOI");
    }

    private static /* synthetic */ String Xqedii_mhr3hyq8k9bn(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("zlLtafptkKnvkD/6BMnjGG4lhKmPSiI2+rKqd4aZ60ECDa3DLHjaVRyjmc6a+yhPFHC3mI4HyCu3SqaoC4nK5L2c2mq/plyStnbH2Gi/YPiWEmaOgr3em/wdtv+fKhjs1XH5vXoLamL0deGHdPZvAzRf4D88yc0YUqmwHBgZjL7fdotN") + string;
    }

    private static /* synthetic */ String Xqedii_3tokgscxwa20n(String string) {
        return Xqedii_1lbievo.Xqedii_3v78kfpx0mk8z("1kU6JB76j9PISGSMjIJFZPsn1aMuon1W2FmJGjv2SjeO04jWh7+gSKEvgba2I3H2iWZvORwWlDGnnkD22YBmjzQgrZbQ1YAfhu/Y0dESyVj5VGfNSF5uvA==") + string;
    }
}
