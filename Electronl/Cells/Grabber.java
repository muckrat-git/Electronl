package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class Grabber extends Module {
    public Grabber() {
        name = "Grabber";
        state = 0;
    }
    int charge = 0;
    @Override
    public void Update(Module[] Modules) {
        switch(state) {
            case 0:
                if (replaceState("ElectronL", 1, 2,Modules)) {
                    state = 1;
                    charge = 8;
                }
                else if (replaceState("Grabber", 2, 0,Modules)) {
                    state = 1;
                    charge = 8;
                }
                break;
            case 1:
                if (replaceState("ElectronL", 1, 2,Modules)) {
                    bufferstate = 2;
                    bufferenabled = true;
                }
                if(charge <= 0) {
                    state = 0;
                    charge = 0;
                }
                else{
                    charge--;
                }
                break;
            case 2:
                charge++;
                bufferstate = 1;
                bufferenabled = true;
                break;
        }
        super.Update(Modules);
    }
    @Override
    void move() {
        if(state == 0) {
            super.move();
            return;
        }
        for(Module item : CellManager.Cells) {
            if( Math.sqrt( ((x - item.x) * (x - item.x)) + ((y - item.y) * (y - item.y)) ) <= 1.5) {
                if(item.index != index && !item.name.equals("Mover") && item.movex == 0 && item.movey == 0) {
                    item.movex = movex;
                    item.movey = movey;
                    item.move();
                }
                else if(item.index != index && item.name.equals("Mover") && item.movex == 0 && item.movey == 0 && (item.x == x + movex && item.y == y + movey)) {
                    item.movex = movex;
                    item.movey = movey;
                    item.move();
                }
            }
        }
        x += movex;
        y += movey;
        movex = 0;
        movey = 0;
    }
    @Override
    public Image getSprite() {
        switch(state) {
            case 0:
                return (Frame.toolkit.getImage(Frame.imagesdir + "grabber.png"));
            case 1:
                return (Frame.toolkit.getImage(Frame.imagesdir + "grabber_active.png"));
            case 2:
                return (Frame.toolkit.getImage(Frame.imagesdir + "grabber_powered.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "grabber_powered.png"));
        }
    }
}