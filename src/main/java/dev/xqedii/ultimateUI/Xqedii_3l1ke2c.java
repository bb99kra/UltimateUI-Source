package dev.xqedii.ultimateUI;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class Xqedii_3l1ke2c {
   public static final char Xqedii_3ld17p9 = '\ue000';
   public static final char Xqedii_1tp679b = '\ue001';
   public static final String[] Xqedii_1s4c06s;
   public static final String Xqedii_1piucqt = "+-*/%<>=!()[]{},.:?";
   public final String Xqedii_3exi2tx;
   public final List<Xqedii_25ri1e7> Xqedii_34wrjqr = new ArrayList<>();
   public final Deque<Integer> Xqedii_qge9b71 = new ArrayDeque<>();
   public int Xqedii_1khowns;
   public int Xqedii_2453gam = 1;
   public int Xqedii_34f012n;
   public int Xqedii_3jtzixm;

   public Xqedii_3l1ke2c(String var1) {
      this.Xqedii_3exi2tx = var1 == null
         ? ""
         : var1.replace(
               "\r\n",
               "\n"
            )
            .replace((char)13, (char)10);
      this.Xqedii_qge9b71.push(0);
   }

   public List<Xqedii_25ri1e7> Xqedii_1lbczed() {
      {
         int var1 = 1;

         while (this.Xqedii_1khowns < this.Xqedii_3exi2tx.length()) {
            if (var1 != 0 && this.Xqedii_3jtzixm == 0) {
               if (!this.Xqedii_1qysv1a()) {
                  continue;
               }

               var1 = 0;
            }

            char var2 = this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns);
            if (var2 == 10) {
               this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
               if (this.Xqedii_3jtzixm == 0) {
                  this.Xqedii_2m0u96g();
                  var1 = 1;
               }

               this.Xqedii_2453gam = this.Xqedii_2453gam + 1;
               this.Xqedii_34f012n = this.Xqedii_1khowns;
            } else if (var2 == 32 || var2 == 9) {
               this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
            } else if (var2 == 35) {
               this.Xqedii_1442pc0();
            } else if (var2 == 34 || var2 == 39) {
               this.Xqedii_333x9hr(var2);
            } else if (Character.isDigit(var2)) {
               this.Xqedii_18clkg2();
            } else if (!Character.isLetter(var2) && var2 != 95) {
               this.Xqedii_91dl6q0(var2);
            } else {
               this.Xqedii_1xljy6y();
            }
         }

         if (this.Xqedii_3jtzixm > 0) {
            throw new Xqedii_3vsk7zm(
               "A bracket was opened but never closed",
               this.Xqedii_2453gam,
               this.Xqedii_27fe156()
            );
         } else {
            this.Xqedii_2m0u96g();

            while (this.Xqedii_qge9b71.peek() > 0) {
               this.Xqedii_qge9b71.pop();
               this.Xqedii_34wrjqr
                  .add(
                     new Xqedii_25ri1e7(
                        Xqedii_1p9lu7f.DEDENT,
                        "",
                        this.Xqedii_2453gam,
                        1
                     )
                  );
            }

            this.Xqedii_34wrjqr
               .add(
                  new Xqedii_25ri1e7(
                     Xqedii_1p9lu7f.EOF,
                     "",
                     this.Xqedii_2453gam,
                     1
                  )
               );
            return this.Xqedii_34wrjqr;
         }
      }
   }

   public boolean Xqedii_1qysv1a() {
      {
         int var1 = 0;

         int var2;
         for (var2 = this.Xqedii_1khowns;
            this.Xqedii_1khowns < this.Xqedii_3exi2tx.length();
            this.Xqedii_1khowns = this.Xqedii_1khowns + 1
         ) {
            char var3 = this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns);
            if (var3 == 32) {
               var1++;
            } else {
               if (var3 != 9) {
                  break;
               }

               var1 += 4;
            }
         }

         if (this.Xqedii_1khowns >= this.Xqedii_3exi2tx.length()) {
            return true;
         } else {
            char var5 = this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns);
            if (var5 != 10 && var5 != 35) {
               int var4 = this.Xqedii_qge9b71.peek();
               if (var1 > var4) {
                  this.Xqedii_qge9b71.push(var1);
                  this.Xqedii_34wrjqr
                     .add(
                        new Xqedii_25ri1e7(
                           Xqedii_1p9lu7f.INDENT,
                           "",
                           this.Xqedii_2453gam,
                           1
                        )
                     );
               } else if (var1 < var4) {
                  while (var1 < this.Xqedii_qge9b71.peek()) {
                     this.Xqedii_qge9b71.pop();
                     this.Xqedii_34wrjqr
                        .add(
                           new Xqedii_25ri1e7(
                              Xqedii_1p9lu7f.DEDENT,
                              "",
                              this.Xqedii_2453gam,
                              1
                           )
                        );
                  }

                  if (var1 != this.Xqedii_qge9b71.peek()) {
                     throw new Xqedii_3vsk7zm(
                        "This line is indented differently from the lines above it",
                        this.Xqedii_2453gam,
                        this.Xqedii_1khowns - var2 + 1
                     );
                  }
               }

               return true;
            } else {
               if (var5 == 35) {
                  this.Xqedii_1442pc0();
               }

               if (this.Xqedii_1khowns < this.Xqedii_3exi2tx.length()) {
                  this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
                  this.Xqedii_2453gam = this.Xqedii_2453gam + 1;
                  this.Xqedii_34f012n = this.Xqedii_1khowns;
               }

               return false;
            }
         }
      }
   }

   public void Xqedii_2m0u96g() {
      {
         if (!this.Xqedii_34wrjqr.isEmpty()) {
            Xqedii_25ri1e7 var1 = this.Xqedii_34wrjqr.get(this.Xqedii_34wrjqr.size() - 1);
            if (var1.type() != Xqedii_1p9lu7f.NEWLINE && var1.type() != Xqedii_1p9lu7f.INDENT && var1.type() != Xqedii_1p9lu7f.DEDENT) {
               this.Xqedii_34wrjqr
                  .add(
                     new Xqedii_25ri1e7(
                        Xqedii_1p9lu7f.NEWLINE,
                        "",
                        this.Xqedii_2453gam,
                        this.Xqedii_27fe156()
                     )
                  );
            }
         }
      }
   }

   public void Xqedii_1442pc0() {
      {
         while (
            this.Xqedii_1khowns < this.Xqedii_3exi2tx.length()
               && this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns) != 10
         ) {
            this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
         }
      }
   }

   public int Xqedii_27fe156() {
      {
         return this.Xqedii_1khowns - this.Xqedii_34f012n + 1;
      }
   }

   public void Xqedii_333x9hr(char var1) {
      {
         int var2 = this.Xqedii_2453gam;
         int var3 = this.Xqedii_27fe156();
         this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
         StringBuilder var4 = new StringBuilder();

         while (
            this.Xqedii_1khowns < this.Xqedii_3exi2tx.length()
               && this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns) != 10
         ) {
            String var10000 = this.Xqedii_3exi2tx;
            int var10001 = this.Xqedii_1khowns;
            this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
            char var5 = var10000.charAt(var10001);
            if (var5 == var1) {
               this.Xqedii_34wrjqr.add(new Xqedii_25ri1e7(Xqedii_1p9lu7f.STRING, var4.toString(), var2, var3));
               return;
            }

            if (var5 == 92 && this.Xqedii_1khowns < this.Xqedii_3exi2tx.length()) {
               var10000 = this.Xqedii_3exi2tx;
               var10001 = this.Xqedii_1khowns;
               this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
               char var6 = var10000.charAt(var10001);
               switch (var6) {
                  case 'n':
                     var4.append((char)10);
                     break;
                  case 't':
                     var4.append((char)9);
                     break;
                  case '{':
                     var4.append((char)57344);
                     break;
                  case '}':
                     var4.append((char)57345);
                     break;
                  default:
                     var4.append(var6);
               }
            } else {
               var4.append(var5);
            }
         }

         throw new Xqedii_3vsk7zm(Xqedii_2pn0bba2cjx8w(var1), var2, var3);
      }
   }

   public void Xqedii_18clkg2() {
      {
         int var1 = this.Xqedii_1khowns;
         int var2 = this.Xqedii_27fe156();

         while (
            this.Xqedii_1khowns < this.Xqedii_3exi2tx.length()
               && (
                  Character.isDigit(this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns))
                     || this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns) == 95
               )
         ) {
            this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
         }

         if (this.Xqedii_1khowns + 1 < this.Xqedii_3exi2tx.length()
            && this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns) == 46
            && Character.isDigit(this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns + 1))) {
            this.Xqedii_1khowns = this.Xqedii_1khowns + 1;

            while (this.Xqedii_1khowns < this.Xqedii_3exi2tx.length() && Character.isDigit(this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns))) {
               this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
            }
         }

         this.Xqedii_34wrjqr
            .add(
               new Xqedii_25ri1e7(
                  Xqedii_1p9lu7f.NUMBER,
                  this.Xqedii_3exi2tx
                     .substring(var1, this.Xqedii_1khowns)
                     .replace(
                        "_",
                        ""
                     ),
                  this.Xqedii_2453gam,
                  var2
               )
            );
      }
   }

   public void Xqedii_1xljy6y() {
      {
         int var1 = this.Xqedii_1khowns;
         int var2 = this.Xqedii_27fe156();

         while (
            this.Xqedii_1khowns < this.Xqedii_3exi2tx.length()
               && (
                  Character.isLetterOrDigit(this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns))
                     || this.Xqedii_3exi2tx.charAt(this.Xqedii_1khowns) == 95
               )
         ) {
            this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
         }

         this.Xqedii_34wrjqr.add(new Xqedii_25ri1e7(Xqedii_1p9lu7f.NAME, this.Xqedii_3exi2tx.substring(var1, this.Xqedii_1khowns), this.Xqedii_2453gam, var2));
      }
   }

   public void Xqedii_91dl6q0(char var1) {
      {
         int var2 = this.Xqedii_27fe156();
         if (this.Xqedii_1khowns + 1 < this.Xqedii_3exi2tx.length()) {
            String var3 = this.Xqedii_3exi2tx.substring(this.Xqedii_1khowns, this.Xqedii_1khowns + 2);
            String[] var4 = Xqedii_1s4c06s;
            int var5 = var4.length;

            for (int var6 = 0; var6 < var5; var6++) {
               String var7 = var4[var6];
               if (var7.equals(var3)) {
                  this.Xqedii_1khowns = this.Xqedii_1khowns + 2;
                  this.Xqedii_34wrjqr.add(new Xqedii_25ri1e7(Xqedii_1p9lu7f.SYMBOL, var7, this.Xqedii_2453gam, var2));
                  return;
               }
            }
         }

         if ("+-*/%<>=!()[]{},.:?".indexOf(var1) < 0) {
            throw new Xqedii_3vsk7zm(Xqedii_1klt28h5m72q(var1), this.Xqedii_2453gam, var2);
         } else {
            if (var1 == 91 || var1 == 123) {
               this.Xqedii_3jtzixm = this.Xqedii_3jtzixm + 1;
            } else if ((var1 == 93 || var1 == 125)
               && this.Xqedii_3jtzixm > 0) {
               this.Xqedii_3jtzixm = this.Xqedii_3jtzixm - 1;
            }

            this.Xqedii_1khowns = this.Xqedii_1khowns + 1;
            this.Xqedii_34wrjqr.add(new Xqedii_25ri1e7(Xqedii_1p9lu7f.SYMBOL, String.valueOf(var1), this.Xqedii_2453gam, var2));
         }
      }
   }

   static {
      {
         String[] var10000 = new String[10];
         var10000[0] = "==";
         var10000[1] = "!=";
         var10000[2] = "<=";
         var10000[3] = ">=";
         var10000[4] = "&&";
         var10000[5] = "||";
         var10000[6] = "+=";
         var10000[7] = "-=";
         var10000[8] = "*=";
         var10000[9] = "/=";
         Xqedii_1s4c06s = var10000;
      }
   }

   private static /* synthetic */ String Xqedii_2pn0bba2cjx8w(char c) {
        return "This text is missing its closing quote " + c;
    }

    private static /* synthetic */ String Xqedii_1klt28h5m72q(char c) {
        return "Unexpected character '" + c + "'";
    }
}
