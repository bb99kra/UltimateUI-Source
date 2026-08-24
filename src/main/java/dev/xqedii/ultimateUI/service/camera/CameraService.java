package dev.xqedii.ultimateUI.service.camera;

import dev.xqedii.ultimateUI.UltimateUI;
import dev.xqedii.ultimateUI.service.hud.HudService;
import dev.xqedii.ultimateUI.util.PlatformCompat;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TextDisplay;
import org.bukkit.entity.TextDisplay.TextAlignment;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class CameraService {
   private static final double CURSOR_RENDER_EPSILON = 0.005;
   private static final String CAMERA_INTERNAL_META = "xqgui_internal_camera";
   private static final double CAMERA_BASE_Y_OFFSET = 1.5;
   private static final double CAMERA_VIEW_Z_OFFSET = 0.0;
   private static final double CAMERA_SEAT_Y_OFFSET = -1.5;
   private static final double CAMERA_SEAT_Z_OFFSET = 0.0;
   private static final int EDITOR_CURSOR_INTERPOLATION_DURATION_DEFAULT = 1;
   private static final int EDITOR_CURSOR_TELEPORT_DURATION_DEFAULT = 1;
   private static final int STATIONARY_CURSOR_CALLBACK_INTERVAL_TICKS = 2;
   private final UltimateUI plugin;
   private final HudService hudService;
   private final Map<UUID, Boolean> cameraStatus = new HashMap<>();
   private final Map<UUID, Set<CameraService.BlockPos>> clientBarrierBlocks = new HashMap<>();
   private final Map<UUID, CameraService.BlockPos> clientBarrierCenters = new HashMap<>();
   private final Map<UUID, Set<UUID>> cameraInternalEntityIds = new HashMap<>();
   private final Map<UUID, Location> cameraBaseLocations = new HashMap<>();
   private final Map<UUID, Integer> cameraRunTokens = new HashMap<>();
   private final Map<UUID, Double> cameraCursorLayers = new HashMap<>();
   private final Map<UUID, Vector> cursorVisualLocks = new HashMap<>();
   private final Map<UUID, Float> cursorVisualLockPitches = new HashMap<>();
   private final Map<Class<?>, Method> craftEntityGetHandleMethodCache = new HashMap<>();
   private static final BlockData CLIENT_BARRIER_DATA = Material.BARRIER.createBlockData();
   private Method craftPlayerGetHandleMethod;
   private Field serverPlayerConnectionField;
   private Method playerConnectionSendMethod;
   private Constructor<?> setCameraPacketConstructor;
   private boolean spectateReflectionReady;
   private boolean spectateReflectionUnavailable;
   private CameraService.CursorListener cursorListener;

   public CameraService(UltimateUI var1, HudService var2) {
      this.plugin = var1;
      this.hudService = var2;
   }

   public boolean isCameraActive(Player var1) {
      return this.cameraStatus.getOrDefault(var1.getUniqueId(), false);
   }

   public Location getCameraBaseLocation(Player var1) {
      if (var1 == null) {
         return null;
      } else {
         Location var2 = this.cameraBaseLocations.get(var1.getUniqueId());
         return var2 == null ? null : var2.clone();
      }
   }

   public void setCameraActive(Player var1, boolean var2) {
      if (var1 != null) {
         this.cameraStatus.put(var1.getUniqueId(), var2);
      }
   }

   public void forceStop(Player var1) {
      this.cleanupCameraRuntime(var1, true);
   }

   public void updateActiveCursorLayer(Player var1, double var2) {
      if (var1 != null && Double.isFinite(var2)) {
         UUID var4 = var1.getUniqueId();
         if (this.cameraStatus.getOrDefault(var4, false)) {
            this.cameraCursorLayers.put(var4, var2);
            Entity var5 = this.hudService.getHud(var1, "cursor");
            if (var5 != null) {
               Vector var6 = this.hudService.getHudLocation(var5);
               if (var6 != null && !(Math.abs(var6.getZ() - var2) <= 0.005)) {
                  this.hudService
                     .moveHud(
                        var5,
                        new Vector(var6.getX(), var6.getY(), var2),
                        this.resolveEditorCursorInterpolationDurationTicks(),
                        this.resolveEditorCursorTeleportDurationTicks()
                     );
               }
            }
         }
      }
   }

   public void setCursorListener(CameraService.CursorListener var1) {
      this.cursorListener = var1;
   }

   public void lockCursorVisual(Player var1, double var2, double var4) {
      if (var1 != null && Double.isFinite(var2) && Double.isFinite(var4)) {
         double var6 = 0.0;
         Entity var8 = this.hudService.getHud(var1, "cursor");
         if (var8 != null) {
            Vector var9 = this.hudService.getHudLocation(var8);
            if (var9 != null && Double.isFinite(var9.getZ())) {
               var6 = var9.getZ();
            }
         }

         this.cursorVisualLocks.put(var1.getUniqueId(), new Vector(var2, var4, var6));
         this.cursorVisualLockPitches.put(var1.getUniqueId(), var1.getLocation().getPitch());
      }
   }

   public void unlockCursorVisual(Player var1) {
      if (var1 != null) {
         this.cursorVisualLocks.remove(var1.getUniqueId());
         this.cursorVisualLockPitches.remove(var1.getUniqueId());
      }
   }

   public void spectate(Player var1, Entity var2) {
      if (var1 != null) {
         try {
            if (!this.ensureSpectateReflectionReady(var1)) {
               return;
            }

            Object var3 = this.craftPlayerGetHandleMethod.invoke(var1);
            if (var3 == null) {
               return;
            }

            Object var4 = this.serverPlayerConnectionField.get(var3);
            if (var4 == null) {
               return;
            }

            Object var5 = var2 == null ? var3 : this.resolveCraftEntityHandle(var2);
            if (var5 == null) {
               return;
            }

            Object var6 = this.setCameraPacketConstructor.newInstance(var5);
            this.playerConnectionSendMethod.invoke(var4, var6);
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }
   }

   private boolean ensureSpectateReflectionReady(Player var1) {
      if (this.spectateReflectionReady) {
         return true;
      } else if (!this.spectateReflectionUnavailable && var1 != null) {
         try {
            this.craftPlayerGetHandleMethod = var1.getClass().getMethod("getHandle");
            Object var2 = this.craftPlayerGetHandleMethod.invoke(var1);
            if (var2 == null) {
               return false;
            } else {
               this.serverPlayerConnectionField = var2.getClass().getField("connection");
               Object var3 = this.serverPlayerConnectionField.get(var2);
               if (var3 == null) {
                  return false;
               } else {
                  Class var4 = Class.forName("net.minecraft.network.protocol.Packet");
                  Class var5 = Class.forName("net.minecraft.world.entity.Entity");
                  this.playerConnectionSendMethod = var3.getClass().getMethod("send", var4);
                  this.setCameraPacketConstructor = Class.forName("net.minecraft.network.protocol.game.ClientboundSetCameraPacket").getConstructor(var5);
                  this.spectateReflectionReady = true;
                  return true;
               }
            }
         } catch (Exception var6) {
            this.spectateReflectionUnavailable = true;
            var6.printStackTrace();
            return false;
         }
      } else {
         return false;
      }
   }

   private Object resolveCraftEntityHandle(Entity var1) throws ReflectiveOperationException {
      if (var1 == null) {
         return null;
      } else {
         Class var2 = var1.getClass();
         Method var3 = this.craftEntityGetHandleMethodCache.get(var2);
         if (var3 == null) {
            var3 = var2.getMethod("getHandle");
            this.craftEntityGetHandleMethodCache.put(var2, var3);
         }

         return var3.invoke(var1);
      }
   }

   public void start(Player var1, double var2, double var4, double var6, double var8, double var10, double var12, String var14, double var15) {
      if (var1 != null) {
         UUID var17 = var1.getUniqueId();
         Location var18 = this.resolveCameraBaseLocation(var1);
         this.cleanupCameraRuntime(var1, true);
         Location var19 = var1.getLocation().clone();
         if (var18 != null && var18.getWorld() != null && var18.getWorld().equals(var19.getWorld())) {
            if (var18.distanceSquared(var19) > 0.01) {
               PlatformCompat.teleportSafely(var1, var18);
               var19 = var1.getLocation().clone();
            }

            if (var19.getWorld() != null && var19.getWorld().equals(var18.getWorld())) {
               var18 = var19;
            }
         } else {
            var18 = var19;
         }

         if (var18 == null || var18.getWorld() == null) {
            var18 = var1.getLocation().clone();
         }

         this.cameraStatus.put(var17, true);
         this.cameraBaseLocations.put(var17, var18.clone());
         this.cameraCursorLayers.put(var17, var15);
         int var20 = this.cameraRunTokens.merge(var17, 1, Integer::sum);
         Location var21 = var18.clone().add(0.0, 1.5, 0.0);
         TextDisplay var22 = (TextDisplay)var1.getWorld().spawn(var21, TextDisplay.class);
         TextDisplay var23 = (TextDisplay)var1.getWorld().spawn(var21.clone().add(0.0, -1.5, 0.0), TextDisplay.class);
         this.hudService.enforceFullBrightness(var22);
         this.hudService.enforceFullBrightness(var23);
         var22.setPersistent(false);
         var23.setPersistent(false);
         var22.setMetadata("xqgui_internal_camera", new FixedMetadataValue(this.plugin, true));
         var23.setMetadata("xqgui_internal_camera", new FixedMetadataValue(this.plugin, true));
         var22.setGravity(false);
         var23.setGravity(false);
         var23.addPassenger(var1);
         this.trackCameraEntity(var1, var22);
         this.trackCameraEntity(var1, var23);
         double var24 = Math.max(1.0, var10);
         double var26 = this.resolveEditorCursorMovementSpeed(var12);
         String var28 = var14 != null && !var14.isBlank() ? var14 : "\ue67c";
         PlatformCompat.runEntityTaskLater(
            this.plugin,
            var1,
            () -> {
               if (!var1.isOnline()) {
                  this.cleanupCameraRuntime(var1, true);
               } else {
                  Integer var19x = this.cameraRunTokens.get(var1.getUniqueId());
                  if (var19x != null && var19x == var20) {
                     if (!this.cameraStatus.getOrDefault(var1.getUniqueId(), false)) {
                        this.cleanupCameraRuntime(var1, true);
                     } else {
                        var1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 0, false, false));
                        this.spectate(var1, var22);
                        double[] var20x = new double[]{var2 / 2.0 + var6};
                        double[] var21x = new double[]{((double)var1.getLocation().getPitch() + 90.0) / 180.0 * var4 + var8};
                        float[] var22x = new float[]{var1.getLocation().getYaw()};
                        float[] var23x = new float[]{var1.getLocation().getPitch()};
                        double[] var24x = new double[]{-1.0};
                        double[] var25 = new double[]{-1.0};
                        boolean[] var26x = new boolean[]{false};
                        int[] var27 = new int[]{0};
                        double var28x = this.resolveRuntimeCursorLayer(var1.getUniqueId(), var15);
                        this.hudService
                           .addHud(
                              var1,
                              "cursor",
                              new Vector(var20x[0], var4 / 2.0 + var8, var28x),
                              new Vector(var24, var24, 2.0),
                              var28,
                              TextAlignment.CENTER,
                              TextAlignment.CENTER,
                              false,
                              255
                           );
                        this.refreshCursorOpacity(var1);
                        this.refreshClientBarrierCube(var1);
                        PlatformCompat.runEntityTimer(
                           this.plugin,
                           var1,
                           0L,
                           1L,
                           var21xx -> {
                              if (!var1.isOnline()) {
                                 this.cleanupCameraRuntime(var1, true);
                                 var21xx.cancel();
                              } else {
                                 Integer var22xx = this.cameraRunTokens.get(var1.getUniqueId());
                                 if (var22xx != null && var22xx == var20) {
                                    if (!this.cameraStatus.getOrDefault(var1.getUniqueId(), false)) {
                                       this.cleanupCameraRuntime(var1, true);
                                       var21xx.cancel();
                                    } else {
                                       Location var23xx = var1.getLocation();
                                       float var24xx = var23xx.getYaw();
                                       float var25x = var24xx - var22x[0];
                                       var22x[0] = var24xx;
                                       Vector var26xx = this.cursorVisualLocks.get(var1.getUniqueId());
                                       boolean var27x = var26xx != null;
                                       if (var25x > 180.0F) {
                                          var25x -= 360.0F;
                                       } else if (var25x < -180.0F) {
                                          var25x += 360.0F;
                                       }

                                       if ((double)Math.abs(var25x) > 0.01) {
                                          var20x[0] += (double)var25x / 360.0 * var2 * var26;
                                       }

                                       float var28xx = var23xx.getPitch();
                                       if (var27x) {
                                          Float var29 = this.cursorVisualLockPitches.get(var1.getUniqueId());
                                          if (var29 != null && Float.isFinite(var29)) {
                                             if (Math.abs(var28xx - var29) > 0.01F) {
                                                var1.setRotation(var24xx, var29);
                                             }

                                             var28xx = var29;
                                          }
                                       }

                                       float var38 = var28xx - var23x[0];
                                       var23x[0] = var28xx;
                                       if (!var27x && (double)Math.abs(var38) > 0.01) {
                                          var21x[0] += (double)var38 / 180.0 * var4 * var26;
                                       }

                                       if (!var27x && var26x[0]) {
                                          double var30 = var24x[0];
                                          if (!Double.isFinite(var30) || var30 < 0.0) {
                                             var30 = var20x[0];
                                          }

                                          var20x[0] = var30;
                                          double var32 = var25[0];
                                          if (Double.isFinite(var32)) {
                                             var21x[0] = var32;
                                          }
                                       }

                                       if (!var27x) {
                                          if (var20x[0] < 0.0) {
                                             var20x[0] = 0.0;
                                          } else if (var20x[0] > var2) {
                                             var20x[0] = var2;
                                          }

                                          double var40 = var4 + var8;
                                          if (var21x[0] < var8) {
                                             var21x[0] = var8;
                                          } else if (var21x[0] > var40) {
                                             var21x[0] = var40;
                                          }
                                       }

                                       double var39 = var26xx == null ? var20x[0] : var26xx.getX();
                                       double var41 = var26xx == null ? var21x[0] : var26xx.getY();
                                       boolean var34 = Math.abs(var39 - var24x[0]) > 0.005 || Math.abs(var41 - var25[0]) > 0.005;
                                       if (var34) {
                                          Entity var35 = this.hudService.getHud(var1, "cursor");
                                          if (var35 != null) {
                                             double var36 = this.resolveRuntimeCursorLayer(var1.getUniqueId(), var15);
                                             this.hudService
                                                .moveHud(
                                                   var35,
                                                   new Vector(var39, var41, var36),
                                                   this.resolveEditorCursorInterpolationDurationTicks(),
                                                   this.resolveEditorCursorTeleportDurationTicks()
                                                );
                                          }

                                          var24x[0] = var39;
                                          var25[0] = var41;
                                       }

                                       if (this.cursorListener != null) {
                                          boolean var42 = var27x != var26x[0];
                                          boolean var43 = var34 || var42 || var27x;
                                          if (!var43) {
                                             var27[0]++;
                                             var43 = var27[0] >= 2;
                                          }

                                          if (var43) {
                                             this.cursorListener.onCursor(var1, var20x[0], var21x[0]);
                                             var27[0] = 0;
                                          }
                                       }

                                       var26x[0] = var27x;
                                    }
                                 } else {
                                    var21xx.cancel();
                                 }
                              }
                           }
                        );
                     }
                  }
               }
            },
            1L
         );
      }
   }

   private int resolveEditorCursorInterpolationDurationTicks() {
      return 1;
   }

   public void suppressStoneBreakAndHitSounds(Player var1) {
      if (var1 != null) {
         var1.stopSound(Sound.BLOCK_STONE_BREAK, SoundCategory.BLOCKS);
         var1.stopSound(Sound.BLOCK_STONE_HIT, SoundCategory.BLOCKS);
      }
   }

   public void refreshClientBarrierCube(Player var1) {
      if (var1 != null && var1.isOnline()) {
         if (this.cameraStatus.getOrDefault(var1.getUniqueId(), false)) {
            this.updateClientBarrierCube(var1, var1.getLocation(), true);
         }
      }
   }

   private double resolveRuntimeCursorLayer(UUID var1, double var2) {
      if (var1 == null) {
         return var2;
      } else {
         Double var4 = this.cameraCursorLayers.get(var1);
         return var4 != null && Double.isFinite(var4) ? var4 : var2;
      }
   }

   private double resolveEditorCursorMovementSpeed(double var1) {
      return Double.isFinite(var1) && var1 > 0.0 ? var1 : 1.0;
   }

   private int resolveEditorCursorTeleportDurationTicks() {
      return this.resolveEditorCursorInterpolationDurationTicks();
   }

   private void refreshCursorOpacity(Player var1) {
      Entity var2 = this.hudService.getHud(var1, "cursor");
      if (var2 != null) {
         int var3 = Math.max(0, Math.min(255, this.hudService.getHudOpacity(var2)));
         int var4 = var3 >= 255 ? 254 : Math.min(255, var3 + 1);
         if (var4 != var3) {
            this.hudService.setOpacity(var2, var4);
            PlatformCompat.runEntityTaskLater(this.plugin, var1, () -> {
               if (var1.isOnline() && this.cameraStatus.getOrDefault(var1.getUniqueId(), false)) {
                  Entity var3x = this.hudService.getHud(var1, "cursor");
                  if (var3x != null) {
                     this.hudService.setOpacity(var3x, var3);
                  }
               }
            }, 1L);
         }
      }
   }

   private void trackCameraEntity(Player var1, Entity var2) {
      if (var1 != null && var2 != null) {
         this.cameraInternalEntityIds.computeIfAbsent(var1.getUniqueId(), var0 -> new HashSet<>()).add(var2.getUniqueId());
      }
   }

   private void removeTrackedCameraEntities(Player var1) {
      if (var1 != null) {
         Set var2 = this.cameraInternalEntityIds.remove(var1.getUniqueId());
         if (var2 != null && !var2.isEmpty()) {
            for (UUID var4 : (Set<UUID>)(Set)var2) {
               Entity var5 = Bukkit.getEntity(var4);
               if (var5 != null && var5.isValid() && !var5.isDead()) {
                  var5.remove();
               }
            }
         }
      }
   }

   private void cleanupCameraRuntime(Player var1, boolean var2) {
      if (var1 != null) {
         UUID var3 = var1.getUniqueId();
         this.cameraRunTokens.merge(var3, 1, Integer::sum);
         this.cameraStatus.put(var3, false);
         if (var1.isInsideVehicle()) {
            var1.leaveVehicle();
         }

         this.removeTrackedCameraEntities(var1);
         Entity var4 = this.hudService.getHud(var1, "cursor");
         if (var4 != null) {
            this.hudService.removeHudX(Collections.singletonList(var4), false);
         }

         this.cursorVisualLocks.remove(var3);
         this.cursorVisualLockPitches.remove(var3);
         this.cameraCursorLayers.remove(var3);
         this.cameraBaseLocations.remove(var3);
         if (var1.isOnline()) {
            this.clearClientBarrierCube(var1);
            var1.removePotionEffect(PotionEffectType.INVISIBILITY);
            if (var2) {
               this.spectate(var1, null);
            }
         } else {
            this.clientBarrierBlocks.remove(var3);
            this.clientBarrierCenters.remove(var3);
         }
      }
   }

   private Location resolveCameraBaseLocation(Player var1) {
      UUID var2 = var1.getUniqueId();
      Location var3 = this.cameraBaseLocations.get(var2);
      if (var3 != null && var3.getWorld() != null && var3.getWorld().equals(var1.getWorld())) {
         return var3.clone();
      } else {
         Location var4 = var1.getLocation().clone();
         Entity var5 = var1.getVehicle();
         if (var5 != null && var5.hasMetadata("xqgui_internal_camera")) {
            double var6 = 0.0;
            double var8 = 0.0;
            return var4.subtract(0.0, var6, var8);
         } else {
            return var4;
         }
      }
   }

   private Location resolveNearbySafeCameraBaseLocation(Player var1, Location var2) {
      if (var1 != null && var2 != null) {
         World var3 = var2.getWorld();
         if (var3 == null) {
            return var2;
         } else if (this.isCameraSpawnSpaceOpen(var3, var2.getBlockX(), var2.getBlockY(), var2.getBlockZ())) {
            return var2.clone();
         } else {
            double var4 = Math.toRadians((double)var2.getYaw());
            Vector var6 = new Vector(-Math.sin(var4), 0.0, Math.cos(var4));
            if (var6.lengthSquared() < 1.0E-4) {
               var6 = new Vector(0.0, 0.0, 1.0);
            } else {
               var6.normalize();
            }

            Vector var7 = new Vector(var6.getZ(), 0.0, -var6.getX());
            Vector var8 = var7.clone().multiply(-1.0);
            Vector var9 = var6.clone().multiply(-1.0);
            List<Vector> var10 = List.of(var8, var7, var6, var9, var6.clone().add(var8), var6.clone().add(var7), var9.clone().add(var8), var9.clone().add(var7));
            double[] var11 = new double[]{2.0, 3.0};

            for (Object var15_raw : var11) {
               double var15 = ((Number)var15_raw).doubleValue();
               for (Vector var18 : var10) {
                  Vector var19 = var18.clone();
                  if (!(var19.lengthSquared() < 1.0E-4)) {
                     var19.normalize().multiply(var15);
                     Location var20 = this.resolveSafeCameraCandidate(var3, var2, var19.getX(), var19.getZ());
                     if (var20 != null) {
                        return var20;
                     }
                  }
               }
            }

            return var2;
         }
      } else {
         return var2;
      }
   }

   private Location resolveSafeCameraCandidate(World var1, Location var2, double var3, double var5) {
      if (var1 != null && var2 != null) {
         int var7 = (int)Math.floor(var2.getX() + var3);
         int var8 = (int)Math.floor(var2.getZ() + var5);
         int var9 = var2.getBlockY();
         int var10 = var1.getMinHeight();
         int var11 = var1.getMaxHeight() - 1;
         int[] var12 = new int[]{0, 1, -1, 2, -2};

         for (Object var16_raw : var12) {
            int var16 = ((Number)var16_raw).intValue();
            int var17 = var9 + var16;
            if (var17 >= var10 && var17 + 1 <= var11 && this.isCameraSpawnSpaceOpen(var1, var7, var17, var8)) {
               return new Location(var1, (double)var7 + 0.5, (double)var17, (double)var8 + 0.5, var2.getYaw(), var2.getPitch());
            }
         }

         return null;
      } else {
         return null;
      }
   }

   private boolean isCameraSpawnSpaceOpen(World var1, int var2, int var3, int var4) {
      return var1 == null ? false : !var1.getBlockAt(var2, var3, var4).getType().isSolid() && !var1.getBlockAt(var2, var3 + 1, var4).getType().isSolid();
   }

   private void updateClientBarrierCube(Player var1, Location var2, boolean var3) {
      if (var1 != null && var2 != null) {
         World var4 = var1.getWorld();
         UUID var5 = var1.getUniqueId();
         int var6 = var2.getBlockX();
         int var7 = var2.getBlockY();
         int var8 = (int)Math.round(0.0);
         int var9 = var2.getBlockZ() - var8;
         CameraService.BlockPos var10 = new CameraService.BlockPos(var6, var7, var9);
         CameraService.BlockPos var11 = this.clientBarrierCenters.get(var5);
         if (var3 || !var10.equals(var11)) {
            this.clientBarrierCenters.put(var5, var10);
            Set<CameraService.BlockPos> var12 = this.clientBarrierBlocks.computeIfAbsent(var5, var0 -> new HashSet<>());
            HashSet var13 = new HashSet();

            for (int var14 = -2; var14 <= 2; var14++) {
               for (int var15 = -2; var15 <= 2; var15++) {
                  for (int var16 = -2; var16 <= 2; var16++) {
                     int var17 = var6 + var14;
                     int var18 = var7 + var15;
                     int var19 = var9 + var16;
                     if (var4.getBlockAt(var17, var18, var19).getType().isAir()) {
                        CameraService.BlockPos var20 = new CameraService.BlockPos(var17, var18, var19);
                        var13.add(var20);
                        if (var3 || !var12.contains(var20)) {
                           var1.sendBlockChange(new Location(var4, (double)var17, (double)var18, (double)var19), CLIENT_BARRIER_DATA);
                        }
                     }
                  }
               }
            }

            for (CameraService.BlockPos var22 : var12) {
               if (!var13.contains(var22)) {
                  var1.sendBlockChange(
                     new Location(var4, (double)var22.x, (double)var22.y, (double)var22.z), var4.getBlockAt(var22.x, var22.y, var22.z).getBlockData()
                  );
               }
            }

            var12.clear();
            var12.addAll(var13);
         }
      }
   }

   private void clearClientBarrierCube(Player var1) {
      UUID var2 = var1.getUniqueId();
      this.clientBarrierCenters.remove(var2);
      Set<CameraService.BlockPos> var3 = this.clientBarrierBlocks.remove(var2);
      if (var3 != null && !var3.isEmpty()) {
         World var4 = var1.getWorld();

         for (CameraService.BlockPos var6 : var3) {
            var1.sendBlockChange(new Location(var4, (double)var6.x, (double)var6.y, (double)var6.z), var4.getBlockAt(var6.x, var6.y, var6.z).getBlockData());
         }
      }
   }

   private static final class BlockPos {
      private final int x;
      private final int y;
      private final int z;

      private BlockPos(int var1, int var2, int var3) {
         this.x = var1;
         this.y = var2;
         this.z = var3;
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else {
            return !(var1 instanceof CameraService.BlockPos var2) ? false : this.x == var2.x && this.y == var2.y && this.z == var2.z;
         }
      }

      @Override
      public int hashCode() {
         int var1 = Integer.hashCode(this.x);
         var1 = 31 * var1 + Integer.hashCode(this.y);
         return 31 * var1 + Integer.hashCode(this.z);
      }
   }

   public interface CursorListener {
      void onCursor(Player var1, double var2, double var4);
   }
}
