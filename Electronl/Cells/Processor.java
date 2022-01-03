package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class Processor extends Module {
    public Processor() {
        name = "Processor";
        state = 0;
    }
    int charge = 0;
    @Override
    public void Update(Module[] Modules) {
        super.Update(Modules);
        switch(state) {
            case 0:
                main.alive = false; 
                if(replaceState("ElectronL", 1, 2,Modules)) {
                    replaceState("ElectronL", 1, 2,CellManager.Cells);
                    state = 1;
                    charge = 6;
                    emitslight = true;
                }
                emitslight = false;
                break;
            case 1:
                main.alive = true;
                emitslight = true;
                charge--;
                if(replaceState("ElectronL", 1, 2,Modules)) {
                    replaceState("ElectronL", 1, 2,CellManager.Cells);
                    charge = 6;
                    emitslight = true;
                }
                if(charge <= 0) {
                    state = 0;
                    charge = 0;
                    emitslight = false;
                }
                break;
        }
    }
    @Override
    public Image getSprite() {
        switch(state) {
            case 1:
                return (Frame.toolkit.getImage(Frame.imagesdir + "processor.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "processor_dead.png"));
        }
    }
}
