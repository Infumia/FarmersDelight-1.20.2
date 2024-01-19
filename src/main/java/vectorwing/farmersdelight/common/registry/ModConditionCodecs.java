package vectorwing.farmersdelight.common.registry;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.condition.VanillaCrateEnabledCondition;

import java.util.function.Supplier;

public class ModConditionCodecs {
  public static final DeferredRegister<Codec<? extends ICondition>> CONDITION_CODECS = DeferredRegister.create(ForgeRegistries.CONDITION_SERIALIZERS, FarmersDelight.MODID);

  public static final Supplier<Codec<? extends ICondition>> VANILLA_CRATE_ENABLED = CONDITION_CODECS.register("vanilla_crates_enabled", () -> VanillaCrateEnabledCondition.CODEC);
}
