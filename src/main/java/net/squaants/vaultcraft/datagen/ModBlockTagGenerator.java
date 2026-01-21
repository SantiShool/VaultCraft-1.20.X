package net.squaants.vaultcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output,
                                CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, VaultCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Everything here is mineable with a pickaxe
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.JUKEBOX.get(),

                ModBlocks.METAL_BLOCK.get(),
                ModBlocks.RUSTY_WAREHOUSE_BLOCK.get(),
                ModBlocks.WAREHOUSE_BLOCK.get(),

                ModBlocks.METAL_STAIRS.get(),
                ModBlocks.METAL_SLAB.get(),
                ModBlocks.RUSTY_WAREHOUSE_STAIRS.get(),
                ModBlocks.RUSTY_WAREHOUSE_SLAB.get(),
                ModBlocks.WAREHOUSE_STAIRS.get(),
                ModBlocks.WAREHOUSE_SLAB.get(),

                ModBlocks.BAUXITE_ORE.get(),
                ModBlocks.BLACK_TITANIUM_ORE.get(),
                ModBlocks.LEAD_ORE.get(),
                ModBlocks.SILVER_ORE.get(),
                ModBlocks.ULTRACITE_ORE.get(),
                ModBlocks.URANIUM_ORE.get(),
                ModBlocks.DEEPSLATE_BAUXITE_ORE.get(),
                ModBlocks.DEEPSLATE_BLACK_TITANIUM_ORE.get(),
                ModBlocks.DEEPSLATE_LEAD_ORE.get(),
                ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                ModBlocks.DEEPSLATE_ULTRACITE_ORE.get(),
                ModBlocks.DEEPSLATE_URANIUM_ORE.get()
        );

        // If you want, we can also move your camp_blocks.json to datagen here later.
    }

    @Override
    public String getName() {
        return "VaultCraft Block Tags";
    }
}