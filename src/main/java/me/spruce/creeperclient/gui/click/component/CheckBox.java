package me.spruce.creeperclient.gui.click.component;

import me.spruce.creeperclient.gui.click.Button;
import me.spruce.creeperclient.setting.n.Setting;
import me.spruce.creeperclient.util.font.FontUtil;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class CheckBox {

    private int x;
    private int y;
    private Setting<Boolean> option;
    private Button b;

    public CheckBox(Button button, Setting s, int x, int y) {
        this.b = button;
        this.x = x;
        this.y = y;
        this.option = s;
    }

    public void update(int mouseX, int mouseY, int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks, int offset) {
        Gui.drawRect(x, y, x + 96, y + 12, new Color(217, 200, 250, 255).getRGB());
        Gui.drawRect(x + 85, y + 2, x + 93, y + 10, !option.getValue() ? new Color(50, 50, 50, 255).getRGB() : new Color(152, 100, 255, 255).getRGB());
        FontUtil.normal.drawString(option.getName(), x + 1, y + 2.75f, 0);
    }

    public void keyTyped(char typedChar, int keyCode) {
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        if (bounding(mouseX, mouseY)) {
            if (mouseButton == 0) {
                option.setValue(!option.getValue());
            }
        }
    }

    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public boolean bounding(int mouseX, int mouseY) {
        return mouseX >= x && mouseY >= y && mouseX <= x + 96 && mouseY <= y + 15;
    }
}
