package vectorwing.farmersdelight.common.registry;

import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import vectorwing.farmersdelight.FarmersDelight;
import vectorwing.farmersdelight.common.crafting.ingredient.ToolActionIngredient;

import java.util.function.Supplier;

public class ModIngredientTypes {
    public static final DeferredRegister<IIngredientSerializer<?>> INGREDIENT_TYPES = DeferredRegister.create(ForgeRegistries.INGREDIENT_SERIALIZERS, FarmersDelight.MODID);

    public static final Supplier<IIngredientSerializer<?>> TOOL_ACTION_INGREDIENT = INGREDIENT_TYPES.register("tool_action", () -> ToolActionIngredient.SERIALIZER);
}