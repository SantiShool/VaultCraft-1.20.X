package net.squaants.vaultcraft.item;

import net.squaants.vaultcraft.VaultCraft;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.squaants.vaultcraft.item.custom.CampItem;
import net.squaants.vaultcraft.item.custom.FuelItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, VaultCraft.MOD_ID);

    public static final RegistryObject<Item> OAK_LOG = ITEMS.register("oak_log",
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

    public static final RegistryObject<Item> CAMP = ITEMS.register("camp",
            () -> new CampItem(new Item.Properties().stacksTo(1).durability(100)));
    public static final RegistryObject<Item> FUEL = ITEMS.register("fuel",
            () -> new FuelItem(new Item.Properties(),6000));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}