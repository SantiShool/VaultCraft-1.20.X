package net.squaants.vaultcraft.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.block.custom.Jukebox;
import net.squaants.vaultcraft.block.custom.RustingBlock;
import net.squaants.vaultcraft.block.custom.RustingSlabBlock;
import net.squaants.vaultcraft.block.custom.RustingStairBlock;
import net.squaants.vaultcraft.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, VaultCraft.MOD_ID);

    // METAL BLOCK
    public static final RegistryObject<Block> METAL_BLOCK = registerBlock("metal_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(60.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<StairBlock> METAL_STAIRS = registerBlock("metal_stairs",
                    () -> new StairBlock(() -> METAL_BLOCK.get().defaultBlockState(),
                            BlockBehaviour.Properties.copy(METAL_BLOCK.get())));

    public static final RegistryObject<SlabBlock> METAL_SLAB = registerBlock("metal_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(METAL_BLOCK.get())));


    public static final RegistryObject<Block> RUSTY_WAREHOUSE_BLOCK = registerBlock("rusty_warehouse_block",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops().strength(15.0F, 2.0F)));

    public static final RegistryObject<Block> WAREHOUSE_BLOCK = registerBlock("warehouse_block",
            () -> new RustingBlock(() -> RUSTY_WAREHOUSE_BLOCK.get(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                            .requiresCorrectToolForDrops().strength(30.0F, 4.0F).sound(SoundType.METAL)));

    public static final RegistryObject<StairBlock> RUSTY_WAREHOUSE_STAIRS = registerBlock("rusty_warehouse_stairs",
                    () -> new StairBlock(() -> RUSTY_WAREHOUSE_BLOCK.get().defaultBlockState(),
                            BlockBehaviour.Properties.copy(RUSTY_WAREHOUSE_BLOCK.get())));

    public static final RegistryObject<SlabBlock> RUSTY_WAREHOUSE_SLAB = registerBlock("rusty_warehouse_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(RUSTY_WAREHOUSE_BLOCK.get())));

    public static final RegistryObject<StairBlock> WAREHOUSE_STAIRS = registerBlock("warehouse_stairs",
            () -> new RustingStairBlock(() -> RUSTY_WAREHOUSE_STAIRS.get(),
                    BlockBehaviour.Properties.copy(WAREHOUSE_BLOCK.get()), () -> WAREHOUSE_BLOCK.get().defaultBlockState()));

    public static final RegistryObject<SlabBlock> WAREHOUSE_SLAB = registerBlock("warehouse_slab",
            () -> new RustingSlabBlock(() -> RUSTY_WAREHOUSE_SLAB.get(),
                    BlockBehaviour.Properties.copy(WAREHOUSE_BLOCK.get())));


    public static final RegistryObject<Block> JUKEBOX = registerBlock("jukebox",
            () -> new Jukebox(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));


    public static final RegistryObject<Block> BAUXITE_ORE = registerBlock("bauxite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final RegistryObject<Block> BLACK_TITANIUM_ORE = registerBlock("black_titanium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final RegistryObject<Block> LEAD_ORE = registerBlock("lead_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final RegistryObject<Block> ULTRACITE_ORE = registerBlock("ultracite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .instrument(NoteBlockInstrument.BASEDRUM).strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> DEEPSLATE_BAUXITE_ORE = registerBlock("deepslate_bauxite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(4.5F, 3.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_BLACK_TITANIUM_ORE = registerBlock("deepslate_black_titanium_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(4.5F, 3.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_LEAD_ORE = registerBlock("deepslate_lead_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(4.5F, 3.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(4.5F, 3.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> DEEPSLATE_ULTRACITE_ORE = registerBlock("deepslate_ultracite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(4.5F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> DEEPSLATE_URANIUM_ORE = registerBlock("deepslate_uranium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(4.5F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}