package Electronl.Cells;

import Electronl.CellManager;
import Electronl.Frame;

import java.awt.*;

public class Magnet extends Module {
    public Magnet() {
        name = "Magnet";
    }
    @Override
    public void Update(Module[] Modules) {
        super.Update(Modules);
    }
    @Override
    void move() {
        for(Module item : CellManager.Cells) {
            if( Math.sqrt( ((x - item.x) * (x - item.x)) + ((y - item.y) * (y - item.y)) ) <= 1.5) {
                if(item.index != index && item.name.equals("Magnet") && item.movex == 0 && item.movey == 0) {
                    item.movex = movex;
                    item.movey = movey;
                    item.move();
                }
            }
        }
        super.move();
    }
    @Override
    public Image getSprite() {
        return (Frame.toolkit.getImage(Frame.imagesdir + "magnet.png"));
    }
}