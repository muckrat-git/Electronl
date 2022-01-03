package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class IonPowerUnit extends Module {
    int charge = 0;
    public IonPowerUnit(int s) {
        state = s;
        if(s == 1) {
            charge = 128;
        }
        name = "Ion Power Unit";
    }

    int cycles = 0;

    @Override
    public void Update(Module[] Modules) {
        super.Update(Modules);
        if(cycles < 5 && state == 1) {
            cycles++;
            return;
        }
        cycles = 0;
        if(state == 1) {
            if (replaceStateBuffered("ElectronL", 0, 1,CellManager.Cells)) {
                replaceState("ElectronL", 0, 1,Modules);
                charge--;
                if(charge <= 0) {
                    charge = 0;
                    state = 0;
                    return;
                }
            }
        }
        else if(state == 0) {
            if (replaceStateBuffered("ElectronL", 1, 2,CellManager.Cells)) {
                replaceState("ElectronL", 1, 2,Modules);
                charge++;
                if(charge >= 128) {
                    charge = 128;
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
                return (Frame.toolkit.getImage(Frame.imagesdir + "ion_power_unit_drained.png"));
            case 1:
                return (Frame.toolkit.getImage(Frame.imagesdir + "ion_power_unit.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "ion_power_unit_drained.png"));
        }
    }
}
