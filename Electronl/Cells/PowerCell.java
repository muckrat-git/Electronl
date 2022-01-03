package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class PowerCell extends Module {
    int charge = 0;
    public PowerCell(int s) {
        state = s;
        if(s == 1) {
            charge = 32;
        }
        name = "Power Cell";
    }
    @Override
    public void Update(Module[] Modules) {
        super.Update(Modules);
        if(state == 1) {
            if(replaceState("ElectronL", 0, 1,Modules)) {
                replaceState("ElectronL", 0, 1,CellManager.Cells);
                charge--;
                if(charge <= 0) {
                    charge = 0;
                    state = 0;
                    return;
                }
            }
        }
        else if(state == 0) {
            if(replaceState("ElectronL", 1, 2,Modules)) {
                replaceState("ElectronL", 1, 2,CellManager.Cells);
                charge++;
                if(charge >= 32) {
                    charge = 32;
                    state = 1;
                    return;
                }
            }
        }
    }
    @Override
    public Image getSprite() {
        switch(state) {
            case 0:
                return (Frame.toolkit.getImage(Frame.imagesdir + "power_cell.png"));
            case 1:
                return (Frame.toolkit.getImage(Frame.imagesdir + "power_cell_charged.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "power_cell.png"));
        }
    }
}
