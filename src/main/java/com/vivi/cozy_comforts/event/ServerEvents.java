package com.vivi.cozy_comforts.event;

import com.vivi.cozy_comforts.CozyComforts;
import com.vivi.cozy_comforts.mixin.BiomeAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = CozyComforts.MOD_ID)
public class ServerEvents {



    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.phase != TickEvent.Phase.END) return;
        if(event.side.isClient()) return;

        BlockPos pos = event.player.blockPosition();
        Holder<Biome> biome = event.player.level().getBiome(pos);
        ResourceLocation id = biomeID(biome);
        BiomeAccessor accessor = (BiomeAccessor) (Object) (biome.get());
//        CozyComforts.LOGGER.info("Biome: " + id + ", temperature: " + accessor.cozy_comforts$getTemperature(pos));
    }

    private static ResourceLocation biomeID(Holder<Biome> pBiomeHolder) {
        return pBiomeHolder.unwrap().map(ResourceKey::location, (biome) -> null);
    }
}
