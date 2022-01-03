package Electronl.UI;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.*;

import java.util.Arrays;

import Electronl.CellManager;
import Electronl.Frame;

//ElectronL by Scratchdragon
class PriorityButton extends GUIButton {
    boolean enabled = false;
    public int layer = 0;
    public Image image = Frame.toolkit.getImage(Frame.imagesdir + "/UI/priority_icon.png");
    public PriorityButton() {
        x = 10;
        y = 40;
        width = 16;
        height = 16;
    }
    @Override
    public Image getImage() {
        if(enabled) {
            image = Frame.toolkit.getImage(Frame.imagesdir + "/UI/priority_icon_enabled.png");
        }
        else {
            image = Frame.toolkit.getImage(Frame.imagesdir + "/UI/priority_icon.png");
        }
        return image;
    }

    @Override
    public void onPressed() {
        enabled = !enabled;
    }
}

class ModuleIndicator extends GUIElement {
    public int layer = 0;
    public Image image = Frame.toolkit.getImage(Frame.imagesdir + "cursor.png");
    public ModuleIndicator() {
        x = 10;
        y = 0;
        width = 32;
        height = 32;
    }
    @Override
    public Image getImage() {
        CellManager cellManager = new CellManager();
        return cellManager.modules[cellManager.mod_i].getSprite();
    }
}
class ModuleLabel extends GUIElement {
    public int layer = 0;
    public Image image = Frame.toolkit.getImage(Frame.imagesdir + "cursor.png");
    public ModuleLabel() {
        x = 50;
        y = 0;
        width = 100;
        height = 18;
    }
    @Override
    public Image getImage() {
        CellManager cellManager = new CellManager();
        return Frame.toolkit.getImage(Frame.imagesdir + "labels/" + cellManager.modules[cellManager.mod_i].name + ".png");
    }
}

class Toolbar extends GUIElement {
    public int layer = 0;
    public Image image = Frame.toolkit.getImage(Frame.imagesdir + "toolbar.png");
    public Toolbar() {
        x = 10;
        y = 20;
        width = (int)(42 * 2.8);
        height = (int)(126 * 2.8);
    }
    @Override
    public Image getImage() {return image;}
}

public class GUIManager {
    static BufferedImage UI;
    static GUIElement[] Elements = new GUIElement[0];

    public static void addUI(GUIElement element) {
        Elements = Arrays.copyOf(Elements, Elements.length + 1);
        Elements[Elements.length - 1] = element;
    }
    public static void init(Frame frame) {
        UI = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
        //Add Elements:
        addUI(new ModuleIndicator());
        addUI(new ModuleLabel());
        //addUI(new PriorityButton());
        //Initial update to avoid null elements
        Update(frame);
    }
    public static boolean updating = false;
    public static void Update(Frame frame) {
        BufferedImage buffer = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
        updating = true;
        Graphics2D g2d = buffer.createGraphics();
        for(int i = -1; i < 3; i++) {
            for (GUIElement item : Elements) {
                if (item.layer == i) {
                    item.updateMethods();
                    item.Update();
                    g2d.drawImage(item.getImage(), (int)(item.x * (frame.getHeight() / 400)), (int)(item.y * (frame.getHeight() / 400)) + 36, (int)(item.width * (frame.getHeight() / 400)), (int)(item.height * (frame.getHeight() / 400)), frame);
                }
            }
        }
        UI = buffer;
        updating = false;
    }
    public static void render(Graphics2D g,Frame frame) {
        g.drawImage(UI, 0, 0, frame);
    }
}
