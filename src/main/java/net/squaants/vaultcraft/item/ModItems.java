package net.squaants.vaultcraft.item;

import net.squaants.vaultcraft.VaultCraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.squaants.vaultcraft.item.custom.CampItem;
import net.squaants.vaultcraft.item.custom.FuelItem;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VaultCraft.MOD_ID);

    public static final RegistryObject<Item> ACACIA_LOG = ITEMS.register("acacia_log",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_LOG = ITEMS.register("birch_log",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHERRY_LOG = ITEMS.register("cherry_log",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_LOG = ITEMS.register("dark_oak_log",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_LOG = ITEMS.register("jungle_log",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_LOG = ITEMS.register("mangrove_log",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OAK_LOG = ITEMS.register("oak_log",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_LOG = ITEMS.register("spruce_log",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ACACIA_PLANK = ITEMS.register("acacia_plank",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BIRCH_PLANK = ITEMS.register("birch_plank",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CHERRY_PLANK = ITEMS.register("cherry_plank",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DARK_OAK_PLANK = ITEMS.register("dark_oak_plank",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> JUNGLE_PLANK = ITEMS.register("jungle_plank",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MANGROVE_PLANK = ITEMS.register("mangrove_plank",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> OAK_PLANK = ITEMS.register("oak_plank",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SPRUCE_PLANK = ITEMS.register("spruce_plank",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> SILVER_INGOT = ITEMS.register("silver_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_BAUXITE = ITEMS.register("raw_bauxite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_SILVER = ITEMS.register("raw_silver",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_ULTRACITE = ITEMS.register("raw_ultracite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_URANIUM = ITEMS.register("raw_uranium",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DEATHCLAW_OMELETTE = ITEMS.register("deathclaw_omelette",
            () -> new Item(new Item.Properties().food(ModFoodProperties.DEATHCLAW_OMELETTE)));

    public static final RegistryObject<CampItem> CAMP = ITEMS.register(
            "camp",
            () -> new CampItem(new Item.Properties()));
    public static final RegistryObject<Item> FUEL = ITEMS.register("fuel",
            () -> new FuelItem(new Item.Properties(),6000));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}