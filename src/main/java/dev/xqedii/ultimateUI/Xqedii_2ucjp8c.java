package dev.xqedii.ultimateUI;

public final class Xqedii_2ucjp8c extends Xqedii_ev3jp8w {
   public final String Xqedii_39zi9f8;

   public Xqedii_2ucjp8c(String var1, int var2) {
      super(var2);
      this.Xqedii_39zi9f8 = var1;
   }

   @Override
   public Object Xqedii_28g36j3(Xqedii_y5pr2ec var1) {
      {
         Xqedii_1yqm9k3 var2 = var1.Xqedii_3a9kp91;
         Object var3 = var2.Xqedii_107f1nz.get(this.Xqedii_39zi9f8);
         if (var3 != null || var2.Xqedii_107f1nz.containsKey(this.Xqedii_39zi9f8)) {
            return var3;
         } else {
            return var2.Xqedii_2q97y6i.Xqedii_3i3ujw9.containsKey(this.Xqedii_39zi9f8)
               ? var2.Xqedii_2q97y6i.Xqedii_3i3ujw9.get(this.Xqedii_39zi9f8)
               : Xqedii_2d07ocz.Xqedii_2pszqr1(var1, this.Xqedii_39zi9f8, this.Xqedii_3dzdvck);
         }
      }
   }

   @Override
   public boolean Xqedii_25ddebp() {
      {
         return true;
      }
   }

   @Override
   public void Xqedii_2uxio2i(Xqedii_y5pr2ec var1, Object var2) {
      {
         Xqedii_1yqm9k3 var3 = var1.Xqedii_3a9kp91;
         if (!var3.Xqedii_107f1nz.containsKey(this.Xqedii_39zi9f8) && var3.Xqedii_2q97y6i.Xqedii_3i3ujw9.containsKey(this.Xqedii_39zi9f8)) {
            var3.Xqedii_2q97y6i.Xqedii_3i3ujw9.put(this.Xqedii_39zi9f8, var2);
         } else if (Xqedii_2d07ocz.Xqedii_2b8p7zx(this.Xqedii_39zi9f8)) {
            throw new Xqedii_3vsk7zm(Xqedii_30hykafnq7c1k(this.Xqedii_39zi9f8), this.Xqedii_3dzdvck);
         } else {
            var3.Xqedii_107f1nz.put(this.Xqedii_39zi9f8, var2);
         }
      }
   }

   @Override
   public void Xqedii_2dz6h65(Xqedii_y5pr2ec var1) {
      {
         if (var1.Xqedii_3a9kp91.Xqedii_107f1nz.remove(this.Xqedii_39zi9f8) == null) {
            var1.Xqedii_3a9kp91.Xqedii_2q97y6i.Xqedii_3i3ujw9.put(this.Xqedii_39zi9f8, null);
         }
      }
   }

   private static /* synthetic */ String Xqedii_30hykafnq7c1k(String string) {
        return Xqedii_2ndqxax.Xqedii_1oladqbs3xf8j((String)"YPHk94JEF1h9wwOMxsuAPYJcdS3ScIvt8HvibSlA") + string + Xqedii_2ndqxax.Xqedii_1oladqbs3xf8j((String)"Ncz9gH8KcusM50vPzH5PH77yF+jKNhNFUNUzQZXsRSFQbh///wdAuHQBcvqX3uxf9pYC3EH6pjRTa0SHCFYxIWaiZtW9D06Gv+HxCHGRNH7XWLIGam6Aw1oXXIMMr+kdq0LQb1FS8N3iiS9vj1QKNg==");
    }
}
