package sunsetsatellite.vintagequesting.gui.slot.task;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.TooltipElement;
import net.minecraft.client.render.Lighting;
import net.minecraft.client.render.TextureManager;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import sunsetsatellite.vintagequesting.gui.ItemRenderHelper;
import sunsetsatellite.vintagequesting.interfaces.IRenderable;
import sunsetsatellite.vintagequesting.quest.task.RetrievalTask;

public class GuiRetrievalTaskSlot extends Gui implements IRenderable {

	public int width;
	public int height;
	private final Minecraft mc;
	private final RetrievalTask task;
	private final TooltipElement tooltip;

	public GuiRetrievalTaskSlot(Minecraft mc, int width, int height, RetrievalTask task){
		this.width = width;
        this.height = height;
		this.mc = mc;
		this.task = task;
		this.tooltip = new TooltipElement(mc);
	}

	@Override
	public void render(int x, int y, int mouseX, int mouseY) {
		if(task.isCompleted()){
			drawRectWidthHeight(x,y,width,height,0xFF008000);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
		}
		drawSlot(mc.textureManager, x+3,y+3,0xFFFFFFFF);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		ItemStack item = task.getStack();
		ItemRenderHelper.renderItemStack(item, x + 4, y + 4, 1, 1, 1,1);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_CULL_FACE);
		Lighting.disable();

		drawString(mc.font, task.getProgress()+" / "+item.stackSize+"x "+ item.getDisplayName(),x+28, y+8, 0xFFFFFFFF);

		if(mouseX > x+3 && mouseX < x+21 && mouseY > y+3 && mouseY < y+21){

			boolean ctrl = Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL);
			String tooltipText = tooltip.getTooltipText(item, ctrl);
			tooltip.render(tooltipText,mouseX,mouseY,8,-8);
		}
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	public void drawSlot(TextureManager re, int x, int y, int argb) {
		float a = (float)(argb >> 24 & 255) / 255.0F;
		float r = (float)(argb >> 16 & 255) / 255.0F;
		float g = (float)(argb >> 8 & 255) / 255.0F;
		float b = (float)(argb & 255) / 255.0F;
		re.bindTexture(re.loadTexture("/assets/vq/textures/gui/slot.png"));
		GL11.glColor4f(r, g, b, a);
		this.drawTexturedModalRect(x, y, 0, 0, 18, 18, 18, 0.0078125F);
	}
}
