package net.squaants.vaultcraft.blockentity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.squaants.vaultcraft.VaultCraft;
import net.squaants.vaultcraft.block.ModBlocks;
import net.squaants.vaultcraft.blockentity.custom.JukeboxEntity;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, VaultCraft.MOD_ID);

    public static final RegistryObject<BlockEntityType<JukeboxEntity>> JUKEBOX =
            BLOCK_ENTITIES.register("jukebox",
                    () -> BlockEntityType.Builder.of(
                            JukeboxEntity::new,
                            ModBlocks.JUKEBOX.get()
                    ).build(null));
}
