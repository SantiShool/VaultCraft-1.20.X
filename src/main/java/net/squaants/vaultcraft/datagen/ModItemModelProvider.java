package net.squaants.vaultcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VaultCraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Logs
        simpleItem(ModItems.ACACIA_LOG);
        simpleItem(ModItems.BIRCH_LOG);
        simpleItem(ModItems.CHERRY_LOG);
        simpleItem(ModItems.DARK_OAK_LOG);
        simpleItem(ModItems.JUNGLE_LOG);
        simpleItem(ModItems.MANGROVE_LOG);
        simpleItem(ModItems.OAK_LOG);
        simpleItem(ModItems.SPRUCE_LOG);

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
        simpleItem(ModItems.SILVER_INGOT);
        simpleItem(ModItems.STEEL_INGOT);

        // Raw ores
        simpleItem(ModItems.RAW_BAUXITE);
        simpleItem(ModItems.RAW_SILVER);
        simpleItem(ModItems.RAW_ULTRACITE);
        simpleItem(ModItems.RAW_URANIUM);

        // Other items
        simpleItem(ModItems.DEATHCLAW_OMELETTE);
        simpleItem(ModItems.CAMP);
        simpleItem(ModItems.FUEL);
    }

    private ItemModelBuilder simpleItem(RegistryObject<? extends Item> item) {
        String name = item.getId().getPath();
        return withExistingParent(name, new ResourceLocation("item/generated"))
                .texture("layer0", new ResourceLocation(VaultCraft.MOD_ID, "item/" + name));
    }
}