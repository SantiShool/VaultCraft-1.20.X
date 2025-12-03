package net.squaants.vaultcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.squaants.vaultcraft.VaultCraft;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = VaultCraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        // ===== SERVER-SIDE DATA =====
        generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));

        // Block tags + item tags (same pattern as course – block tags feed into item tags)
        ModBlockTagGenerator blockTags = new ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);

        generator.addProvider(event.includeServer(),
                new ModItemTagGenerator(packOutput, lookupProvider, blockTags.contentsGetter(), existingFileHelper));

        // ===== CLIENT-SIDE DATA =====
        generator.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
    }
}