package vectorwing.farmersdelight.common.crafting.ingredient;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.crafting.ingredients.IIngredientSerializer;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ToolActionIngredient extends Ingredient
{
	public static final Serializer SERIALIZER = new Serializer();
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

	@Override
	public IIngredientSerializer<? extends Ingredient> serializer() {
		return SERIALIZER;
	}

	public static final class Serializer implements IIngredientSerializer<ToolActionIngredient> {
		public static final Codec<ToolActionIngredient> CODEC = RecordCodecBuilder.create(inst ->
			inst.group(ToolAction.CODEC.fieldOf("action").forGetter(ToolActionIngredient::getToolAction)
			).apply(inst, ToolActionIngredient::new));

		@Override
		public Codec<? extends ToolActionIngredient> codec() {
			return CODEC;
		}

		@Override
		public void write(FriendlyByteBuf buffer, ToolActionIngredient value) {
			buffer.writeUtf(value.toolAction.name());
		}

		@Override
		public ToolActionIngredient read(FriendlyByteBuf buffer) {
			final String toolActionName = buffer.readUtf();
			return new ToolActionIngredient(ToolAction.get(toolActionName));
		}
	}
}
