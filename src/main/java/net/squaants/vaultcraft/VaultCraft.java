package net.squaants.vaultcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.squaants.vaultcraft.block.ModBlocks;
import net.squaants.vaultcraft.blockentity.ModBlockEntities;
import net.squaants.vaultcraft.item.ModCreativeModeTabs;
import net.squaants.vaultcraft.item.ModItems;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(VaultCraft.MOD_ID)
public class VaultCraft {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "vaultcraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public VaultCraft(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SILVER_INGOT);
            event.accept(ModItems.STEEL_INGOT);
            event.accept(ModItems.RAW_SILVER);
            event.accept(ModItems.RAW_ULTRACITE);
            event.accept(ModItems.RAW_URANIUM);
            event.accept(ModItems.FUEL);
            event.accept(ModItems.DEATHCLAW_OMELETTE);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.METAL_BLOCK);
            event.accept(ModBlocks.METAL_ENGRAVED);

            event.accept(ModBlocks.SILVER_ORE);
            event.accept(ModBlocks.ULTRACITE_ORE);
            event.accept(ModBlocks.URANIUM_ORE);
            event.accept(ModBlocks.DEEPSLATE_SILVER_ORE);
            event.accept(ModBlocks.DEEPSLATE_ULTRACITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_URANIUM_ORE);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.SILVER_INGOT);
            event.accept(ModItems.STEEL_INGOT);
            event.accept(ModItems.RAW_SILVER);
            event.accept(ModItems.RAW_ULTRACITE);
            event.accept(ModItems.RAW_URANIUM);
            event.accept(ModItems.FUEL);
            event.accept(ModItems.DEATHCLAW_OMELETTE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
