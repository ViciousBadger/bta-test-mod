package turniplabs.examplemod.mixin;

import net.minecraft.client.option.enums.RenderDistance;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderDistance.class, remap = false)
public abstract class TestMixin {
	@Accessor
	public abstract int getChunks();

	@Accessor
	public abstract void setChunks(int chunks);

	@Inject(at = @At("TAIL"), method = "<init>")
	private void test(CallbackInfo ci) {
		this.setChunks(this.getChunks() * 4);
		System.out.println("CHONKS" + this.getChunks());
	}
}
