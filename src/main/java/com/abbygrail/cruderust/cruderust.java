package com.abbygrail.cruderust;

import com.abbygrail.cruderust.block.FireWoodBlock;
import com.abbygrail.cruderust.core.registry.CrudeRustItems;
import net.minecraft.world.level.block.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.teamabnormals.blueprint.core.registry.BlueprintBlockEntityTypes.HELPER;


// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(cruderust.MODID)
public class cruderust
{

    public static final String MODID = "cruderust";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredBlock<RotatedPillarBlock> BRONZE_BLOCK =
            BLOCKS.register("bronze_block", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.GOLD)));
    public static final DeferredItem<BlockItem> BRONZE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("bronze_block", BRONZE_BLOCK);

    public static final DeferredBlock<Block> ARSENIC_ORE = BLOCKS.registerSimpleBlock("arsenic_ore");
    public static final DeferredItem<BlockItem> ARSENIC_ORE_ITEM = ITEMS.registerSimpleBlockItem("arsenic_ore", ARSENIC_ORE);

    public static final DeferredBlock<Block> FIREWOOD = BLOCKS.register("firewood",
            () -> new FireWoodBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .noOcclusion()
                    .isSuffocating((state, level, pos) -> false)
                    .instabreak()
                    .sound(SoundType.CHERRY_WOOD)));
    public static final DeferredItem<BlockItem> FIREWOOD_ITEM = ITEMS.registerSimpleBlockItem("firewood", FIREWOOD);



//    public static final RegistryObject<Block> COAL = HELPER.createPlacedItem("coal", () -> new CoalBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).strength(5.0F, 6.0F).requiresCorrectToolForDrops().lightLevel(state -> !state.getValue(CoalBlock.LIT) ? 0 : 9 + state.getValue(CoalBlock.COAL)).noOcclusion().pushReaction(PushReaction.DESTROY)));

//    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CRUDERUST = CREATIVE_MODE_TABS.register("cruderust", () -> CreativeModeTab.builder()
//            .title(Component.translatable("itemGroup.cruderust"))
//            .withTabsBefore(CreativeModeTabs.COMBAT)
//            .icon(() -> BRONZE_INGOT.get().getDefaultInstance())
//            .displayItems((parameters, output) -> {
//                output.accept(BRONZE_BLOCK_ITEM.get());
//                output.accept(BRONZE_INGOT.get());
//                output.accept(BRONZE_NUGGET.get());
//                output.accept(FIREWOOD_ITEM.get());
//            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public cruderust(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        CrudeRustItems.register(modEventBus);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (cruderust) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(BRONZE_BLOCK_ITEM);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}

