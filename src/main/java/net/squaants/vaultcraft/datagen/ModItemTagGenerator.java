package net.squaants.vaultcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.squaants.vaultcraft.VaultCraft;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput output,
                               CompletableFuture<HolderLookup.Provider> lookupProvider,
                               CompletableFuture<TagsProvider.TagLookup<Block>> blockTags,
                               @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, VaultCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // This is where we can move your wooden_planks.json tag later
        // Example:
        // tag(ModTags.Items.WOODEN_PLANKS).add(
        //     ModItems.ACACIA_PLANK.get(), ... );
    }

    @Override
    public String getName() {
        return "VaultCraft Item Tags";
    }
}