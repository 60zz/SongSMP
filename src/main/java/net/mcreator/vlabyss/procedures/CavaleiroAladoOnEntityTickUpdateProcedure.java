package net.mcreator.vlabyss.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.BlockPos;

import net.mcreator.vlabyss.entity.CavaleiroAladoEntity;
import net.mcreator.vlabyss.configuration.SsmpConfiguration;
import net.mcreator.vlabyss.VlAbyssMod;

import java.util.List;
import java.util.Comparator;

public class CavaleiroAladoOnEntityTickUpdateProcedure {
public static void execute(
LevelAccessor world,
double x,
double y,
double z,
Entity entity ) {
if (
entity == null ) return ;
if (entity.getPersistentData().getDouble("manaregen")<=14) {entity.getPersistentData().putDouble("manaregen", (entity.getPersistentData().getDouble("manaregen")+1));}if (entity.getPersistentData().getDouble("manaregen")>=15) {if (!(entity.getPersistentData().getDouble("mana_atual")==entity.getPersistentData().getDouble("mana_total"))) {if ((entity instanceof CavaleiroAladoEntity _datEntL6 &&
_datEntL6.getEntityData().get(CavaleiroAladoEntity.DATA_ataquevoador))==false&&(entity instanceof CavaleiroAladoEntity _datEntL7 &&
_datEntL7.getEntityData().get(CavaleiroAladoEntity.DATA_ataquechao))==false) {entity.getPersistentData().putDouble("mana_atual", (entity.getPersistentData().getDouble("mana_atual")+2));entity.getPersistentData().putDouble("manaregen", 0);}}}if () {if (Math.random()>=0&&!(Math.random()>=0.5)) {if (entity.getPersistentData().getDouble("mana_atual")>=120) {if ((entity instanceof CavaleiroAladoEntity _datEntL12 &&
_datEntL12.getEntityData().get(CavaleiroAladoEntity.DATA_ataquevoador))==false&&(entity instanceof CavaleiroAladoEntity _datEntL13 &&
_datEntL13.getEntityData().get(CavaleiroAladoEntity.DATA_ataquechao))==false) {if (entity instanceof CavaleiroAladoEntity) {
((CavaleiroAladoEntity) entity).setAnimation("animation.model.ataquevoador");
}if (entity instanceof CavaleiroAladoEntity _datEntSetL)
_datEntSetL.getEntityData().set(CavaleiroAladoEntity.DATA_ataquevoador, true);entity.setDeltaMovement(new Vec3(0, 5, 0));if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING,280,254));VlAbyssMod.queueServerWork(305, () -> {
{
final Vec3 _center = new Vec3(x, y, z);
List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(12 / 2d), e -> true)
.stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
for (Entity entityiterator : _entfound) {
if (!(entityiterator instanceof CavaleiroAladoEntity)) {entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:danoabismoboss"))), entity), (float)(double) SsmpConfiguration.WINGEDKNIGHTFLYINGAXEGROUNDATTACKDAMAGE.get());}if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:batida_cavaleiro_alado")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:batida_cavaleiro_alado")),
SoundSource.MASTER, 1, 1, false);
}
}
}
}
});
VlAbyssMod.queueServerWork(320, () -> {
if (entity instanceof CavaleiroAladoEntity) {
((CavaleiroAladoEntity) entity).setAnimation("empty");
}if (entity instanceof CavaleiroAladoEntity) {
((CavaleiroAladoEntity) entity).setAnimation("animation.model.preso no ch\u00E3o");
}VlAbyssMod.queueServerWork(160, () -> {
if (entity instanceof CavaleiroAladoEntity _datEntSetL)
_datEntSetL.getEntityData().set(CavaleiroAladoEntity.DATA_ataquevoador, false);
});
});
entity.getPersistentData().putDouble("mana_atual", (entity.getPersistentData().getDouble("mana_atual")-120));}}}else if (Math.random()>=0.5) {if (entity.getPersistentData().getDouble("mana_atual")>=80) {if ((entity instanceof CavaleiroAladoEntity _datEntL33 &&
_datEntL33.getEntityData().get(CavaleiroAladoEntity.DATA_ataquevoador))==false&&(entity instanceof CavaleiroAladoEntity _datEntL34 &&
_datEntL34.getEntityData().get(CavaleiroAladoEntity.DATA_ataquechao))==false) {if (entity instanceof CavaleiroAladoEntity) {
((CavaleiroAladoEntity) entity).setAnimation("animation.model.ataqueno ch\u00E3o");
}if(entity instanceof LivingEntity _entity && !_entity.level().isClientSide())
_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN,80,254));VlAbyssMod.queueServerWork(75, () -> {
{
final Vec3 _center = new Vec3(x, y, z);
List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true)
.stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
for (Entity entityiterator : _entfound) {
if (!(entityiterator instanceof CavaleiroAladoEntity)) {entityiterator.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.parse("vl_abyss:danoabismoboss"))), entity), (float)(double) SsmpConfiguration.WINGEDKNIGHTGROUNDAXEATTACKDAMAGE.get());}if (world instanceof Level _level) {
if (!_level.isClientSide()) {
_level.playSound(null, BlockPos.containing(x,y,z),
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:batida_cavaleiro_alado")),
SoundSource.MASTER, 1, 1);
} else {
_level.playLocalSound(x, y, z,
ForgeRegistries.SOUND_EVENTS.getValue(ResourceLocation.parse("vl_abyss:batida_cavaleiro_alado")),
SoundSource.MASTER, 1, 1, false);
}
}
}
}if (entity instanceof CavaleiroAladoEntity _datEntSetL)
_datEntSetL.getEntityData().set(CavaleiroAladoEntity.DATA_ataquechao, false);
});
if (entity instanceof CavaleiroAladoEntity _datEntSetL)
_datEntSetL.getEntityData().set(CavaleiroAladoEntity.DATA_ataquechao, true);entity.getPersistentData().putDouble("mana_atual", (entity.getPersistentData().getDouble("mana_atual")-80));}}}}
}
}