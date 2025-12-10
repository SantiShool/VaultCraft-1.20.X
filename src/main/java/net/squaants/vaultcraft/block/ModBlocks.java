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


    public static final RegistryObject<Block> METAL_BRICK = registerBlock("metal_brick",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(-1.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> METAL_ENGRAVED = registerBlock("metal_engraved",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(-1.0F, 6.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> METAL_PANELING = registerBlock("metal_paneling",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(-1.0F, 6.0F).sound(SoundType.METAL)));


    public static final RegistryObject<StairBlock> METAL_BRICK_STAIRS = registerBlock("metal_brick_stairs",
                    () -> new StairBlock(() -> METAL_BRICK.get().defaultBlockState(),
                            BlockBehaviour.Properties.copy(METAL_BRICK.get())));

    public static final RegistryObject<SlabBlock> METAL_BRICK_SLAB = registerBlock("metal_brick_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(METAL_BRICK.get())));

    public static final RegistryObject<StairBlock> METAL_ENGRAVED_STAIRS = registerBlock("metal_engraved_stairs",
                    () -> new StairBlock(() -> METAL_ENGRAVED.get().defaultBlockState(),
                            BlockBehaviour.Properties.copy(METAL_ENGRAVED.get())));

    public static final RegistryObject<SlabBlock> METAL_ENGRAVED_SLAB = registerBlock("metal_engraved_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(METAL_ENGRAVED.get())));

    public static final RegistryObject<StairBlock> METAL_PANELING_STAIRS = registerBlock("metal_paneling_stairs",
                    () -> new StairBlock(() -> METAL_PANELING.get().defaultBlockState(),
                            BlockBehaviour.Properties.copy(METAL_PANELING.get())));

    public static final RegistryObject<SlabBlock> METAL_PANELING_SLAB = registerBlock("metal_paneling_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(METAL_PANELING.get())));


    public static final RegistryObject<Block> RUSTY_WAREHOUSE_PLATING = registerBlock("rusty_warehouse_plating",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops().strength(-1.0F, 2.0F)));

    public static final RegistryObject<Block> RUSTY_WAREHOUSE_TREADING = registerBlock("rusty_warehouse_treading",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops().strength(-1.0F, 2.0F)));

    public static final RegistryObject<Block> RUSTY_WAREHOUSE_WALLING = registerBlock("rusty_warehouse_walling",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .sound(SoundType.METAL).requiresCorrectToolForDrops().strength(-1.0F, 2.0F)));


    public static final RegistryObject<Block> WAREHOUSE_PLATING = registerBlock("warehouse_plating",
            () -> new RustingBlock(() -> RUSTY_WAREHOUSE_PLATING.get(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                            .requiresCorrectToolForDrops().strength(-1.0F, 4.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> WAREHOUSE_TREADING = registerBlock("warehouse_treading",
            () -> new RustingBlock(() -> RUSTY_WAREHOUSE_TREADING.get(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                            .requiresCorrectToolForDrops().strength(-1.0F, 4.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> WAREHOUSE_WALLING = registerBlock("warehouse_walling",
            () -> new RustingBlock(() -> RUSTY_WAREHOUSE_WALLING.get(),
                    BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                            .requiresCorrectToolForDrops().strength(-1.0F, 4.0F).sound(SoundType.METAL)));


    public static final RegistryObject<StairBlock> RUSTY_WAREHOUSE_PLATING_STAIRS = registerBlock("rusty_warehouse_plating_stairs",
                    () -> new StairBlock(() -> RUSTY_WAREHOUSE_PLATING.get().defaultBlockState(),
                            BlockBehaviour.Properties.copy(RUSTY_WAREHOUSE_PLATING.get())));

    public static final RegistryObject<SlabBlock> RUSTY_WAREHOUSE_PLATING_SLAB = registerBlock("rusty_warehouse_plating_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(RUSTY_WAREHOUSE_PLATING.get())));

    public static final RegistryObject<StairBlock> RUSTY_WAREHOUSE_TREADING_STAIRS = registerBlock("rusty_warehouse_treading_stairs",
                    () -> new StairBlock(() -> RUSTY_WAREHOUSE_TREADING.get().defaultBlockState(),
                            BlockBehaviour.Properties.copy(RUSTY_WAREHOUSE_TREADING.get())));

    public static final RegistryObject<SlabBlock> RUSTY_WAREHOUSE_TREADING_SLAB = registerBlock("rusty_warehouse_treading_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(RUSTY_WAREHOUSE_TREADING.get())));

    public static final RegistryObject<StairBlock> RUSTY_WAREHOUSE_WALLING_STAIRS = registerBlock("rusty_warehouse_walling_stairs",
                    () -> new StairBlock(() -> RUSTY_WAREHOUSE_WALLING.get().defaultBlockState(),
                            BlockBehaviour.Properties.copy(RUSTY_WAREHOUSE_WALLING.get())));

    public static final RegistryObject<SlabBlock> RUSTY_WAREHOUSE_WALLING_SLAB = registerBlock("rusty_warehouse_walling_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.copy(RUSTY_WAREHOUSE_WALLING.get())));


    public static final RegistryObject<StairBlock> WAREHOUSE_PLATING_STAIRS = registerBlock("warehouse_plating_stairs",
            () -> new RustingStairBlock(() -> RUSTY_WAREHOUSE_PLATING_STAIRS.get(),
                    BlockBehaviour.Properties.copy(WAREHOUSE_PLATING.get()), () -> WAREHOUSE_PLATING.get().defaultBlockState()));

    public static final RegistryObject<SlabBlock> WAREHOUSE_PLATING_SLAB = registerBlock("warehouse_plating_slab",
            () -> new RustingSlabBlock(() -> RUSTY_WAREHOUSE_PLATING_SLAB.get(),
                    BlockBehaviour.Properties.copy(WAREHOUSE_PLATING.get())));

    public static final RegistryObject<StairBlock> WAREHOUSE_TREADING_STAIRS = registerBlock("warehouse_treading_stairs",
            () -> new RustingStairBlock(() -> RUSTY_WAREHOUSE_TREADING_STAIRS.get(),
                    BlockBehaviour.Properties.copy(WAREHOUSE_TREADING.get()), () -> WAREHOUSE_TREADING.get().defaultBlockState()));

    public static final RegistryObject<SlabBlock> WAREHOUSE_TREADING_SLAB = registerBlock("warehouse_treading_slab",
            () -> new RustingSlabBlock(() -> RUSTY_WAREHOUSE_TREADING_SLAB.get(),
                    BlockBehaviour.Properties.copy(WAREHOUSE_TREADING.get())));

    public static final RegistryObject<StairBlock> WAREHOUSE_WALLING_STAIRS = registerBlock("warehouse_walling_stairs",
            () -> new RustingStairBlock(() -> RUSTY_WAREHOUSE_WALLING_STAIRS.get(),
                    BlockBehaviour.Properties.copy(WAREHOUSE_WALLING.get()), () -> WAREHOUSE_WALLING.get().defaultBlockState()));

    public static final RegistryObject<SlabBlock> WAREHOUSE_WALLING_SLAB = registerBlock("warehouse_walling_slab",
            () -> new RustingSlabBlock(() -> RUSTY_WAREHOUSE_WALLING_SLAB.get(),
                    BlockBehaviour.Properties.copy(WAREHOUSE_WALLING.get())));


    public static final RegistryObject<Block> JUKEBOX = registerBlock("jukebox",
            () -> new Jukebox(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD).noOcclusion()));


    public static final RegistryObject<Block> BAUXITE_ORE = registerBlock("bauxite_ore",
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