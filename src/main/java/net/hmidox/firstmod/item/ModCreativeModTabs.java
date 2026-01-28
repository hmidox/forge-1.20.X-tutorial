package net.hmidox.firstmod.item;

import net.hmidox.firstmod.FirstMod;
import net.hmidox.firstmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FirstMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> LHBAL_TAB = CREATIVE_MODE_TABS.register("lhbal",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(Moditems.BAALLA.get()))
                    .title(Component.translatable("creativetab.lhbal_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(Moditems.BAALLA.get());
                        pOutput.accept(Moditems.ABDELHAMID.get());

                        pOutput.accept(ModBlocks.BAALLA_BLOCK.get());
                        pOutput.accept(ModBlocks.ABDELHAMID_BLOCK.get());
                        pOutput.accept(ModBlocks.ABDELHAMID_ORE.get());
                        pOutput.accept(ModBlocks.BAALLA_ORE.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
