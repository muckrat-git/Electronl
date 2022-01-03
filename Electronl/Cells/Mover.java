package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;
import Electronl.Cells.Module;

public class Mover extends Module {
    public Mover() {
        name = "Mover";
        state = 0;
    }
    @Override
    public void Update(Module[] Modules) {
        super.Update(Modules);
        switch(state) {
            case 0:
                //Inactive
                for(Module item : Modules) {
                    if(item.name.equals("ElectronL")) {
                        if(item.getState() == 1) {
                            if (Math.sqrt(((x - item.x) * (x - item.x)) + ((y - item.y) * (y - item.y))) <= 1.5) {
                                movex = 0 - (x - item.x);
                                movey = 0 - (y - item.y);
                            }
                        }
                    }
                }
                if (replaceState("ElectronL", 1, 2,Modules)) {
                    state = 1;
                }
                break;
            case 1:
                //Active
                move();
                state = 0;
                break;
        }
    }
    @Override
    public Image getSprite() {
        switch(state) {
            case 0:
                return (Frame.toolkit.getImage(Frame.imagesdir + "mover.png"));
            case 1:
                return (Frame.toolkit.getImage(Frame.imagesdir + "mover_active.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "mover.png"));
        }
    }
}