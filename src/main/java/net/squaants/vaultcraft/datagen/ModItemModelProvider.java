package net.squaants.vaultcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.block.ModBlocks;
import net.squaants.vaultcraft.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VaultCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        // Planks
        simpleItem(ModItems.ACACIA_PLANK);
        simpleItem(ModItems.BIRCH_PLANK);
        simpleItem(ModItems.CHERRY_PLANK);
        simpleItem(ModItems.DARK_OAK_PLANK);
        simpleItem(ModItems.JUNGLE_PLANK);
        simpleItem(ModItems.MANGROVE_PLANK);
        simpleItem(ModItems.OAK_PLANK);
        simpleItem(ModItems.SPRUCE_PLANK);

        // Ingots
        simpleItem(ModItems.ALUMINUM_INGOT);
        simpleItem(ModItems.BLACK_TITANIUM_INGOT);
        simpleItem(ModItems.LEAD_INGOT);
        simpleItem(ModItems.SILVER_INGOT);
        simpleItem(ModItems.STEEL_INGOT);

        // Raw ores
        simpleItem(ModItems.RAW_BAUXITE);
        simpleItem(ModItems.RAW_BLACK_TITANIUM);
        simpleItem(ModItems.RAW_LEAD);
        simpleItem(ModItems.RAW_SILVER);
        simpleItem(ModItems.ULTRACITE);
        simpleItem(ModItems.URANIUM);

        // Other items
        simpleItem(ModItems.DEATHCLAW_OMELETTE);
        simpleItem(ModItems.FUEL);
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(VaultCraft.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<? extends Item> item) {
        String name = item.getId().getPath();
        return withExistingParent(name, new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(VaultCraft.MOD_ID, "item/" + name));
    }
}