
package net.mcreator.vlabyss.item;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoItem;

import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.Item;
// MODIFICADO: Adicionados imports necessários para transformar em SwordItem
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.SwordItem;
//
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;

import net.mcreator.vlabyss.item.renderer.AdagaAssassinosItemRenderer;

import java.util.function.Consumer;

import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap;

public class AdagaAssassinosItem extends SwordItem implements GeoItem {
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	public String animationprocedure = "empty";
	public static ItemDisplayContext transformType;

	public AdagaAssassinosItem() {
		// MODIFICADO: Valor mudado de 7 para 4 para evitar duplicação
		// Tiers.IRON (3 base) + 3 adicional = 7 total de Attack Damage
		super(Tiers.IRON, 7, -2.0f, new Properties().durability(1853).rarity(Rarity.EPIC));
	}

	@Override
	public void initializeClient(Consumer<IClientItemExtensions> consumer) {
		super.initializeClient(consumer);
		consumer.accept(new IClientItemExtensions() {
			private final BlockEntityWithoutLevelRenderer renderer = new AdagaAssassinosItemRenderer();

			@Override
			public BlockEntityWithoutLevelRenderer getCustomRenderer() {
				return renderer;
			}
		});
	}

	public void getTransformType(ItemDisplayContext type) {
		this.transformType = type;
	}

	private PlayState idlePredicate(AnimationState event) {
		if (this.transformType != null ? true : false) {
			if (this.animationprocedure.equals("empty")) {
				event.getController().setAnimation(RawAnimation.begin().thenLoop("model.animation.0"));
				return PlayState.CONTINUE;
			}
		}
		return PlayState.STOP;
	}

	private PlayState procedurePredicate(AnimationState event) {
		if (this.transformType != null ? true : false) {
			if (!this.animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
				if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
					this.animationprocedure = "empty";
					event.getController().forceAnimationReset();
				}
			} else if (this.animationprocedure.equals("empty")) {
				return PlayState.STOP;
			}
		}
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		AnimationController procedureController = new AnimationController(this, "procedureController", 0, this::procedurePredicate);
		data.add(procedureController);
		AnimationController idleController = new AnimationController(this, "idleController", 0, this::idlePredicate);
		data.add(idleController);
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}

	@Override
	public int getEnchantmentValue() {
		return 10;
	}


	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot) {
		if (equipmentSlot == EquipmentSlot.MAINHAND) {
			// MODIFICADO: Removida toda a lógica de adicionar atributos extras
			// para evitar valores duplicados na tooltip
			// Agora usa APENAS os valores definidos no construtor do SwordItem
			return super.getDefaultAttributeModifiers(equipmentSlot);
		}
		return super.getDefaultAttributeModifiers(equipmentSlot);
	}
}