package vectorwing.farmersdelight.common.crafting.ingredient;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ToolActionIngredient extends Ingredient
{
	public static final Codec<ToolActionIngredient> CODEC = RecordCodecBuilder.create(inst ->
		inst.group(ToolAction.CODEC.fieldOf("action").forGetter(ToolActionIngredient::getToolAction)
		).apply(inst, ToolActionIngredient::new));

	public final ToolAction toolAction;

	/**
	 * Ingredient that checks if the given stack can perform a ToolAction from Forge.
	 */
	public ToolActionIngredient(ToolAction toolAction) {
		super(ForgeRegistries.ITEMS.getValues().stream()
				.map(ItemStack::new)
				.filter(stack -> stack.canPerformAction(toolAction))
				.map(Ingredient.ItemValue::new));
		this.toolAction = toolAction;
	}

	@Override
	public boolean test(@Nullable ItemStack stack) {
		return stack != null && stack.canPerformAction(toolAction);
	}

	public ToolAction getToolAction() {
		return toolAction;
	}
}
