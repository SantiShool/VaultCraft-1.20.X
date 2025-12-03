package net.squaants.vaultcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
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
                ModBlocks.METAL_BRICK.get(),
                ModBlocks.METAL_ENGRAVED.get(),
                ModBlocks.METAL_PANELING.get(),
                ModBlocks.RUSTY_WAREHOUSE_PLATING.get(),
                ModBlocks.RUSTY_WAREHOUSE_TREADING.get(),
                ModBlocks.RUSTY_WAREHOUSE_WALLING.get(),
                ModBlocks.WAREHOUSE_PLATING.get(),
                ModBlocks.WAREHOUSE_TREADING.get(),
                ModBlocks.WAREHOUSE_WALLING.get(),
                ModBlocks.JUKEBOX.get(),
                ModBlocks.BAUXITE_ORE.get(),
                ModBlocks.SILVER_ORE.get(),
                ModBlocks.ULTRACITE_ORE.get(),
                ModBlocks.URANIUM_ORE.get(),
                ModBlocks.DEEPSLATE_BAUXITE_ORE.get(),
                ModBlocks.DEEPSLATE_SILVER_ORE.get(),
                ModBlocks.DEEPSLATE_ULTRACITE_ORE.get(),
                ModBlocks.DEEPSLATE_URANIUM_ORE.get()
        );

        // If you want, we can also move your camp_valuables.json to datagen here later.
    }

    @Override
    public String getName() {
        return "VaultCraft Block Tags";
    }
}