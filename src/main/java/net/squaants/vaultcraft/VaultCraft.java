package net.squaants.vaultcraft;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
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

@Mod(VaultCraft.MOD_ID)
public class VaultCraft {

    public static final String MOD_ID = "vaultcraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public VaultCraft(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // common init here if you need it later
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            // (empty for now)
        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.BAUXITE_ORE);
            event.accept(ModBlocks.SILVER_ORE);
            event.accept(ModBlocks.ULTRACITE_ORE);
            event.accept(ModBlocks.URANIUM_ORE);
            event.accept(ModBlocks.DEEPSLATE_BAUXITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_SILVER_ORE);
            event.accept(ModBlocks.DEEPSLATE_ULTRACITE_ORE);
            event.accept(ModBlocks.DEEPSLATE_URANIUM_ORE);
        }

        if (event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            // (empty for now)
        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            // (empty for now)
        }

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            // (empty for now)
        }

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.ACACIA_LOG);
            event.accept(ModItems.BIRCH_LOG);
            event.accept(ModItems.CHERRY_LOG);
            event.accept(ModItems.DARK_OAK_LOG);
            event.accept(ModItems.JUNGLE_LOG);
            event.accept(ModItems.MANGROVE_LOG);
            event.accept(ModItems.OAK_LOG);
            event.accept(ModItems.SPRUCE_LOG);

            event.accept(ModItems.ACACIA_PLANK);
            event.accept(ModItems.BIRCH_PLANK);
            event.accept(ModItems.CHERRY_PLANK);
            event.accept(ModItems.DARK_OAK_PLANK);
            event.accept(ModItems.JUNGLE_PLANK);
            event.accept(ModItems.MANGROVE_PLANK);
            event.accept(ModItems.OAK_PLANK);
            event.accept(ModItems.SPRUCE_PLANK);

            event.accept(ModItems.ALUMINUM_INGOT);
            event.accept(ModItems.SILVER_INGOT);
            event.accept(ModItems.STEEL_INGOT);
            event.accept(ModItems.RAW_BAUXITE);
            event.accept(ModItems.RAW_SILVER);
            event.accept(ModItems.RAW_ULTRACITE);
            event.accept(ModItems.RAW_URANIUM);
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // server startup stuff if needed
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                // translucent so the glass looks right
                ItemBlockRenderTypes.setRenderLayer(
                        ModBlocks.JUKEBOX.get(),
                        RenderType.translucent()
                );
            });
        }
    }
}