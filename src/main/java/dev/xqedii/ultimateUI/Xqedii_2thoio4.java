package dev.xqedii.ultimateUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public final class Xqedii_2thoio4 {
   public static final Set<String> Xqedii_3ct77sg;
   public static final Set<String> Xqedii_200w0a7;
   public static final List<String> Xqedii_4h0tx6n;
   public static final List<String> Xqedii_p2rt6hv;
   public static final List<String> Xqedii_2xrm6um;
   public final List<LexerToken> Xqedii_rty1vz4;
   public final Xqedii_1kffk3z Xqedii_d4hvym9;
   public final List<Xqedii_39jklon> Xqedii_1ukapu0;
   public List<Xqedii_3vsk7zm> Xqedii_1ws39dv;
   public int Xqedii_2d86asd;
   public int Xqedii_38gkvzl;
   public int Xqedii_1okvqlt;

   public Xqedii_2thoio4(List<LexerToken> var1, Xqedii_1kffk3z var2, List<Xqedii_39jklon> var3) {
      this.Xqedii_rty1vz4 = var1;
      this.Xqedii_d4hvym9 = var2;
      this.Xqedii_1ukapu0 = var3;
   }

   public static Xqedii_1kffk3z Xqedii_2owkzy2(String var0, String var1, List<Xqedii_3vsk7zm> var2) {
      {
         Xqedii_1kffk3z var3 = new Xqedii_1kffk3z(var0);

         List var4;
         try {
            var4 = new Xqedii_3l1ke2c(var1).Xqedii_1lbczed();
         } catch (Xqedii_3vsk7zm var9) {
            var2.add(var9);
            return var3;
         }

         ArrayList<Xqedii_39jklon> var5 = new ArrayList<>();
         Xqedii_2thoio4 var6 = new Xqedii_2thoio4(var4, var3, var5);
         var6.Xqedii_19ahbp7(var2);

         for (Xqedii_3tcqkw2 var8 : var3.Xqedii_39uxme9) {
            if (var8.Xqedii_nyxmub2 == null && var3.Xqedii_lp3i5aj != null && Xqedii_1bczasj(var8.Xqedii_27kq3c6)) {
               var3.Xqedii_39uxme9.set(var3.Xqedii_39uxme9.indexOf(var8), Xqedii_1xmpkr7(var8, var3.Xqedii_lp3i5aj));
            }
         }

         for (Xqedii_39jklon var11 : var5) {
            if (!var3.Xqedii_3j5g4ep.containsKey(var11.name()) && !Xqedii_1iw7g71.Xqedii_2ln2q92(var11.name())) {
               var2.add(new Xqedii_3vsk7zm(Xqedii_rds1654yvnow(var11.name(), var11.name()), var11.line(), var11.column()));
            }
         }

         return var3;
      }
   }

   public static boolean Xqedii_1bczasj(String var0) {
      {
         return ((!var0.equals("load")
               && !var0.equals("join")
               && !var0.equals("quit")
               && !var0.equals("command")
            ? 1
            : 0) != 0);
      }
   }

   public static Xqedii_3tcqkw2 Xqedii_1xmpkr7(Xqedii_3tcqkw2 var0, String var1) {
      {
         Xqedii_3tcqkw2 var2 = new Xqedii_3tcqkw2(var0.Xqedii_1swqvpn, var0.Xqedii_27kq3c6, var0.Xqedii_2t32j8y, var1, var0.Xqedii_3e9f81u);
         var2.Xqedii_fw60fwr = var0.Xqedii_fw60fwr;
         var2.Xqedii_3gg5za6 = var0.Xqedii_3gg5za6;
         var2.Xqedii_ne1a52s = var0.Xqedii_ne1a52s;
         var2.Xqedii_3al7qe9 = var0.Xqedii_3al7qe9;
         return var2;
      }
   }

   public void Xqedii_19ahbp7(List<Xqedii_3vsk7zm> var1) {
      {
         this.Xqedii_1ws39dv = var1;

         while (!this.Xqedii_2o6grdd().is(LexerTokenType.EOF)) {
            if (this.Xqedii_2o6grdd().is(LexerTokenType.NEWLINE)) {
               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            } else if (!this.Xqedii_2o6grdd().is(LexerTokenType.INDENT) && !this.Xqedii_2o6grdd().is(LexerTokenType.DEDENT)) {
               int var2 = this.Xqedii_2d86asd;

               try {
                  this.Xqedii_hw7pztw();
               } catch (Xqedii_3vsk7zm var4) {
                  var1.add(var4);
                  this.Xqedii_1okvqlt = 0;
                  this.Xqedii_38gkvzl = 0;
                  if (this.Xqedii_2d86asd == var2) {
                     this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                  }

                  this.Xqedii_swtn3wk(var2);
               }
            } else {
               var1.add(
                  this.Xqedii_3sn28jz(
                     this.Xqedii_2o6grdd(),
                     "This line should not be indented"
                  )
               );
               this.Xqedii_swtn3wk(this.Xqedii_2d86asd);
            }
         }
      }
   }

   public void Xqedii_krrt9j0(int var1) {
      {
         this.Xqedii_1okvqlt = 0;
         boolean var2 = this.Xqedii_rty1vz4.get(var1).isWord("if");
         this.Xqedii_3f7o26q(var1);

         while (var2) {
            int var3 = this.Xqedii_2d86asd;

            while (this.Xqedii_2o6grdd().is(LexerTokenType.NEWLINE)) {
               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            }

            if (!this.Xqedii_3i85ij1("else")
               && !this.Xqedii_3i85ij1("otherwise")
               && !this.Xqedii_3i85ij1("elif")) {
               this.Xqedii_2d86asd = var3;
               return;
            }

            this.Xqedii_3f7o26q(this.Xqedii_2d86asd);
         }
      }
   }

   public void Xqedii_3f7o26q(int var1) {
      {
         int var2 = 0;

         for (int var3 = var1; var3 < this.Xqedii_2d86asd && var3 < this.Xqedii_rty1vz4.size(); var3++) {
            if (this.Xqedii_rty1vz4.get(var3).is(LexerTokenType.INDENT)) {
               var2++;
            } else if (this.Xqedii_rty1vz4.get(var3).is(LexerTokenType.DEDENT)) {
               var2--;
            }
         }

         if (this.Xqedii_2d86asd <= var1
            || var2 > 0
            || !this.Xqedii_rty1vz4.get(this.Xqedii_2d86asd - 1).is(LexerTokenType.NEWLINE)
            || this.Xqedii_2o6grdd().is(LexerTokenType.INDENT)) {
            while (!this.Xqedii_2o6grdd().is(LexerTokenType.EOF)) {
               LexerToken var4 = this.Xqedii_2o6grdd();
               if (var4.is(LexerTokenType.INDENT)) {
                  var2++;
               } else {
                  if (var4.is(LexerTokenType.DEDENT)) {
                     if (var2 <= 0) {
                        return;
                     }

                     var2--;
                     this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                     if (var2 == 0) {
                        return;
                     }
                     continue;
                  }

                  if (var4.is(LexerTokenType.NEWLINE) && var2 <= 0) {
                     this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                     if (!this.Xqedii_2o6grdd().is(LexerTokenType.INDENT)) {
                        return;
                     }
                     continue;
                  }
               }

               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            }
         }
      }
   }

   public void Xqedii_swtn3wk(int var1) {
      {
         int var2 = 0;

         for (int var3 = var1; var3 < this.Xqedii_2d86asd && var3 < this.Xqedii_rty1vz4.size(); var3++) {
            if (this.Xqedii_rty1vz4.get(var3).is(LexerTokenType.INDENT)) {
               var2++;
            } else if (this.Xqedii_rty1vz4.get(var3).is(LexerTokenType.DEDENT)) {
               var2--;
            }
         }

         while (!this.Xqedii_2o6grdd().is(LexerTokenType.EOF)) {
            LexerToken var4 = this.Xqedii_2o6grdd();
            if (var4.is(LexerTokenType.INDENT)) {
               var2++;
            } else if (var4.is(LexerTokenType.DEDENT)) {
               if (--var2 <= 0) {
                  this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                  if (!this.Xqedii_2o6grdd().is(LexerTokenType.DEDENT)) {
                     return;
                  }
                  continue;
               }
            } else if (var4.is(LexerTokenType.NEWLINE) && var2 <= 0) {
               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
               if (!this.Xqedii_2o6grdd().is(LexerTokenType.INDENT)) {
                  return;
               }
               continue;
            }

            this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
         }
      }
   }

   public LexerToken Xqedii_2o6grdd() {
      {
         return this.Xqedii_rty1vz4.get(Math.min(this.Xqedii_2d86asd, this.Xqedii_rty1vz4.size() - 1));
      }
   }

   public LexerToken Xqedii_1ns9xu6(int var1) {
      {
         return this.Xqedii_rty1vz4.get(Math.min(this.Xqedii_2d86asd + var1, this.Xqedii_rty1vz4.size() - 1));
      }
   }

   public LexerToken Xqedii_my78stx() {
      {
         LexerToken var1 = this.Xqedii_2o6grdd();
         if (!var1.is(LexerTokenType.EOF)) {
            this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
         }

         return var1;
      }
   }

   public Xqedii_3vsk7zm Xqedii_3sn28jz(LexerToken var1, String var2) {
      {
         return new Xqedii_3vsk7zm(var2, var1.line(), var1.column());
      }
   }

   public boolean Xqedii_3i85ij1(String var1) {
      {
         return this.Xqedii_2o6grdd().isWord(var1);
      }
   }

   public boolean Xqedii_g1933gx(String var1) {
      if (this.Xqedii_3i85ij1(var1)) {
         this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
         return true;
      } else {
         return false;
      }
   }

   public void Xqedii_2ugqkeg(String var1, String var2) {
      if (!this.Xqedii_g1933gx(var1)) {
         throw this.Xqedii_3sn28jz(this.Xqedii_2o6grdd(), Xqedii_2pzpvqkaa0cc4(var1, var2, this.Xqedii_2o6grdd().describe()));
      }
   }

   public boolean Xqedii_3kqvbrx(String var1) {
      {
         return this.Xqedii_2o6grdd().isSymbol(var1);
      }
   }

   public boolean Xqedii_1s3d88e(String var1) {
      if (this.Xqedii_3kqvbrx(var1)) {
         this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
         return true;
      } else {
         return false;
      }
   }

   public void Xqedii_3cuz73k(String var1, String var2) {
      if (!this.Xqedii_1s3d88e(var1)) {
         throw this.Xqedii_3sn28jz(this.Xqedii_2o6grdd(), Xqedii_1cefcnyi2rc0f(var1, var2, this.Xqedii_2o6grdd().describe()));
      }
   }

   public String Xqedii_34yikyz(String var1) {
      {
         LexerToken var2 = this.Xqedii_2o6grdd();
         if (!var2.is(LexerTokenType.NAME)) {
            throw this.Xqedii_3sn28jz(var2, Xqedii_11usblxca3255(var1, var2.describe()));
         } else {
            this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            return var2.text();
         }
      }
   }

   public String Xqedii_swi2jwl(String var1) {
      {
         LexerToken var2 = this.Xqedii_2o6grdd();
         if (!var2.is(LexerTokenType.STRING)) {
            throw this.Xqedii_3sn28jz(var2, Xqedii_1taq5bcwpzh1z(var1, var2.describe()));
         } else {
            this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            return Xqedii_xm7zknp(var2.text());
         }
      }
   }

   public boolean Xqedii_32zt7zc() {
      {
         return ((!this.Xqedii_2o6grdd().is(LexerTokenType.NEWLINE)
               && !this.Xqedii_2o6grdd().is(LexerTokenType.EOF)
               && !this.Xqedii_2o6grdd().is(LexerTokenType.DEDENT)
            ? 0
            : 1) != 0);
      }
   }

   public void Xqedii_2n6e3pq() {
      if (this.Xqedii_2o6grdd().is(LexerTokenType.NEWLINE)) {
         this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
      } else if (!this.Xqedii_2o6grdd().is(LexerTokenType.EOF) && !this.Xqedii_2o6grdd().is(LexerTokenType.DEDENT)) {
         throw this.Xqedii_3sn28jz(this.Xqedii_2o6grdd(), Xqedii_3be94jvrxos1a(this.Xqedii_2o6grdd().describe()));
      }
   }

   public void Xqedii_hw7pztw() {
      {
         LexerToken var1 = this.Xqedii_2o6grdd();
         String var2 = this.Xqedii_34yikyz(
               "on, every, command, function, var or page"
            )
            .toLowerCase(Locale.ROOT);
         int var4 = -1;
         switch (var2.hashCode()) {
            case 3551:
               if (var2.equals("on")) {
                  var4 = 3;
               }
               break;
            case 99333:
               if (var2.equals("def")) {
                  var4 = 8;
               }
               break;
            case 107035:
               if (var2.equals("let")) {
                  var4 = 2;
               }
               break;
            case 116519:
               if (var2.equals("var")) {
                  var4 = 1;
               }
               break;
            case 3433103:
               if (var2.equals("page")) {
                  var4 = 0;
               }
               break;
            case 3648314:
               if (var2.equals("when")) {
                  var4 = 4;
               }
               break;
            case 96891675:
               if (var2.equals("every")) {
                  var4 = 5;
               }
               break;
            case 950394699:
               if (var2.equals("command")) {
                  var4 = 6;
               }
               break;
            case 1380938712:
               if (var2.equals("function")) {
                  var4 = 7;
               }
         }

         switch (var4) {
            case 0:
               this.Xqedii_d4hvym9.Xqedii_lp3i5aj = this.Xqedii_swi2jwl(
                  "the page name"
               );
               this.Xqedii_2n6e3pq();
               break;
            case 1:
            case 2:
               String var15 = this.Xqedii_34yikyz(
                  "a variable name"
               );
               if (Xqedii_2d07ocz.Xqedii_2b8p7zx(var15)) {
                  throw this.Xqedii_3sn28jz(var1, Xqedii_36dple85j8emh(var15));
               }

               Xqedii_ev3jp8w var18 = null;
               if (this.Xqedii_1s3d88e("=")) {
                  var18 = this.Xqedii_3nv6toq();
               }

               this.Xqedii_d4hvym9.Xqedii_2buerqn.put(var15, (Xqedii_ev3jp8w)(var18 == null ? new Xqedii_1z9wnlh(null, var1.line()) : var18));
               this.Xqedii_2n6e3pq();
               break;
            case 3:
            case 4:
               this.Xqedii_1bly2od(var1);
               break;
            case 5:
               this.Xqedii_1okvqlt = this.Xqedii_1okvqlt + 1;
               Xqedii_ev3jp8w var14 = this.Xqedii_3nv6toq();
               this.Xqedii_1okvqlt = this.Xqedii_1okvqlt - 1;
               if (!(var14 instanceof Xqedii_1z9wnlh var17) || !(var17.Xqedii_2azibg0 instanceof Double var19)) {
                  throw this.Xqedii_3sn28jz(
                     var1,
                     "every needs a plain number, like: every 2 seconds:"
                  );
               }

               double var20 = this.Xqedii_1qolmih(true);
               String var10 = this.Xqedii_g1933gx("in")
                  ? this.Xqedii_swi2jwl("the page name")
                  : null;
               Xqedii_3tcqkw2 var11 = new Xqedii_3tcqkw2(
                  this.Xqedii_d4hvym9, "every", null, var10, var1.line()
               );
               var11.Xqedii_3gg5za6 = Math.max(1L, Math.round(var19 * var20));
               var11.Xqedii_fw60fwr = this.Xqedii_2vw9h4x();
               this.Xqedii_d4hvym9.Xqedii_39uxme9.add(var11);
               break;
            case 6:
               String var12;
               if (this.Xqedii_1s3d88e("/")) {
                  var12 = this.Xqedii_34yikyz(
                     "the command name"
                  );
               } else if (this.Xqedii_2o6grdd().is(LexerTokenType.STRING)) {
                  var12 = this.Xqedii_swi2jwl(
                     "the command name"
                  );
               } else {
                  var12 = this.Xqedii_34yikyz(
                     "the command name, like /shop"
                  );
               }

               var12 = var12.startsWith("/")
                  ? var12.substring(1)
                  : var12;
               Xqedii_3tcqkw2 var16 = new Xqedii_3tcqkw2(
                  this.Xqedii_d4hvym9, "command", null, null, var1.line()
               );
               var16.Xqedii_ne1a52s = var12.toLowerCase(Locale.ROOT);
               if (this.Xqedii_g1933gx("permission")) {
                  var16.Xqedii_3al7qe9 = this.Xqedii_swi2jwl(
                     "the permission"
                  );
               }

               var16.Xqedii_fw60fwr = this.Xqedii_2vw9h4x();
               this.Xqedii_d4hvym9.Xqedii_39uxme9.add(var16);
               break;
            case 7:
            case 8:
               String var5 = this.Xqedii_34yikyz(
                  "the function name"
               );
               if (Xqedii_1iw7g71.Xqedii_2ln2q92(var5)) {
                  throw this.Xqedii_3sn28jz(var1, Xqedii_22q9n7u7y5jf2(var5));
               }

               if (this.Xqedii_d4hvym9.Xqedii_3j5g4ep.containsKey(var5)) {
                  throw this.Xqedii_3sn28jz(var1, Xqedii_3vlnfzljnwyca(var5));
               }

               this.Xqedii_3cuz73k(
                  "(",
                  "after the function name"
               );
               ArrayList var6 = new ArrayList();
               if (!this.Xqedii_3kqvbrx(")")) {
                  do {
                     var6.add(
                        this.Xqedii_34yikyz(
                           "a parameter name"
                        )
                     );
                  } while (this.Xqedii_1s3d88e(","));
               }

               this.Xqedii_3cuz73k(
                  ")",
                  "after the parameters"
               );
               Xqedii_2x2kkmf var7 = new Xqedii_2x2kkmf(var5, (String[])var6.toArray(new String[0]), var1.line());
               this.Xqedii_d4hvym9.Xqedii_3j5g4ep.put(var5, var7);
               var7.Xqedii_3476x5s = this.Xqedii_2vw9h4x();
               break;
            default:
               throw this.Xqedii_3sn28jz(var1, Xqedii_11clkzaey29tn(var1.text()));
         }
      }
   }

   public double Xqedii_1qolmih(boolean var1) {
      if (!this.Xqedii_2o6grdd().is(LexerTokenType.NAME)) {
         if (var1) {
            return Double.longBitsToDouble(4607182418800017408L);
         } else {
            throw this.Xqedii_3sn28jz(
               this.Xqedii_2o6grdd(),
               "Expected ticks, seconds or minutes"
            );
         }
      } else {
         String var2 = this.Xqedii_2o6grdd().text().toLowerCase(Locale.ROOT);
         int var6 = -1;
         switch (var2.hashCode()) {
            case -1074026988:
               if (var2.equals("minute")) {
                  var6 = 8;
               }
               break;
            case -906279820:
               if (var2.equals("second")) {
                  var6 = 3;
               }
               break;
            case 104:
               if (var2.equals("h")) {
                  var6 = 15;
               }
               break;
            case 109:
               if (var2.equals("m")) {
                  var6 = 12;
               }
               break;
            case 115:
               if (var2.equals("s")) {
                  var6 = 7;
               }
               break;
            case 116:
               if (var2.equals("t")) {
                  var6 = 2;
               }
               break;
            case 108114:
               if (var2.equals("min")) {
                  var6 = 10;
               }
               break;
            case 113745:
               if (var2.equals("sec")) {
                  var6 = 5;
               }
               break;
            case 3208676:
               if (var2.equals("hour")) {
                  var6 = 13;
               }
               break;
            case 3351649:
               if (var2.equals("mins")) {
                  var6 = 11;
               }
               break;
            case 3526210:
               if (var2.equals("secs")) {
                  var6 = 6;
               }
               break;
            case 3559837:
               if (var2.equals("tick")) {
                  var6 = 0;
               }
               break;
            case 99469071:
               if (var2.equals("hours")) {
                  var6 = 14;
               }
               break;
            case 110355062:
               if (var2.equals("ticks")) {
                  var6 = 1;
               }
               break;
            case 1064901855:
               if (var2.equals("minutes")) {
                  var6 = 9;
               }
               break;
            case 1970096767:
               if (var2.equals("seconds")) {
                  var6 = 4;
               }
         }
         double var3 = switch (var6) {
            case 0, 1, 2 -> Double.longBitsToDouble(4607182418800017408L);
            case 3, 4, 5, 6, 7 -> Double.longBitsToDouble(4626322717216342016L);
            case 8, 9, 10, 11, 12 -> Double.longBitsToDouble(4652992471259676672L);
            case 13, 14, 15 -> Double.longBitsToDouble(4679684215535566848L);
            default -> Double.longBitsToDouble(-4616189618054758400L);
         };
         if (var3 < Double.longBitsToDouble(0L)) {
            if (var1) {
               return Double.longBitsToDouble(4607182418800017408L);
            } else {
               throw this.Xqedii_3sn28jz(this.Xqedii_2o6grdd(), Xqedii_dqobhk5cwls4(this.Xqedii_2o6grdd().text()));
            }
         } else {
            this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            return var3;
         }
      }
   }

   public void Xqedii_1bly2od(LexerToken var1) {
      {
         String var2 = this.Xqedii_34yikyz(
               "an event, like click or open"
            )
            .toLowerCase(Locale.ROOT);
         int var4 = 0;
         int var6 = -1;
         switch (var2.hashCode()) {
            case -1349119146:
               if (var2.equals("cursor")) {
                  var6 = 16;
               }
               break;
            case -1263184552:
               if (var2.equals("opening")) {
                  var6 = 5;
               }
               break;
            case -907680051:
               if (var2.equals("scroll")) {
                  var6 = 9;
               }
               break;
            case -891535336:
               if (var2.equals("submit")) {
                  var6 = 19;
               }
               break;
            case -287575485:
               if (var2.equals("unhover")) {
                  var6 = 12;
               }
               break;
            case 106079:
               if (var2.equals("key")) {
                  var6 = 17;
               }
               break;
            case 3091764:
               if (var2.equals("drag")) {
                  var6 = 14;
               }
               break;
            case 3092207:
               if (var2.equals("drop")) {
                  var6 = 15;
               }
               break;
            case 3267882:
               if (var2.equals("join")) {
                  var6 = 1;
               }
               break;
            case 3317767:
               if (var2.equals("left")) {
                  var6 = 6;
               }
               break;
            case 3327206:
               if (var2.equals("load")) {
                  var6 = 0;
               }
               break;
            case 3417674:
               if (var2.equals("open")) {
                  var6 = 3;
               }
               break;
            case 3482191:
               if (var2.equals("quit")) {
                  var6 = 2;
               }
               break;
            case 94750088:
               if (var2.equals("click")) {
                  var6 = 8;
               }
               break;
            case 94756344:
               if (var2.equals("close")) {
                  var6 = 4;
               }
               break;
            case 96667352:
               if (var2.equals("enter")) {
                  var6 = 11;
               }
               break;
            case 99469628:
               if (var2.equals("hover")) {
                  var6 = 10;
               }
               break;
            case 100358090:
               if (var2.equals("input")) {
                  var6 = 18;
               }
               break;
            case 102846135:
               if (var2.equals("leave")) {
                  var6 = 13;
               }
               break;
            case 108511772:
               if (var2.equals("right")) {
                  var6 = 7;
               }
         }

         String var3;
         switch (var6) {
            case 0:
            case 1:
            case 2:
               var3 = var2;
               break;
            case 3:
            case 4:
            case 5:
               var3 = var2;
               var4 = 1;
               break;
            case 6:
            case 7:
               this.Xqedii_2ugqkeg("click", Xqedii_l45ya4ys9dux(var2));
               var3 = Xqedii_34vwkggyee3vu(var2);
               break;
            case 8:
               var3 = "click";
               break;
            case 9:
               var3 = this.Xqedii_g1933gx("up")
                  ? "scroll up"
                  : (
                     this.Xqedii_g1933gx("down")
                        ? "scroll down"
                        : "scroll"
                  );
               break;
            case 10:
            case 11:
               var3 = "hover";
               break;
            case 12:
            case 13:
               var3 = "unhover";
               break;
            case 14:
               var3 = this.Xqedii_g1933gx("start")
                  ? "drag start"
                  : (
                     this.Xqedii_g1933gx("end")
                        ? "drop"
                        : (
                           this.Xqedii_g1933gx("move")
                              ? "drag"
                              : "drag"
                        )
                  );
               break;
            case 15:
               var3 = "drop";
               break;
            case 16:
               this.Xqedii_2ugqkeg(
                  "move",
                  "after cursor"
               );
               var3 = "cursor move";
               break;
            case 17:
               var3 = "key";
               break;
            case 18:
            case 19:
               var3 = "input";
               break;
            default:
               throw this.Xqedii_3sn28jz(var1, Xqedii_3svs56vtzx5ai(var2));
         }

         String var5 = null;
         if (this.Xqedii_2o6grdd().is(LexerTokenType.STRING)) {
            var5 = this.Xqedii_swi2jwl("the element id");
         }

         String var8 = null;
         if (var4 != 0) {
            var8 = var5;
            var5 = null;
         }

         if (this.Xqedii_g1933gx("in")) {
            var8 = this.Xqedii_swi2jwl("the page name");
         }

         Xqedii_3tcqkw2 var7 = new Xqedii_3tcqkw2(this.Xqedii_d4hvym9, var3, var5, var8, var1.line());
         var7.Xqedii_fw60fwr = this.Xqedii_2vw9h4x();
         this.Xqedii_d4hvym9.Xqedii_39uxme9.add(var7);
      }
   }

   public Xqedii_3nlck21[] Xqedii_2vw9h4x() {
      {
         this.Xqedii_3cuz73k(
            ":",
            "at the end of this line"
         );
         if (!this.Xqedii_2o6grdd().is(LexerTokenType.NEWLINE)) {
            Xqedii_3nlck21 var5 = this.Xqedii_t3irkxz();
            Xqedii_3nlck21[] var10000 = new Xqedii_3nlck21[1];
            var10000[0] = var5;
            return var10000;
         } else {
            this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            if (!this.Xqedii_2o6grdd().is(LexerTokenType.INDENT)) {
               throw this.Xqedii_3sn28jz(
                  this.Xqedii_2o6grdd(),
                  "The lines inside this block must be indented (start them with spaces)"
               );
            } else {
               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
               ArrayList var1 = new ArrayList();

               while (!this.Xqedii_2o6grdd().is(LexerTokenType.DEDENT) && !this.Xqedii_2o6grdd().is(LexerTokenType.EOF)) {
                  if (this.Xqedii_2o6grdd().is(LexerTokenType.NEWLINE)) {
                     this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                  } else {
                     int var2 = this.Xqedii_2d86asd;

                     try {
                        if (this.Xqedii_2o6grdd().is(LexerTokenType.INDENT)) {
                           throw this.Xqedii_3sn28jz(
                              this.Xqedii_2o6grdd(),
                              "This line is indented more than the line above it"
                           );
                        }

                        var1.add(this.Xqedii_t3irkxz());
                     } catch (Xqedii_3vsk7zm var4) {
                        if (this.Xqedii_1ws39dv == null) {
                           throw var4;
                        }

                        this.Xqedii_1ws39dv.add(var4);
                        this.Xqedii_krrt9j0(var2);
                     }
                  }
               }

               if (this.Xqedii_2o6grdd().is(LexerTokenType.DEDENT)) {
                  this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
               }

               return (Xqedii_3nlck21[])var1.toArray(new Xqedii_3nlck21[0]);
            }
         }
      }
   }

   public Xqedii_ev3jp8w[] Xqedii_a87b8l9(Xqedii_ev3jp8w... var1) {
      {
         return var1;
      }
   }

   public Xqedii_3nlck21 Xqedii_3vze4js(int var1, int var2, Xqedii_ev3jp8w... var3) {
      {
         this.Xqedii_2n6e3pq();
         return new Xqedii_vc9adda(var1, var3, var2);
      }
   }

   public Xqedii_ev3jp8w Xqedii_mdc50q8(String var1) {
      {
         return this.Xqedii_g1933gx(var1) ? this.Xqedii_3nv6toq() : null;
      }
   }

   public Xqedii_3nlck21 Xqedii_t3irkxz() {
      {
         LexerToken var1 = this.Xqedii_2o6grdd();
         int var2 = var1.line();
         if ((
               var1.isWord("else")
                  || var1.isWord("elif")
                  || var1.isWord("otherwise")
            )
            && !Xqedii_2w4v4yr(this.Xqedii_1ns9xu6(1))) {
            throw this.Xqedii_3sn28jz(var1, Xqedii_1onahknpkpj9v(var1.text()));
         } else if (var1.is(LexerTokenType.NAME)
            && Xqedii_200w0a7.contains(var1.text().toLowerCase(Locale.ROOT))
            && !Xqedii_2w4v4yr(this.Xqedii_1ns9xu6(1))) {
            this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            return this.Xqedii_33hc29h(var1.text().toLowerCase(Locale.ROOT), var1, var2);
         } else {
            return this.Xqedii_118rlff(var1, var2);
         }
      }
   }

   public static boolean Xqedii_2w4v4yr(LexerToken var0) {
      {
         return ((!var0.is(LexerTokenType.SYMBOL)
               || !Xqedii_3ct77sg.contains(var0.text()) && !var0.text().equals(".")
            ? 0
            : 1) != 0);
      }
   }

   public Xqedii_3nlck21 Xqedii_118rlff(LexerToken var1, int var2) {
      {
         Xqedii_ev3jp8w var3 = this.Xqedii_3nv6toq();
         if (this.Xqedii_2o6grdd().is(LexerTokenType.SYMBOL) && Xqedii_3ct77sg.contains(this.Xqedii_2o6grdd().text())) {
            String var4 = this.Xqedii_my78stx().text();
            this.Xqedii_35kxtal(var3, var1);
            Xqedii_ev3jp8w var5 = this.Xqedii_3nv6toq();
            this.Xqedii_2n6e3pq();
            int var8 = -1;
            switch (var4.hashCode()) {
               case 1363:
                  if (var4.equals("*=")) {
                     var8 = 2;
                  }
                  break;
               case 1394:
                  if (var4.equals("+=")) {
                     var8 = 0;
                  }
                  break;
               case 1456:
                  if (var4.equals("-=")) {
                     var8 = 1;
                  }
                  break;
               case 1518:
                  if (var4.equals("/=")) {
                     var8 = 3;
                  }
            }
            int var6 = switch (var8) {
               case 0 -> 1;
               case 1 -> 2;
               case 2 -> 3;
               case 3 -> 4;
               default -> 0;
            };
            return new Xqedii_2v9d3jq(var3, var6, var5, var2);
         } else if (var3 instanceof Xqedii_2ucjp8c && var1.is(LexerTokenType.NAME) && !this.Xqedii_32zt7zc()) {
            throw this.Xqedii_3sn28jz(var1, Xqedii_2br6gj5oh8vhc(var1.text(), Xqedii_180dthy(var1.text())));
         } else if (!(var3 instanceof Xqedii_3ox0q7t) && !(var3 instanceof Xqedii_3wobtjl)) {
            throw this.Xqedii_3sn28jz(
               var1,
               "This line doesn't do anything. Did you mean: set ... to ..., or a command like send, show, hide?"
            );
         } else {
            this.Xqedii_2n6e3pq();
            return new Xqedii_3kvftsk(var3, var2);
         }
      }
   }

   public static String Xqedii_180dthy(String var0) {
      {
         String var1 = var0.toLowerCase(Locale.ROOT);
         String var2 = null;
         int var3 = 3;

         for (String var5 : new TreeSet<>(Xqedii_200w0a7)) {
            int var6 = Xqedii_151x1my(var1, var5);
            if (var6 < var3) {
               var3 = var6;
               var2 = var5;
            }
         }

         return var2 == null ? "" : Xqedii_jz9avrm56e9f(var2);
      }
   }

   public static int Xqedii_151x1my(String var0, String var1) {
      {
         int[] var2 = new int[var1.length() + 1];
         int[] var3 = new int[var1.length() + 1];
         int var4 = 0;

         while (var4 <= var1.length()) {
            var2[var4] = var4++;
         }

         for (int var7 = 1; var7 <= var0.length(); var7++) {
            var3[0] = var7;

            for (int var5 = 1; var5 <= var1.length(); var5++) {
               int var6 = var0.charAt(var7 - 1)
                     == var1.charAt(var5 - 1)
                  ? 0
                  : 1;
               var3[var5] = Math.min(
                  Math.min(
                     var3[var5 - 1] + 1,
                     var2[var5] + 1
                  ),
                  var2[var5 - 1] + var6
               );
            }

            int[] var8 = var2;
            var2 = var3;
            var3 = var8;
         }

         return var2[var1.length()];
      }
   }

   public void Xqedii_35kxtal(Xqedii_ev3jp8w var1, LexerToken var2) {
      if (!var1.Xqedii_25ddebp()) {
         throw this.Xqedii_3sn28jz(
            var2,
            "You can only change a variable or a property here, like: set text of \"title\" to \"Hi\""
         );
      }
   }

   public Xqedii_3nlck21 Xqedii_33hc29h(String var1, LexerToken var2, int var3) {
      {
         int var5 = -1;
         switch (var1.hashCode()) {
            case -1618876223:
               if (var1.equals("broadcast")) {
                  var5 = 15;
               }
               break;
            case -1422950858:
               if (var1.equals("action")) {
                  var5 = 29;
               }
               break;
            case -1367724422:
               if (var1.equals("cancel")) {
                  var5 = 41;
               }
               break;
            case -1360201941:
               if (var1.equals("teleport")) {
                  var5 = 30;
               }
               break;
            case -1352294148:
               if (var1.equals("create")) {
                  var5 = 27;
               }
               break;
            case -1335458389:
               if (var1.equals("delete")) {
                  var5 = 5;
               }
               break;
            case -934610812:
               if (var1.equals("remove")) {
                  var5 = 4;
               }
               break;
            case -934531685:
               if (var1.equals("repeat")) {
                  var5 = 35;
               }
               break;
            case -934437708:
               if (var1.equals("resize")) {
                  var5 = 10;
               }
               break;
            case -934396624:
               if (var1.equals("return")) {
                  var5 = 39;
               }
               break;
            case -907680051:
               if (var1.equals("scroll")) {
                  var5 = 25;
               }
               break;
            case -868304044:
               if (var1.equals("toggle")) {
                  var5 = 8;
               }
               break;
            case -856935711:
               if (var1.equals("animate")) {
                  var5 = 11;
               }
               break;
            case -567202649:
               if (var1.equals("continue")) {
                  var5 = 38;
               }
               break;
            case -481221037:
               if (var1.equals("uncancel")) {
                  var5 = 42;
               }
               break;
            case 3357:
               if (var1.equals("if")) {
                  var5 = 33;
               }
               break;
            case 96417:
               if (var1.equals("add")) {
                  var5 = 3;
               }
               break;
            case 101577:
               if (var1.equals("for")) {
                  var5 = 36;
               }
               break;
            case 107035:
               if (var1.equals("let")) {
                  var5 = 0;
               }
               break;
            case 107332:
               if (var1.equals("log")) {
                  var5 = 43;
               }
               break;
            case 113291:
               if (var1.equals("run")) {
                  var5 = 20;
               }
               break;
            case 113762:
               if (var1.equals("set")) {
                  var5 = 2;
               }
               break;
            case 116519:
               if (var1.equals("var")) {
                  var5 = 1;
               }
               break;
            case 3173137:
               if (var1.equals("give")) {
                  var5 = 31;
               }
               break;
            case 3202370:
               if (var1.equals("hide")) {
                  var5 = 7;
               }
               break;
            case 3357649:
               if (var1.equals("move")) {
                  var5 = 9;
               }
               break;
            case 3417674:
               if (var1.equals("open")) {
                  var5 = 23;
               }
               break;
            case 3443508:
               if (var1.equals("play")) {
                  var5 = 18;
               }
               break;
            case 3526536:
               if (var1.equals("send")) {
                  var5 = 12;
               }
               break;
            case 3529469:
               if (var1.equals("show")) {
                  var5 = 6;
               }
               break;
            case 3540994:
               if (var1.equals("stop")) {
                  var5 = 40;
               }
               break;
            case 3556273:
               if (var1.equals("tell")) {
                  var5 = 14;
               }
               break;
            case 3641717:
               if (var1.equals("wait")) {
                  var5 = 32;
               }
               break;
            case 94001407:
               if (var1.equals("break")) {
                  var5 = 37;
               }
               break;
            case 94750088:
               if (var1.equals("click")) {
                  var5 = 28;
               }
               break;
            case 94756189:
               if (var1.equals("clone")) {
                  var5 = 26;
               }
               break;
            case 94756344:
               if (var1.equals("close")) {
                  var5 = 24;
               }
               break;
            case 106934957:
               if (var1.equals("print")) {
                  var5 = 44;
               }
               break;
            case 109627663:
               if (var1.equals("sound")) {
                  var5 = 19;
               }
               break;
            case 110371416:
               if (var1.equals("title")) {
                  var5 = 17;
               }
               break;
            case 113101617:
               if (var1.equals("while")) {
                  var5 = 34;
               }
               break;
            case 198298141:
               if (var1.equals("actionbar")) {
                  var5 = 16;
               }
               break;
            case 950394699:
               if (var1.equals("command")) {
                  var5 = 21;
               }
               break;
            case 951510359:
               if (var1.equals("console")) {
                  var5 = 22;
               }
               break;
            case 954925063:
               if (var1.equals("message")) {
                  var5 = 13;
               }
         }

         switch (var5) {
            case 0:
            case 1:
               String var33 = this.Xqedii_34yikyz(
                  "a variable name"
               );
               if (Xqedii_2d07ocz.Xqedii_2b8p7zx(var33)) {
                  throw this.Xqedii_3sn28jz(var2, Xqedii_32yx4q6co7il9(var33));
               }

               Xqedii_ev3jp8w var51 = this.Xqedii_1s3d88e("=")
                  ? this.Xqedii_3nv6toq()
                  : null;
               this.Xqedii_2n6e3pq();
               return new Xqedii_1ral8wi(var33, var51, var3);
            case 2:
               Xqedii_ev3jp8w var32 = this.Xqedii_3nv6toq();
               this.Xqedii_35kxtal(var32, var2);
               this.Xqedii_2ugqkeg(
                  "to",
                  "(set ... to ...)"
               );
               Xqedii_ev3jp8w var50 = this.Xqedii_3nv6toq();
               this.Xqedii_2n6e3pq();
               return new Xqedii_2v9d3jq(var32, 0, var50, var3);
            case 3:
               Xqedii_ev3jp8w var31 = this.Xqedii_3nv6toq();
               this.Xqedii_2ugqkeg(
                  "to",
                  "(add ... to ...)"
               );
               Xqedii_ev3jp8w var49 = this.Xqedii_3nv6toq();
               this.Xqedii_35kxtal(var49, var2);
               this.Xqedii_2n6e3pq();
               return new Xqedii_13ezdyt(true, var31, var49, var3);
            case 4:
               Xqedii_ev3jp8w var30 = this.Xqedii_3nv6toq();
               if (this.Xqedii_g1933gx("from")) {
                  Xqedii_ev3jp8w var48 = this.Xqedii_3nv6toq();
                  this.Xqedii_35kxtal(var48, var2);
                  this.Xqedii_2n6e3pq();
                  return new Xqedii_13ezdyt(false, var30, var48, var3);
               }

               this.Xqedii_2n6e3pq();
               return new Xqedii_uv884vm(var30, var3);
            case 5:
               Xqedii_ev3jp8w var29 = this.Xqedii_3nv6toq();
               this.Xqedii_2n6e3pq();
               return new Xqedii_uv884vm(var29, var3);
            case 6:
               int var92 = 1;
               Xqedii_ev3jp8w[] var122 = new Xqedii_ev3jp8w[1];
               var122[0] = this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var92, var3, var122);
            case 7:
               int var91 = 2;
               Xqedii_ev3jp8w[] var121 = new Xqedii_ev3jp8w[1];
               var121[0] = this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var91, var3, var121);
            case 8:
               int var90 = 3;
               Xqedii_ev3jp8w[] var120 = new Xqedii_ev3jp8w[1];
               var120[0] = this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var90, var3, var120);
            case 9:
               Xqedii_ev3jp8w var28 = this.Xqedii_3nv6toq();
               if (this.Xqedii_g1933gx("into")) {
                  Xqedii_ev3jp8w var47 = this.Xqedii_3nv6toq();
                  int var89 = 32;
                  Xqedii_ev3jp8w[] var119 = new Xqedii_ev3jp8w[3];
                  var119[0] = var28;
                  var119[1] = var47;
                  var119[2] = this.Xqedii_mdc50q8(
                     "at"
                  );
                  return this.Xqedii_3vze4js(var89, var3, var119);
               }

               boolean var46 = this.Xqedii_g1933gx("by");
               if (!var46) {
                  this.Xqedii_2ugqkeg(
                     "to",
                     "(move \"id\" to x, y or move \"id\" by x, y)"
                  );
               }

               Xqedii_ev3jp8w var58 = this.Xqedii_3nv6toq();
               this.Xqedii_3cuz73k(
                  ",",
                  "between x and y"
               );
               Xqedii_ev3jp8w var61 = this.Xqedii_3nv6toq();
               Xqedii_ev3jp8w[] var62 = this.Xqedii_2bkvzcq(var3, false);
               int var88 = var46 ? 5 : 4;
               Xqedii_ev3jp8w[] var118 = new Xqedii_ev3jp8w[5];
               var118[0] = var28;
               var118[1] = var58;
               var118[2] = var61;
               var118[3] = var62[0];
               var118[4] = var62[1];
               return this.Xqedii_3vze4js(var88, var3, var118);
            case 10:
               Xqedii_ev3jp8w var27 = this.Xqedii_3nv6toq();
               this.Xqedii_2ugqkeg(
                  "to",
                  "(resize \"id\" to width, height)"
               );
               Xqedii_ev3jp8w var45 = this.Xqedii_3nv6toq();
               this.Xqedii_3cuz73k(
                  ",",
                  "between width and height"
               );
               Xqedii_ev3jp8w var57 = this.Xqedii_3nv6toq();
               Xqedii_ev3jp8w[] var60 = this.Xqedii_2bkvzcq(var3, false);
               int var87 = 6;
               Xqedii_ev3jp8w[] var117 = new Xqedii_ev3jp8w[5];
               var117[0] = var27;
               var117[1] = var45;
               var117[2] = var57;
               var117[3] = var60[0];
               var117[4] = var60[1];
               return this.Xqedii_3vze4js(var87, var3, var117);
            case 11:
               Xqedii_ev3jp8w var26 = this.Xqedii_3nv6toq();
               LexerToken var44 = this.Xqedii_2o6grdd();
               String var56 = var44.is(LexerTokenType.NAME)
                  ? var44.text().toLowerCase(Locale.ROOT)
                  : "";
               if (!Xqedii_4h0tx6n.contains(var56)) {
                  throw this.Xqedii_3sn28jz(
                     var44,
                     Xqedii_1a8hvczznev5x(
                        String.join(", ", Xqedii_4h0tx6n), var44.describe()
                     )
                  );
               }

               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
               this.Xqedii_2ugqkeg("to", Xqedii_3g81ocn1cm0ob(var56));
               Xqedii_ev3jp8w var59 = this.Xqedii_3nv6toq();
               Xqedii_ev3jp8w var10 = null;
               if (var56.equals("position")
                  || var56.equals("size")) {
                  this.Xqedii_3cuz73k(
                     ",",
                     var56.equals("size")
                        ? "between width and height"
                        : "between x and y"
                  );
                  var10 = this.Xqedii_3nv6toq();
               }

               Xqedii_ev3jp8w[] var11 = this.Xqedii_2bkvzcq(var3, true);
               int var86 = 33;
               Xqedii_ev3jp8w[] var116 = new Xqedii_ev3jp8w[6];
               var116[0] = var26;
               var116[1] = new Xqedii_29jdaam(var56, var3);
               var116[2] = var59;
               var116[3] = var10;
               var116[4] = var11[0];
               var116[5] = var11[1];
               return this.Xqedii_3vze4js(var86, var3, var116);
            case 12:
            case 13:
            case 14:
               Xqedii_ev3jp8w var25 = this.Xqedii_3nv6toq();
               int var85 = 7;
               Xqedii_ev3jp8w[] var115 = new Xqedii_ev3jp8w[2];
               var115[0] = var25;
               var115[1] = this.Xqedii_mdc50q8(
                  "to"
               );
               return this.Xqedii_3vze4js(var85, var3, var115);
            case 15:
               int var84 = 8;
               Xqedii_ev3jp8w[] var114 = new Xqedii_ev3jp8w[1];
               var114[0] = this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var84, var3, var114);
            case 16:
               Xqedii_ev3jp8w var24 = this.Xqedii_3nv6toq();
               int var83 = 9;
               Xqedii_ev3jp8w[] var113 = new Xqedii_ev3jp8w[2];
               var113[0] = var24;
               var113[1] = this.Xqedii_mdc50q8(
                  "to"
               );
               return this.Xqedii_3vze4js(var83, var3, var113);
            case 17:
               Xqedii_ev3jp8w var23 = this.Xqedii_3nv6toq();
               Xqedii_ev3jp8w var43 = this.Xqedii_mdc50q8("subtitle");
               int var82 = 10;
               Xqedii_ev3jp8w[] var112 = new Xqedii_ev3jp8w[3];
               var112[0] = var23;
               var112[1] = var43;
               var112[2] = this.Xqedii_mdc50q8(
                  "to"
               );
               return this.Xqedii_3vze4js(var82, var3, var112);
            case 18:
               this.Xqedii_2ugqkeg(
                  "sound",
                  "(play sound \"name\")"
               );
               return this.Xqedii_38ata03(var3);
            case 19:
               return this.Xqedii_38ata03(var3);
            case 20:
               if (this.Xqedii_g1933gx("console")) {
                  int var81 = 13;
                  Xqedii_ev3jp8w[] var111 = new Xqedii_ev3jp8w[1];
                  var111[0] = this.Xqedii_3nv6toq();
                  return this.Xqedii_3vze4js(var81, var3, var111);
               }

               this.Xqedii_g1933gx("command");
               int var80 = 12;
               Xqedii_ev3jp8w[] var110 = new Xqedii_ev3jp8w[1];
               var110[0] = this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var80, var3, var110);
            case 21:
               int var79 = 12;
               Xqedii_ev3jp8w[] var109 = new Xqedii_ev3jp8w[1];
               var109[0] = this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var79, var3, var109);
            case 22:
               int var78 = 13;
               Xqedii_ev3jp8w[] var108 = new Xqedii_ev3jp8w[1];
               var108[0] = this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var78, var3, var108);
            case 23:
               if (this.Xqedii_g1933gx("input")) {
                  int var77 = 17;
                  Xqedii_ev3jp8w[] var107 = new Xqedii_ev3jp8w[1];
                  var107[0] = this.Xqedii_3nv6toq();
                  return this.Xqedii_3vze4js(var77, var3, var107);
               }

               this.Xqedii_g1933gx("page");
               this.Xqedii_g1933gx("ui");
               Xqedii_ev3jp8w var22 = this.Xqedii_3nv6toq();
               int var42 = 0;
               if (this.Xqedii_g1933gx("as")) {
                  this.Xqedii_2ugqkeg(
                     "hud",
                     "(open \"page\" as hud)"
                  );
                  var42 = 1;
               }

               Xqedii_ev3jp8w var55 = this.Xqedii_mdc50q8("for");
               int var76 = var42 != 0 ? 15 : 14;
               Xqedii_ev3jp8w[] var106 = new Xqedii_ev3jp8w[2];
               var106[0] = var22;
               var106[1] = var55;
               return this.Xqedii_3vze4js(var76, var3, var106);
            case 24:
               if (!this.Xqedii_g1933gx("ui")) {
                  this.Xqedii_g1933gx("page");
               }

               Xqedii_ev3jp8w var21 = !this.Xqedii_32zt7zc()
                     && !this.Xqedii_3i85ij1("for")
                  ? this.Xqedii_3nv6toq()
                  : null;
               Xqedii_ev3jp8w var41 = this.Xqedii_mdc50q8("for");
               int var75 = 16;
               Xqedii_ev3jp8w[] var105 = new Xqedii_ev3jp8w[2];
               var105[0] = var41;
               var105[1] = var21;
               return this.Xqedii_3vze4js(var75, var3, var105);
            case 25:
               Xqedii_ev3jp8w var20 = this.Xqedii_3nv6toq();
               if (this.Xqedii_g1933gx("by")) {
                  Xqedii_ev3jp8w var40 = this.Xqedii_3nv6toq();
                  this.Xqedii_3cuz73k(
                     ",",
                     "between x and y"
                  );
                  int var74 = 18;
                  Xqedii_ev3jp8w[] var104 = new Xqedii_ev3jp8w[3];
                  var104[0] = var20;
                  var104[1] = var40;
                  var104[2] = this.Xqedii_3nv6toq();
                  return this.Xqedii_3vze4js(var74, var3, var104);
               } else {
                  this.Xqedii_2ugqkeg(
                     "to",
                     "(scroll \"id\" to top, to bottom, to x, y or by x, y)"
                  );
                  if (this.Xqedii_g1933gx("top")) {
                     int var73 = 20;
                     Xqedii_ev3jp8w[] var103 = new Xqedii_ev3jp8w[1];
                     var103[0] = var20;
                     return this.Xqedii_3vze4js(var73, var3, var103);
                  } else if (this.Xqedii_g1933gx("bottom")) {
                     int var72 = 21;
                     Xqedii_ev3jp8w[] var102 = new Xqedii_ev3jp8w[1];
                     var102[0] = var20;
                     return this.Xqedii_3vze4js(var72, var3, var102);
                  } else {
                     if (this.Xqedii_g1933gx("element")) {
                        int var71 = 30;
                        Xqedii_ev3jp8w[] var101 = new Xqedii_ev3jp8w[2];
                        var101[0] = var20;
                        var101[1] = this.Xqedii_3nv6toq();
                        return this.Xqedii_3vze4js(var71, var3, var101);
                     }

                     Xqedii_ev3jp8w var39 = this.Xqedii_3nv6toq();
                     this.Xqedii_3cuz73k(
                        ",",
                        "between x and y"
                     );
                     int var70 = 19;
                     Xqedii_ev3jp8w[] var100 = new Xqedii_ev3jp8w[3];
                     var100[0] = var20;
                     var100[1] = var39;
                     var100[2] = this.Xqedii_3nv6toq();
                     return this.Xqedii_3vze4js(var70, var3, var100);
                  }
               }
            case 26:
               Xqedii_ev3jp8w var19 = this.Xqedii_3nv6toq();
               this.Xqedii_2ugqkeg(
                  "as",
                  "(clone \"id\" as \"new_id\")"
               );
               Xqedii_ev3jp8w var38 = this.Xqedii_3nv6toq();
               Xqedii_ev3jp8w var54 = this.Xqedii_mdc50q8("into");
               int var69 = 22;
               Xqedii_ev3jp8w[] var99 = new Xqedii_ev3jp8w[4];
               var99[0] = var19;
               var99[1] = var38;
               var99[2] = var54;
               var99[3] = this.Xqedii_mdc50q8(
                  "at"
               );
               return this.Xqedii_3vze4js(var69, var3, var99);
            case 27:
               return this.Xqedii_2eglftz(var3);
            case 28:
               int var68 = 23;
               Xqedii_ev3jp8w[] var98 = new Xqedii_ev3jp8w[1];
               var98[0] = this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var68, var3, var98);
            case 29:
               Xqedii_ev3jp8w var18 = this.Xqedii_3nv6toq();
               Xqedii_ev3jp8w var37 = this.Xqedii_32zt7zc() ? null : this.Xqedii_3nv6toq();
               int var67 = 24;
               Xqedii_ev3jp8w[] var97 = new Xqedii_ev3jp8w[2];
               var97[0] = var18;
               var97[1] = var37;
               return this.Xqedii_3vze4js(var67, var3, var97);
            case 30:
               this.Xqedii_g1933gx("to");
               this.Xqedii_1okvqlt = this.Xqedii_1okvqlt + 1;
               Xqedii_ev3jp8w var17 = this.Xqedii_3nv6toq();
               this.Xqedii_3cuz73k(
                  ",",
                  "between x and y"
               );
               Xqedii_ev3jp8w var36 = this.Xqedii_3nv6toq();
               this.Xqedii_3cuz73k(
                  ",",
                  "between y and z"
               );
               Xqedii_ev3jp8w var53 = this.Xqedii_3nv6toq();
               this.Xqedii_1okvqlt = this.Xqedii_1okvqlt - 1;
               Xqedii_ev3jp8w var9 = null;
               if (this.Xqedii_g1933gx("in")) {
                  this.Xqedii_g1933gx("world");
                  var9 = this.Xqedii_3nv6toq();
               }

               int var66 = 25;
               Xqedii_ev3jp8w[] var96 = new Xqedii_ev3jp8w[4];
               var96[0] = var17;
               var96[1] = var36;
               var96[2] = var53;
               var96[3] = var9;
               return this.Xqedii_3vze4js(var66, var3, var96);
            case 31:
               Xqedii_ev3jp8w var16 = this.Xqedii_3nv6toq();
               Xqedii_ev3jp8w var35 = this.Xqedii_32zt7zc() ? null : this.Xqedii_3nv6toq();
               if (var16 instanceof Xqedii_1z9wnlh var52 && var52.Xqedii_2azibg0 instanceof Double && var35 != null) {
                  int var65 = 26;
                  Xqedii_ev3jp8w[] var95 = new Xqedii_ev3jp8w[2];
                  var95[0] = var35;
                  var95[1] = var16;
                  return this.Xqedii_3vze4js(var65, var3, var95);
               }

               int var64 = 26;
               Xqedii_ev3jp8w[] var94 = new Xqedii_ev3jp8w[2];
               var94[0] = var16;
               var94[1] = var35;
               return this.Xqedii_3vze4js(var64, var3, var94);
            case 32:
               Xqedii_ev3jp8w var15 = this.Xqedii_3nv6toq();
               double var34 = this.Xqedii_1qolmih(true);
               this.Xqedii_2n6e3pq();
               return new Xqedii_1jxgido(var15, var34, var3);
            case 33:
               return this.Xqedii_1fsxdm6(var3);
            case 34:
               Xqedii_ev3jp8w var14 = this.Xqedii_3nv6toq();
               return new Xqedii_3vayq8t(var14, this.Xqedii_2txbn32(), var3);
            case 35:
               Xqedii_ev3jp8w var13 = this.Xqedii_3nv6toq();
               this.Xqedii_g1933gx("times");
               return new Xqedii_1mitiba(var13, this.Xqedii_2txbn32(), var3);
            case 36:
               this.Xqedii_g1933gx("each");
               String var12 = this.Xqedii_34yikyz(
                  "a variable name (for item in list:)"
               );
               this.Xqedii_2ugqkeg(
                  "in",
                  "(for item in list:)"
               );
               Xqedii_ev3jp8w var7 = this.Xqedii_3nv6toq();
               Xqedii_ev3jp8w var8 = this.Xqedii_mdc50q8("to");
               return new Xqedii_2uv99kq(var12, var7, var8, this.Xqedii_2txbn32(), var3);
            case 37:
            case 38:
               if (this.Xqedii_38gkvzl == 0) {
                  throw this.Xqedii_3sn28jz(var2, Xqedii_1bl3f0i9524f7(var1));
               }

               this.Xqedii_2n6e3pq();
               return new Xqedii_1drddta(
                  var1.equals("break")
                     ? 1
                     : 2,
                  null,
                  var3
               );
            case 39:
               Xqedii_ev3jp8w var6 = this.Xqedii_32zt7zc() ? null : this.Xqedii_3nv6toq();
               this.Xqedii_2n6e3pq();
               return new Xqedii_1drddta(3, var6, var3);
            case 40:
               if (!this.Xqedii_g1933gx("animation")
                  && !this.Xqedii_g1933gx("animations")
                  && !this.Xqedii_g1933gx("animating")) {
                  this.Xqedii_2n6e3pq();
                  return new Xqedii_1drddta(3, null, var3);
               }

               this.Xqedii_g1933gx("of");
               int var63 = 34;
               Xqedii_ev3jp8w[] var93 = new Xqedii_ev3jp8w[1];
               var93[0] = this.Xqedii_32zt7zc() ? null : this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var63, var3, var93);
            case 41:
               this.Xqedii_g1933gx("event");
               return this.Xqedii_3vze4js(28, var3);
            case 42:
               this.Xqedii_g1933gx("event");
               return this.Xqedii_3vze4js(29, var3);
            case 43:
            case 44:
               int var10001 = 27;
               Xqedii_ev3jp8w[] var10003 = new Xqedii_ev3jp8w[1];
               var10003[0] = this.Xqedii_3nv6toq();
               return this.Xqedii_3vze4js(var10001, var3, var10003);
            default:
               throw this.Xqedii_3sn28jz(var2, Xqedii_3l3a4kjaqi84z(var1));
         }
      }
   }

   public Xqedii_ev3jp8w[] Xqedii_2bkvzcq(int var1, boolean var2) {
      {
         Object var3 = null;
         Xqedii_ev3jp8w var4 = null;
         if (this.Xqedii_g1933gx("over")) {
            Xqedii_ev3jp8w var5 = this.Xqedii_3nv6toq();
            double var6 = this.Xqedii_1qolmih(true);
            var3 = var6 == Double.longBitsToDouble(4607182418800017408L)
               ? var5
               : new Xqedii_3l5gjg9(3, var5, new Xqedii_1z9wnlh(var6, var1), var1);
         }

         if (this.Xqedii_g1933gx("with")) {
            LexerToken var8 = this.Xqedii_2o6grdd();
            var4 = this.Xqedii_3nv6toq();
            if (var8.is(LexerTokenType.STRING) && var4 instanceof Xqedii_29jdaam && Xqedii_2awqdkk.Xqedii_1uh18ch(var8.text()) == null) {
               throw this.Xqedii_3sn28jz(var8, Xqedii_2awqdkk.Xqedii_pzxldhl(var8.text()));
            }
         }

         if (var3 == null && (var4 != null || var2)) {
            var3 = new Xqedii_1z9wnlh(Double.longBitsToDouble(4621819117588971520L), var1);
         }

         Xqedii_ev3jp8w[] var10000 = new Xqedii_ev3jp8w[2];
         var10000[0] = (Xqedii_ev3jp8w)var3;
         var10000[1] = var4;
         return var10000;
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public Xqedii_3nlck21 Xqedii_2eglftz(int var1) {
      {
         LexerToken var2 = this.Xqedii_2o6grdd();
         if (!var2.is(LexerTokenType.NAME)) {
            throw this.Xqedii_3sn28jz(
               var2, Xqedii_149had7p0ynq4(String.join(", ", Xqedii_p2rt6hv))
            );
         } else {
            this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            ArrayList var3 = new ArrayList();
            var3.add(new Xqedii_29jdaam(var2.text().toLowerCase(Locale.ROOT), var1));
            this.Xqedii_1okvqlt = this.Xqedii_1okvqlt + 1;
            boolean var12 = false /* VF: Semaphore variable */;

            try {
               var12 = true;
               var3.add(this.Xqedii_3nv6toq());

               while (!this.Xqedii_32zt7zc()) {
                  LexerToken var4 = this.Xqedii_2o6grdd();
                  String var5 = var4.is(LexerTokenType.NAME)
                     ? var4.text().toLowerCase(Locale.ROOT)
                     : "";
                  if (!Xqedii_2xrm6um.contains(var5)) {
                     throw this.Xqedii_3sn28jz(
                        var4,
                        Xqedii_1fia4o6yk4zw7(
                           var4.describe(), String.join(", ", Xqedii_2xrm6um)
                        )
                     );
                  }

                  this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                  int var7 = -1;
                  switch (var5.hashCode()) {
                     case -1217487446:
                        if (var5.equals("hidden")) {
                           var7 = 2;
                        }
                        break;
                     case 3123:
                        if (var5.equals("at")) {
                           var7 = 0;
                        }
                        break;
                     case 3530753:
                        if (var5.equals("size")) {
                           var7 = 1;
                        }
                  }

                  switch (var7) {
                     case 0:
                        var3.add(new Xqedii_29jdaam("x", var1));
                        var3.add(this.Xqedii_3nv6toq());
                        this.Xqedii_3cuz73k(
                           ",",
                           "between x and y"
                        );
                        var3.add(new Xqedii_29jdaam("y", var1));
                        var3.add(this.Xqedii_3nv6toq());
                        break;
                     case 1:
                        Xqedii_ev3jp8w var8 = this.Xqedii_3nv6toq();
                        Xqedii_ev3jp8w var9 = this.Xqedii_1s3d88e(",")
                           ? this.Xqedii_3nv6toq()
                           : var8;
                        var3.add(new Xqedii_29jdaam("width", var1));
                        var3.add(var8);
                        var3.add(new Xqedii_29jdaam("height", var1));
                        var3.add(var9);
                        break;
                     case 2:
                        var3.add(new Xqedii_29jdaam("hidden", var1));
                        var3.add(new Xqedii_1z9wnlh(Boolean.TRUE, var1));
                        break;
                     default:
                        var3.add(new Xqedii_29jdaam(var5, var1));
                        var3.add(this.Xqedii_3nv6toq());
                  }
               }

               var12 = false;
            } finally {
               if (var12) {
                  this.Xqedii_1okvqlt = this.Xqedii_1okvqlt - 1;
               }
            }

            this.Xqedii_1okvqlt = this.Xqedii_1okvqlt - 1;
            return this.Xqedii_3vze4js(
               31, var1, (Xqedii_ev3jp8w[])var3.toArray(new Xqedii_ev3jp8w[0])
            );
         }
      }
   }

   public Xqedii_3nlck21 Xqedii_38ata03(int var1) {
      {
         Xqedii_ev3jp8w var2 = this.Xqedii_3nv6toq();
         Xqedii_ev3jp8w var3 = this.Xqedii_mdc50q8("volume");
         Xqedii_ev3jp8w var4 = this.Xqedii_mdc50q8("pitch");
         int var10001 = 11;
         Xqedii_ev3jp8w[] var10003 = new Xqedii_ev3jp8w[3];
         var10003[0] = var2;
         var10003[1] = var3;
         var10003[2] = var4;
         return this.Xqedii_3vze4js(var10001, var1, var10003);
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public Xqedii_3nlck21[] Xqedii_2txbn32() {
      {
         this.Xqedii_38gkvzl = this.Xqedii_38gkvzl + 1;
         boolean var4 = false /* VF: Semaphore variable */;

         Xqedii_3nlck21[] var1;
         try {
            var4 = true;
            var1 = this.Xqedii_2vw9h4x();
            var4 = false;
         } finally {
            if (var4) {
               this.Xqedii_38gkvzl = this.Xqedii_38gkvzl - 1;
            }
         }

         this.Xqedii_38gkvzl = this.Xqedii_38gkvzl - 1;
         return var1;
      }
   }

   public Xqedii_3nlck21 Xqedii_1fsxdm6(int var1) {
      {
         ArrayList var2 = new ArrayList();
         ArrayList var3 = new ArrayList();
         var2.add(this.Xqedii_3nv6toq());
         var3.add(this.Xqedii_2vw9h4x());
         Xqedii_3nlck21[] var4 = null;

         while (true) {
            int var5 = this.Xqedii_2d86asd;

            while (this.Xqedii_2o6grdd().is(LexerTokenType.NEWLINE)) {
               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            }

            if (!this.Xqedii_3i85ij1("else")
               && !this.Xqedii_3i85ij1("otherwise")) {
               if (!this.Xqedii_3i85ij1("elif")) {
                  this.Xqedii_2d86asd = var5;
                  break;
               }

               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
               var2.add(this.Xqedii_3nv6toq());
               var3.add(this.Xqedii_2vw9h4x());
            } else {
               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
               if (!this.Xqedii_g1933gx("if")) {
                  var4 = this.Xqedii_2vw9h4x();
                  break;
               }

               var2.add(this.Xqedii_3nv6toq());
               var3.add(this.Xqedii_2vw9h4x());
            }
         }

         return new Xqedii_3cpwyhm(
            (Xqedii_ev3jp8w[])var2.toArray(new Xqedii_ev3jp8w[0]),
            (Xqedii_3nlck21[][])var3.toArray(new Xqedii_3nlck21[0][]),
            var4,
            var1
         );
      }
   }

   public Xqedii_ev3jp8w Xqedii_3nv6toq() {
      {
         return this.Xqedii_1km16he();
      }
   }

   public Xqedii_ev3jp8w Xqedii_1km16he() {
      {
         Xqedii_ev3jp8w var1 = this.Xqedii_3oo4k6o();
         if (this.Xqedii_3kqvbrx("?")) {
            int var2 = this.Xqedii_my78stx().line();
            Xqedii_ev3jp8w var3 = this.Xqedii_1km16he();
            this.Xqedii_3cuz73k(
               ":",
               "in a ? b : c"
            );
            Xqedii_ev3jp8w var4 = this.Xqedii_1km16he();
            return new Xqedii_1putpcp(var1, var3, var4, var2);
         } else {
            return var1;
         }
      }
   }

   public Xqedii_ev3jp8w Xqedii_3oo4k6o() {
      {
         Object var1 = this.Xqedii_2d29s5k();

         while (
            this.Xqedii_3i85ij1("or")
               || this.Xqedii_3kqvbrx("||")
         ) {
            int var2 = this.Xqedii_my78stx().line();
            var1 = new Xqedii_1a78ac9(false, (Xqedii_ev3jp8w)var1, this.Xqedii_2d29s5k(), var2);
         }

         return (Xqedii_ev3jp8w)var1;
      }
   }

   public Xqedii_ev3jp8w Xqedii_2d29s5k() {
      {
         Object var1 = this.Xqedii_vtgtmsh();

         while (
            this.Xqedii_3i85ij1("and")
               || this.Xqedii_3kqvbrx("&&")
         ) {
            int var2 = this.Xqedii_my78stx().line();
            var1 = new Xqedii_1a78ac9(true, (Xqedii_ev3jp8w)var1, this.Xqedii_vtgtmsh(), var2);
         }

         return (Xqedii_ev3jp8w)var1;
      }
   }

   public Xqedii_ev3jp8w Xqedii_vtgtmsh() {
      if (!this.Xqedii_3i85ij1("not")
         && !this.Xqedii_3kqvbrx("!")) {
         return this.Xqedii_9c1u1yf();
      } else {
         int var1 = this.Xqedii_my78stx().line();
         return new Xqedii_1fva3u6(false, this.Xqedii_vtgtmsh(), var1);
      }
   }

   public Xqedii_ev3jp8w Xqedii_9c1u1yf() {
      {
         Object var1 = this.Xqedii_g6hkqz0();

         while (true) {
            LexerToken var2 = this.Xqedii_2o6grdd();
            int var3;
            if (var2.isSymbol("==")) {
               var3 = 6;
            } else if (var2.isSymbol("!=")) {
               var3 = 7;
            } else if (var2.isSymbol("<")) {
               var3 = 8;
            } else if (var2.isSymbol(">")) {
               var3 = 9;
            } else if (var2.isSymbol("<=")) {
               var3 = 10;
            } else if (var2.isSymbol(">=")) {
               var3 = 11;
            } else {
               if (var2.isWord("is")) {
                  this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                  var3 = this.Xqedii_g1933gx("not")
                     ? 13
                     : 12;
                  var1 = new Xqedii_3l5gjg9(var3, (Xqedii_ev3jp8w)var1, this.Xqedii_g6hkqz0(), var2.line());
                  continue;
               }

               if (var2.isWord("contains")) {
                  var3 = 14;
               } else if (var2.isWord("in") && this.Xqedii_1okvqlt == 0) {
                  var3 = 15;
               } else {
                  if (!var2.isWord("not")
                     || !this.Xqedii_1ns9xu6(1)
                        .isWord("in")
                     || this.Xqedii_1okvqlt != 0) {
                     return (Xqedii_ev3jp8w)var1;
                  }

                  this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                  var3 = 16;
               }
            }

            this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
            var1 = new Xqedii_3l5gjg9(var3, (Xqedii_ev3jp8w)var1, this.Xqedii_g6hkqz0(), var2.line());
         }
      }
   }

   public Xqedii_ev3jp8w Xqedii_g6hkqz0() {
      {
         Object var1 = this.Xqedii_3qpg4jp();

         while (
            this.Xqedii_3kqvbrx("+")
               || this.Xqedii_3kqvbrx("-")
         ) {
            LexerToken var2 = this.Xqedii_my78stx();
            int var3 = var2.text().equals("+")
               ? 1
               : 2;
            var1 = new Xqedii_3l5gjg9(var3, (Xqedii_ev3jp8w)var1, this.Xqedii_3qpg4jp(), var2.line());
         }

         return (Xqedii_ev3jp8w)var1;
      }
   }

   public Xqedii_ev3jp8w Xqedii_3qpg4jp() {
      {
         Object var1 = this.Xqedii_3amps4a();

         while (
            this.Xqedii_3kqvbrx("*")
               || this.Xqedii_3kqvbrx("/")
               || this.Xqedii_3kqvbrx("%")
         ) {
            LexerToken var2 = this.Xqedii_my78stx();
            String var4 = var2.text();
            int var5 = -1;
            switch (var4.hashCode()) {
               case 42:
                  if (var4.equals("*")) {
                     var5 = 0;
                  }
                  break;
               case 47:
                  if (var4.equals("/")) {
                     var5 = 1;
                  }
            }
            int var3 = switch (var5) {
               case 0 -> 3;
               case 1 -> 4;
               default -> 5;
            };
            var1 = new Xqedii_3l5gjg9(var3, (Xqedii_ev3jp8w)var1, this.Xqedii_3amps4a(), var2.line());
         }

         return (Xqedii_ev3jp8w)var1;
      }
   }

   public Xqedii_ev3jp8w Xqedii_3amps4a() {
      if (this.Xqedii_3kqvbrx("-")) {
         int var6 = this.Xqedii_my78stx().line();
         Xqedii_ev3jp8w var2 = this.Xqedii_3amps4a();
         return (Xqedii_ev3jp8w)(var2 instanceof Xqedii_1z9wnlh var3 && var3.Xqedii_2azibg0 instanceof Double var4
            ? new Xqedii_1z9wnlh(-var4, var6)
            : new Xqedii_1fva3u6(true, var2, var6));
      } else if (this.Xqedii_3kqvbrx("!")) {
         int var1 = this.Xqedii_my78stx().line();
         return new Xqedii_1fva3u6(false, this.Xqedii_3amps4a(), var1);
      } else {
         return this.Xqedii_3vihs01();
      }
   }

   public Xqedii_ev3jp8w Xqedii_3vihs01() {
      {
         Object var1 = this.Xqedii_2r3irt9();

         while (true) {
            while (!this.Xqedii_3kqvbrx(".")) {
               if (!this.Xqedii_3kqvbrx("[")) {
                  return (Xqedii_ev3jp8w)var1;
               }

               LexerToken var2 = this.Xqedii_my78stx();
               Xqedii_ev3jp8w var3 = this.Xqedii_3nv6toq();
               this.Xqedii_3cuz73k(
                  "]",
                  "to close the [ ]"
               );
               var1 = new Xqedii_2p9runp((Xqedii_ev3jp8w)var1, var3, var2.line());
            }

            LexerToken var4 = this.Xqedii_my78stx();
            String var5 = this.Xqedii_34yikyz(
               "a property name after '.'"
            );
            if (this.Xqedii_3kqvbrx("(")) {
               var1 = new Xqedii_3wobtjl((Xqedii_ev3jp8w)var1, var5, this.Xqedii_2cg4oho(), var4.line());
            } else {
               var1 = new Xqedii_v1f4ltd((Xqedii_ev3jp8w)var1, var5, var4.line());
            }
         }
      }
   }

   public Xqedii_ev3jp8w[] Xqedii_2cg4oho() {
      {
         this.Xqedii_3cuz73k(
            "(",
            ""
         );
         ArrayList var1 = new ArrayList();
         if (!this.Xqedii_3kqvbrx(")")) {
            int var2 = this.Xqedii_1okvqlt;
            this.Xqedii_1okvqlt = 0;

            do {
               var1.add(this.Xqedii_3nv6toq());
            } while (this.Xqedii_1s3d88e(","));

            this.Xqedii_1okvqlt = var2;
         }

         this.Xqedii_3cuz73k(
            ")",
            "to close the ( )"
         );
         return (Xqedii_ev3jp8w[])var1.toArray(new Xqedii_ev3jp8w[0]);
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public Xqedii_ev3jp8w Xqedii_2r3irt9() {
      {
         LexerToken var1 = this.Xqedii_2o6grdd();
         int var2 = var1.line();
         switch (dev.xqedii.ultimateUI.Xqedii_11jvjwo.Xqedii_2wwhkoz[var1.type().ordinal()]) {
            case 1:
               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
               return new Xqedii_1z9wnlh(Double.parseDouble(var1.text()), var2);
            case 2:
               this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
               return this.Xqedii_2pyrjto(var1);
            case 3:
               String var9 = var1.text();
               String var11 = var9.toLowerCase(Locale.ROOT);
               int var6 = -1;
               switch (var11.hashCode()) {
                  case 3521:
                     if (var11.equals("no")) {
                        var6 = 4;
                     }
                     break;
                  case 3551:
                     if (var11.equals("on")) {
                        var6 = 2;
                     }
                     break;
                  case 109935:
                     if (var11.equals("off")) {
                        var6 = 5;
                     }
                     break;
                  case 119527:
                     if (var11.equals("yes")) {
                        var6 = 1;
                     }
                     break;
                  case 3387192:
                     if (var11.equals("none")) {
                        var6 = 6;
                     }
                     break;
                  case 3392903:
                     if (var11.equals("null")) {
                        var6 = 7;
                     }
                     break;
                  case 3569038:
                     if (var11.equals("true")) {
                        var6 = 0;
                     }
                     break;
                  case 97196323:
                     if (var11.equals("false")) {
                        var6 = 3;
                     }
                     break;
                  case 2129323981:
                     if (var11.equals("nothing")) {
                        var6 = 8;
                     }
               }

               switch (var6) {
                  case 0:
                  case 1:
                  case 2:
                     this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                     return new Xqedii_1z9wnlh(Boolean.TRUE, var2);
                  case 3:
                  case 4:
                  case 5:
                     this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                     return new Xqedii_1z9wnlh(Boolean.FALSE, var2);
                  case 6:
                  case 7:
                  case 8:
                     this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                     return new Xqedii_1z9wnlh(null, var2);
                  default:
                     if (this.Xqedii_1ns9xu6(1)
                        .isWord("of")) {
                        this.Xqedii_2d86asd = this.Xqedii_2d86asd + 2;
                        return new Xqedii_2s364ds(var9, this.Xqedii_3amps4a(), var2);
                     } else {
                        this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                        if (this.Xqedii_3kqvbrx("(")) {
                           this.Xqedii_1ukapu0.add(new Xqedii_39jklon(var9, var2, var1.column()));
                           return new Xqedii_3ox0q7t(var9, this.Xqedii_2cg4oho(), var2);
                        }

                        return new Xqedii_2ucjp8c(var9, var2);
                     }
               }
            case 4:
               if (var1.isSymbol("(")) {
                  this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                  int var8 = this.Xqedii_1okvqlt;
                  this.Xqedii_1okvqlt = 0;
                  Xqedii_ev3jp8w var10 = this.Xqedii_3nv6toq();
                  this.Xqedii_1okvqlt = var8;
                  this.Xqedii_3cuz73k(
                     ")",
                     "to close the ( )"
                  );
                  return var10;
               } else if (var1.isSymbol("[")) {
                  this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                  ArrayList var7 = new ArrayList();
                  if (!this.Xqedii_3kqvbrx("]")) {
                     while (!this.Xqedii_3kqvbrx("]")) {
                        var7.add(this.Xqedii_3nv6toq());
                        if (!this.Xqedii_1s3d88e(",")) {
                           break;
                        }
                     }
                  }

                  this.Xqedii_3cuz73k(
                     "]",
                     "to close the list"
                  );
                  return new Xqedii_2m7nhbf((Xqedii_ev3jp8w[])var7.toArray(new Xqedii_ev3jp8w[0]), var2);
               } else if (var1.isSymbol("{")) {
                  this.Xqedii_2d86asd = this.Xqedii_2d86asd + 1;
                  ArrayList var3 = new ArrayList();
                  ArrayList var4 = new ArrayList();
                  if (!this.Xqedii_3kqvbrx("}")) {
                     while (!this.Xqedii_3kqvbrx("}")) {
                        LexerToken var5 = this.Xqedii_my78stx();
                        if (!var5.is(LexerTokenType.NAME) && !var5.is(LexerTokenType.STRING)) {
                           throw this.Xqedii_3sn28jz(
                              var5,
                              "Expected a key name in { key: value }"
                           );
                        }

                        this.Xqedii_3cuz73k(
                           ":",
                           "after the key"
                        );
                        var3.add(Xqedii_xm7zknp(var5.text()));
                        var4.add(this.Xqedii_3nv6toq());
                        if (!this.Xqedii_1s3d88e(",")) {
                           break;
                        }
                     }
                  }

                  this.Xqedii_3cuz73k(
                     "}",
                     "to close the { }"
                  );
                  return new Xqedii_1i9eosk(
                     (String[])var3.toArray(new String[0]),
                     (Xqedii_ev3jp8w[])var4.toArray(new Xqedii_ev3jp8w[0]),
                     var2
                  );
               }
            default:
               throw this.Xqedii_3sn28jz(var1, Xqedii_2bijkgvmrllek(var1.describe()));
         }
      }
   }

   public static String Xqedii_xm7zknp(String var0) {
      {
         return var0.replace((char)57344, (char)123)
            .replace((char)57345, (char)125);
      }
   }

   public Xqedii_ev3jp8w Xqedii_2pyrjto(LexerToken var1) {
      {
         String var2 = var1.text();
         if (var2.indexOf(123) < 0) {
            return new Xqedii_29jdaam(Xqedii_xm7zknp(var2), var1.line());
         } else {
            ArrayList var3 = new ArrayList();
            StringBuilder var4 = new StringBuilder();
            int var5 = 0;

            while (var5 < var2.length()) {
               char var6 = var2.charAt(var5);
               if (var6 != 123) {
                  var4.append(var6);
                  var5++;
               } else {
                  int var7 = 1;

                  int var8;
                  for (var8 = var5 + 1; var8 < var2.length() && var7 > 0; var8++) {
                     char var9 = var2.charAt(var8);
                     if (var9 == 123) {
                        var7++;
                     } else if (var9 == 125) {
                        var7--;
                     }
                  }

                  if (var7 != 0) {
                     throw this.Xqedii_3sn28jz(
                        var1,
                        "A { in this text is never closed. Write \\{ to show a { character"
                     );
                  }

                  if (!var4.isEmpty()) {
                     var3.add(Xqedii_xm7zknp(var4.toString()));
                     var4.setLength(0);
                  }

                  var3.add(
                     this.Xqedii_v4mtmbd(
                        var2.substring(var5 + 1, var8 - 1),
                        var1
                     )
                  );
                  var5 = var8;
               }
            }

            if (!var4.isEmpty()) {
               var3.add(Xqedii_xm7zknp(var4.toString()));
            }

            return new Xqedii_1h5sfhl(var3.toArray(), var1.line());
         }
      }
   }

   public Xqedii_ev3jp8w Xqedii_v4mtmbd(String var1, LexerToken var2) {
      if (var1.isBlank()) {
         throw this.Xqedii_3sn28jz(
            var2,
            "Empty { } in text. Put a value inside, like {player.name}"
         );
      } else {
         List<LexerToken> var3;
         try {
            var3 = new Xqedii_3l1ke2c(var1).Xqedii_1lbczed();
         } catch (Xqedii_3vsk7zm var7) {
            throw this.Xqedii_3sn28jz(var2, Xqedii_1onuw514fh88p(var7.getMessage()));
         }

         ArrayList var4 = new ArrayList(var3.size());

         for (LexerToken var6 : var3) {
            var4.add(new LexerToken(var6.type(), var6.text(), var2.line(), var2.column() + var6.column()));
         }

         Xqedii_2thoio4 var8 = new Xqedii_2thoio4(var4, this.Xqedii_d4hvym9, this.Xqedii_1ukapu0);
         Xqedii_ev3jp8w var9 = var8.Xqedii_3nv6toq();

         while (var8.Xqedii_2o6grdd().is(LexerTokenType.NEWLINE) || var8.Xqedii_2o6grdd().is(LexerTokenType.DEDENT)) {
            var8.Xqedii_2d86asd = var8.Xqedii_2d86asd + 1;
         }

         if (!var8.Xqedii_2o6grdd().is(LexerTokenType.EOF)) {
            throw this.Xqedii_3sn28jz(var2, Xqedii_2bw4z6qvz0csc(var8.Xqedii_2o6grdd().describe()));
         } else {
            return var9;
         }
      }
   }

   static {
      {
         Xqedii_3ct77sg = Set.of(
            "=",
            "+=",
            "-=",
            "*=",
            "/="
         );
         String[] var10000 = new String[45];
         var10000[0] = "let";
         var10000[1] = "var";
         var10000[2] = "set";
         var10000[3] = "add";
         var10000[4] = "remove";
         var10000[5] = "delete";
         var10000[6] = "show";
         var10000[7] = "hide";
         var10000[8] = "toggle";
         var10000[9] = "move";
         var10000[10] = "resize";
         var10000[11] = "send";
         var10000[12] = "message";
         var10000[13] = "tell";
         var10000[14] = "broadcast";
         var10000[15] = "actionbar";
         var10000[16] = "title";
         var10000[17] = "play";
         var10000[18] = "sound";
         var10000[19] = "command";
         var10000[20] = "run";
         var10000[21] = "console";
         var10000[22] = "open";
         var10000[23] = "close";
         var10000[24] = "scroll";
         var10000[25] = "clone";
         var10000[26] = "click";
         var10000[27] = "action";
         var10000[28] = "teleport";
         var10000[29] = "give";
         var10000[30] = "wait";
         var10000[31] = "if";
         var10000[32] = "while";
         var10000[33] = "repeat";
         var10000[34] = "for";
         var10000[35] = "break";
         var10000[36] = "continue";
         var10000[37] = "return";
         var10000[38] = "stop";
         var10000[39] = "cancel";
         var10000[40] = "uncancel";
         var10000[41] = "log";
         var10000[42] = "print";
         var10000[43] = "create";
         var10000[44] = "animate";
         Xqedii_200w0a7 = Set.of(var10000);
         Xqedii_4h0tx6n = List.of(
            "x",
            "y",
            "width",
            "height",
            "opacity",
            "rotation",
            "color",
            "position",
            "size"
         );
         Xqedii_p2rt6hv = List.of(
            "block",
            "rounded",
            "text",
            "image",
            "item",
            "input"
         );
         var10000 = new String[14];
         var10000[0] = "at";
         var10000[1] = "size";
         var10000[2] = "color";
         var10000[3] = "text";
         var10000[4] = "layer";
         var10000[5] = "opacity";
         var10000[6] = "rounding";
         var10000[7] = "image";
         var10000[8] = "item";
         var10000[9] = "align";
         var10000[10] = "font";
         var10000[11] = "in";
         var10000[12] = "hidden";
         var10000[13] = "with";
         Xqedii_2xrm6um = List.of(var10000);
      }
   }

   private static /* synthetic */ String Xqedii_rds1654yvnow(String string, String string2) {
        return "Unknown function " + string + "(). Create it with: function " + string2 + "():";
    }

    private static /* synthetic */ String Xqedii_2pzpvqkaa0cc4(String string, String string2, String string3) {
        return "Expected '" + string + "' " + string2 + " but found " + string3;
    }

    private static /* synthetic */ String Xqedii_1cefcnyi2rc0f(String string, String string2, String string3) {
        return "Expected '" + string + "' " + string2 + " but found " + string3;
    }

    private static /* synthetic */ String Xqedii_11usblxca3255(String string, String string2) {
        return "Expected " + string + " but found " + string2;
    }

    private static /* synthetic */ String Xqedii_1taq5bcwpzh1z(String string, String string2) {
        return "Expected " + string + " in quotes, like \"example\", but found " + string2;
    }

    private static /* synthetic */ String Xqedii_3be94jvrxos1a(String string) {
        return "Unexpected " + string + ", the line should end here";
    }

    private static /* synthetic */ String Xqedii_36dple85j8emh(String string) {
        return "'" + string + "' is a built-in name, choose another variable name";
    }

    private static /* synthetic */ String Xqedii_22q9n7u7y5jf2(String string) {
        return string + "() is a built-in function, choose another name";
    }

    private static /* synthetic */ String Xqedii_3vlnfzljnwyca(String string) {
        return "The function " + string + "() already exists in this file";
    }

    private static /* synthetic */ String Xqedii_11clkzaey29tn(String string) {
        return "Unknown '" + string + "'. A script is made of: on <event>:, every <time>:, command /<name>:, function <name>():, var <name> = ... and page \"<name>\"";
    }

    private static /* synthetic */ String Xqedii_dqobhk5cwls4(String string) {
        return "Unknown time unit '" + string + "', use ticks, seconds or minutes";
    }

    private static /* synthetic */ String Xqedii_l45ya4ys9dux(String string) {
        return "after " + string;
    }

    private static /* synthetic */ String Xqedii_34vwkggyee3vu(String string) {
        return string + " click";
    }

    private static /* synthetic */ String Xqedii_3svs56vtzx5ai(String string) {
        return "Unknown event '" + string + "'. Events: load, join, quit, opening, open, close, click, left click, right click, scroll up, scroll down, hover, unhover, drag start, drag, drop, cursor move, key, input";
    }

    private static /* synthetic */ String Xqedii_1onahknpkpj9v(String string) {
        return "'" + string + "' must come right after an if block, at the same indentation as the if";
    }

    private static /* synthetic */ String Xqedii_2br6gj5oh8vhc(String string, String string2) {
        return "Unknown command '" + string + "'" + string2;
    }

    private static /* synthetic */ String Xqedii_jz9avrm56e9f(String string) {
        return ". Did you mean " + string + "?";
    }

    private static /* synthetic */ String Xqedii_32yx4q6co7il9(String string) {
        return "'" + string + "' is a built-in name, choose another variable name";
    }

    private static /* synthetic */ String Xqedii_1a8hvczznev5x(String string, String string2) {
        return "Expected what to animate (" + string + ") but found " + string2;
    }

    private static /* synthetic */ String Xqedii_3g81ocn1cm0ob(String string) {
        return "(animate \"id\" " + string + " to ...)";
    }

    private static /* synthetic */ String Xqedii_1bl3f0i9524f7(String string) {
        return string + " can only be used inside a loop (while, repeat or for)";
    }

    private static /* synthetic */ String Xqedii_3l3a4kjaqi84z(String string) {
        return "Unknown command '" + string + "'";
    }

    private static /* synthetic */ String Xqedii_149had7p0ynq4(String string) {
        return "Expected the element type after create, like: create block \"my_box\" (types: " + string + ")";
    }

    private static /* synthetic */ String Xqedii_1fia4o6yk4zw7(String string, String string2) {
        return "Unknown create option " + string + ". Options: " + string2;
    }

    private static /* synthetic */ String Xqedii_2bijkgvmrllek(String string) {
        return "Expected a value (a number, \"text\", a variable or a function) but found " + string;
    }

    private static /* synthetic */ String Xqedii_1onuw514fh88p(String string) {
        return "Inside { }: " + string;
    }

    private static /* synthetic */ String Xqedii_2bw4z6qvz0csc(String string) {
        return "Inside { }: unexpected " + string;
    }
}
