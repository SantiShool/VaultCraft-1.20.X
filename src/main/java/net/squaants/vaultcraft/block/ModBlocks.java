package net.squaants.vaultcraft.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.squaants.vaultcraft.block.custom.RustingBlock;
import net.squaants.vaultcraft.block.custom.Jukebox;

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

    public static final RegistryObject<Block> RUSTY_WAREHOUSE_PLATING = registerBlock("rusty_warehouse_plating",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(3.0F, 2.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> RUSTY_WAREHOUSE_TREADING = registerBlock("rusty_warehouse_treading",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(3.0F, 2.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> RUSTY_WAREHOUSE_WALLING = registerBlock("rusty_warehouse_walling",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(3.0F, 2.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> WAREHOUSE_PLATING = registerBlock("warehouse_plating",
            () -> new RustingBlock(() -> ModBlocks.RUSTY_WAREHOUSE_PLATING.get(), BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(-1.0F, 4.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> WAREHOUSE_TREADING = registerBlock("warehouse_treading",
            () -> new RustingBlock(() -> ModBlocks.RUSTY_WAREHOUSE_TREADING.get(), BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(-1.0F, 4.0F).sound(SoundType.METAL)));

    public static final RegistryObject<Block> WAREHOUSE_WALLING = registerBlock("warehouse_walling",
            () -> new RustingBlock(() -> ModBlocks.RUSTY_WAREHOUSE_WALLING.get(), BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(-1.0F, 4.0F).sound(SoundType.METAL)));


    public static final RegistryObject<Block> JUKEBOX = registerBlock("jukebox",
            () -> new Jukebox(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD)
                    .strength(2.0F, 3.0F).sound(SoundType.WOOD)));



    public static final RegistryObject<Block> BAUXITE_ORE = registerBlock("bauxite_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final RegistryObject<Block> SILVER_ORE = registerBlock("silver_ore",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                    .requiresCorrectToolForDrops().strength(3.0F, 3.0F)));

    public static final RegistryObject<Block> ULTRACITE_ORE = registerBlock("ultracite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));

    public static final RegistryObject<Block> URANIUM_ORE = registerBlock("uranium_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).instrument(NoteBlockInstrument.BASEDRUM)
                    .strength(3.0F, 3.0F).requiresCorrectToolForDrops(), UniformInt.of(3, 7)));



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