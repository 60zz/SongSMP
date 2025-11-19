package net.mcreator.vlabyss.item;

import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

import net.mcreator.vlabyss.VlAbyssMod;

public class NyvathBannerPatternItem extends BannerPatternItem {
	public static final TagKey<BannerPattern> PROVIDED_PATTERNS = TagKey.create(Registries.BANNER_PATTERN, ResourceLocation.fromNamespaceAndPath(VlAbyssMod.MODID, "pattern_item/nyvath_banner_pattern"));

	public NyvathBannerPatternItem() {
		super(PROVIDED_PATTERNS, new Item.Properties().stacksTo(1).rarity(Rarity.EPIC));
	}

	public MutableComponent getDisplayName() {
		return Component.translatable(this.getDescriptionId() + ".patterns");
	}
}