package net.mcreator.vlabyss.procedures;

import top.theillusivec4.curios.api.CuriosApi;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.NetworkDirection;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.network.Connection;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import net.mcreator.vlabyss.network.VlAbyssModVariables;
import net.mcreator.vlabyss.init.VlAbyssModMobEffects;
import net.mcreator.vlabyss.init.VlAbyssModItems;
import net.mcreator.vlabyss.init.VlAbyssModEntities;
import net.mcreator.vlabyss.entity.RespiroPrimariaEntity;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Iterator;
import java.util.Comparator;

public class HabilidadePrimariaOnKeyPressedProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
Entity target = null;
if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Respiro>=1&&(entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).MantraRegistrada==true) {if (!(entity instanceof LivingEntity _livEnt0 && _livEnt0.hasEffect(VlAbyssModMobEffects.COOLDOWN_RESPIRO.get()))) {if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Ethir>=40) {{
double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Ethir-40;
entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.Ethir = _setval;
capability.syncPlayerVariables(entity);
});
}
if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.COOLDOWN_RESPIRO.get(),200,0));if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1")),
SoundSource.MASTER, 1, 1, false);
}
}
if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1_1")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1_1")),
SoundSource.MASTER, 1, 1, false);
}
}
if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1_2")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:respiro_hab1_2")),
SoundSource.MASTER, 1, 1, false);
}
}
if (world.isClientSide()) {
SetupAnimationsProcedure.setAnimationClientside((Player)entity, "mantrarespiro1", );
}
if (!world.isClientSide()) {
if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
synchronized(connections) {
Iterator<Connection> iterator = connections.iterator();
while(iterator.hasNext()) {
Connection connection = iterator.next();
if (!connection.isConnecting() && connection.isConnected())
VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("mantrarespiro1"), entity.getId(), ), connection, NetworkDirection.PLAY_TO_CLIENT);
}
}
}
}VlAbyssMod.queueServerWork(20, () -> {
if (entity instanceof LivingEntity lv ? CuriosApi.getCuriosHelper().findEquippedCurio(VlAbyssModItems.CAPUZ_ESQUECIDO.get(), lv).isPresent()
: false) {{
Entity _shootFrom = entity;
Level projectileLevel = _shootFrom.level();
if (!projectileLevel.isClientSide()) {
Projectile _entityToSpawn = new Object() {
public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
AbstractArrow entityToSpawn = new RespiroPrimariaEntity(VlAbyssModEntities.RESPIRO_PRIMARIA.get(), level);
entityToSpawn.setOwner(shooter);
entityToSpawn.setBaseDamage(damage);
entityToSpawn.setKnockback(knockback);
entityToSpawn.setSilent(true);
return entityToSpawn;
}
}.getArrow(projectileLevel, entity, 12, 1);
_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 1, 0);
projectileLevel.addFreshEntity(_entityToSpawn);
}
}}else{{
Entity _shootFrom = entity;
Level projectileLevel = _shootFrom.level();
if (!projectileLevel.isClientSide()) {
Projectile _entityToSpawn = new Object() {
public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
AbstractArrow entityToSpawn = new RespiroPrimariaEntity(VlAbyssModEntities.RESPIRO_PRIMARIA.get(), level);
entityToSpawn.setOwner(shooter);
entityToSpawn.setBaseDamage(damage);
entityToSpawn.setKnockback(knockback);
entityToSpawn.setSilent(true);
return entityToSpawn;
}
}.getArrow(projectileLevel, entity, 8, 1);
_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 1, 0);
projectileLevel.addFreshEntity(_entityToSpawn);
}
}}
});
if (Minecraft.getInstance().options.keyShift.isDown()) {entity.setDeltaMovement(new Vec3(0, 1, 0));if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,40,2));}}else{if (entity instanceof Player _player && !_player.level().isClientSide())
_player.displayClientMessage(Component.literal("\u00A7cSem \"ETHIR\" o suficiente"), true);if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")),
SoundSource.MASTER, 1, 1, false);
}
}
}}}else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Escuridao>=1&&(entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).MantraRegistrada==true) {if (!(entity instanceof LivingEntity _livEnt17 && _livEnt17.hasEffect(VlAbyssModMobEffects.COOLDOWN_RESPIRO.get()))) {if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Ethir>=85) {{
double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Ethir-85;
entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.Ethir = _setval;
capability.syncPlayerVariables(entity);
});
}
if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.SHADOW_COPY.get(),1200,0));if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.COOLDOWN_RESPIRO.get(),3600,0));if (world.isClientSide()) {
SetupAnimationsProcedure.setAnimationClientside((Player)entity, "shadowcopy", );
}
if (!world.isClientSide()) {
if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
synchronized(connections) {
Iterator<Connection> iterator = connections.iterator();
while(iterator.hasNext()) {
Connection connection = iterator.next();
if (!connection.isConnecting() && connection.isConnected())
VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("shadowcopy"), entity.getId(), ), connection, NetworkDirection.PLAY_TO_CLIENT);
}
}
}
}}else{if (entity instanceof Player _player && !_player.level().isClientSide())
_player.displayClientMessage(Component.literal("\u00A7cSem \"ETHIR\" o suficiente"), true);if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")),
SoundSource.MASTER, 1, 1, false);
}
}
}}}else if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Luz>=1&&(entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).MantraRegistrada==true) {if (!(entity instanceof LivingEntity _livEnt23 && _livEnt23.hasEffect(VlAbyssModMobEffects.COOLDOWN_RESPIRO.get()))) {if ((entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Ethir>=95) {if (!world.getEntitiesOfClass(Player.class,
AABB.ofSize(new Vec3((entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getX()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getY()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())), 3, 3, 3), e -> true)
.isEmpty()) {target = (Entity) world.getEntitiesOfClass(Player.class,
AABB.ofSize(new Vec3((entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getX()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getY()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())), 3, 3, 3), e -> true)
.stream().sorted(new Object() {
Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
}
}.compareDistOf((entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getX()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getY()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()))).findFirst().orElse(null);if (!(target == null)) {if (target instanceof LivingEntity _entity) _entity.setHealth((target instanceof LivingEntity _livEnt ? _livEnt.getHealth():-1)+10);if(target instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,160,1));if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.COOLDOWN_RESPIRO.get(),6000,0));if (world.isClientSide()) {
SetupAnimationsProcedure.setAnimationClientside((Player)entity, "luzprimeira", );
}
if (!world.isClientSide()) {
if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
synchronized(connections) {
Iterator<Connection> iterator = connections.iterator();
while(iterator.hasNext()) {
Connection connection = iterator.next();
if (!connection.isConnecting() && connection.isConnected())
VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("luzprimeira"), entity.getId(), ), connection, NetworkDirection.PLAY_TO_CLIENT);
}
}
}
}if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")),
SoundSource.MASTER, 1, 1, false);
}
}
VlAbyssMod.queueServerWork(40, () -> {
if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")),
SoundSource.MASTER, 1, 1, false);
}
}
});
{
double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Ethir-95;
entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.Ethir = _setval;
capability.syncPlayerVariables(entity);
});
}
}}else if (!world.getEntitiesOfClass(Monster.class,
AABB.ofSize(new Vec3((entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getX()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getY()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())), 3, 3, 3), e -> true)
.isEmpty()) {target = (Entity) world.getEntitiesOfClass(Monster.class,
AABB.ofSize(new Vec3((entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getX()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getY()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())), 3, 3, 3), e -> true)
.stream().sorted(new Object() {
Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
}
}.compareDistOf((entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getX()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getY()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()))).findFirst().orElse(null);if (!(target == null)) {if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.COOLDOWN_RESPIRO.get(),6000,0));if (world.isClientSide()) {
SetupAnimationsProcedure.setAnimationClientside((Player)entity, "luzprimeira", );
}
if (!world.isClientSide()) {
if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
synchronized(connections) {
Iterator<Connection> iterator = connections.iterator();
while(iterator.hasNext()) {
Connection connection = iterator.next();
if (!connection.isConnecting() && connection.isConnected())
VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("luzprimeira"), entity.getId(), ), connection, NetworkDirection.PLAY_TO_CLIENT);
}
}
}
}if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")),
SoundSource.MASTER, 1, 1, false);
}
}
VlAbyssMod.queueServerWork(40, () -> {
if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")),
SoundSource.MASTER, 1, 1, false);
}
}
});
if (target instanceof LivingEntity _entity) _entity.setHealth((target instanceof LivingEntity _livEnt ? _livEnt.getHealth():-1)-6);if(target instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.WITHER,100,0));if(target instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.CORTA_REGEN.get(),200,0));{
double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Ethir-95;
entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.Ethir = _setval;
capability.syncPlayerVariables(entity);
});
}
}}else if (!world.getEntitiesOfClass(PathfinderMob.class,
AABB.ofSize(new Vec3((entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getX()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getY()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())), 3, 3, 3), e -> true)
.isEmpty()) {target = (Entity) world.getEntitiesOfClass(PathfinderMob.class,
AABB.ofSize(new Vec3((entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getX()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getY()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())), 3, 3, 3), e -> true)
.stream().sorted(new Object() {
Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
}
}.compareDistOf((entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getX()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getY()), (entity.level().clip(new ClipContext(entity.getEyePosition(1f),entity.getEyePosition(1f)
.add(entity.getViewVector(1f).scale(5)), ClipContext.Block.OUTLINE,
ClipContext.Fluid.NONE, entity)).getBlockPos().getZ()))).findFirst().orElse(null);if (!(target == null)) {if (target instanceof LivingEntity _entity) _entity.setHealth((entity instanceof LivingEntity _livEnt ? _livEnt.getHealth():-1)+10);if(target instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION,160,1));if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(VlAbyssModMobEffects.COOLDOWN_RESPIRO.get(),6000,0));if (world.isClientSide()) {
SetupAnimationsProcedure.setAnimationClientside((Player)entity, "luzprimeira", );
}
if (!world.isClientSide()) {
if (entity instanceof Player && world instanceof ServerLevel srvLvl_) {
List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
synchronized(connections) {
Iterator<Connection> iterator = connections.iterator();
while(iterator.hasNext()) {
Connection connection = iterator.next();
if (!connection.isConnecting() && connection.isConnected())
VlAbyssMod.PACKET_HANDLER.sendTo(new SetupAnimationsProcedure.VlAbyssModAnimationMessage(Component.literal("luzprimeira"), entity.getId(), ), connection, NetworkDirection.PLAY_TO_CLIENT);
}
}
}
}if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:usamantraluzum")),
SoundSource.MASTER, 1, 1, false);
}
}
VlAbyssMod.queueServerWork(40, () -> {
if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:atingemagialuzum")),
SoundSource.MASTER, 1, 1, false);
}
}
});
{
double _setval = (entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null)
.orElse(new VlAbyssModVariables.PlayerVariables())).Ethir-95;
entity.getCapability(VlAbyssModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
capability.Ethir = _setval;
capability.syncPlayerVariables(entity);
});
}
}}}else{if (entity instanceof Player _player && !_player.level().isClientSide())
_player.displayClientMessage(Component.literal("\u00A7cSem \"ETHIR\" o suficiente"), true);if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:sem_ethir_som")),
SoundSource.MASTER, 1, 1, false);
}
}
}}}
}
}