package sunsetsatellite.vintagequesting.gui.generic;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import sunsetsatellite.vintagequesting.interfaces.IRenderable;

public class StringElement extends Gui implements IRenderable {

	private final Minecraft mc;
	public String string;
	public int argb;

	public StringElement(Minecraft mc, String string, int argb) {
		this.string = string;
		this.mc = mc;
		this.argb = argb;
	}

	@Override
	public void render(int x, int y, int mouseX, int mouseY) {
		drawString(mc.font,string,x,y,argb);
	}

	@Override
	public int getHeight() {
		return mc.font.fontHeight;
	}

	@Override
	public int getWidth() {
		return mc.font.getStringWidth(string);
	}
}
