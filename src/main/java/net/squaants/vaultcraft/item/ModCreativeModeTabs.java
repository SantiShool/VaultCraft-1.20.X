package net.squaants.vaultcraft.item;

import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, VaultCraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> VC_BUILDING_BLOCKS = CREATIVE_MODE_TABS.register("vc_building_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SILVER_ORE.get()))
                    .title(Component.translatable("creativetab.vc_building_blocks"))
                    .displayItems((displayParameters, output) -> {

                        output.accept(ModBlocks.METAL_WALL.get());


                        output.accept(ModBlocks.SILVER_ORE.get());
                        output.accept(ModBlocks.ULTRACITE_ORE.get());
                        output.accept(ModBlocks.URANIUM_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_SILVER_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_ULTRACITE_ORE.get());
                        output.accept(ModBlocks.DEEPSLATE_URANIUM_ORE.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> VC_INGREDIENTS = CREATIVE_MODE_TABS.register("vc_ingredients",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SILVER_INGOT.get()))
                    .title(Component.translatable("creativetab.vc_ingredients"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ModItems.FUEL.get());

                        output.accept(ModItems.SILVER_INGOT.get());
                        output.accept(ModItems.STEEL_INGOT.get());
                        output.accept(ModItems.RAW_SILVER.get());
                        output.accept(ModItems.RAW_ULTRACITE.get());
                        output.accept(ModItems.RAW_URANIUM.get());

                    }).build());

    public static final RegistryObject<CreativeModeTab> VC_CONSUMABLES = CREATIVE_MODE_TABS.register("vc_consumables",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DEATHCLAW_OMELETTE.get()))
                    .title(Component.translatable("creativetab.vc_ingredients"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ModItems.DEATHCLAW_OMELETTE.get());

                    }).build());



    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}