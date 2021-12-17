package Electronl.Cells;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.Frame;
import Electronl.main;

public class Electronl extends Module {
    public Electronl(int s) {
        state = s;
        name = "ElectronL";
    }

    @Override
    public void Update(Module[] Modules) {
        switch(state) {
            case 0:
                if (replaceState("ElectronL", 1, 2,Modules)) {
                    state = 1;
                }
                else if (replaceState("Grabber", 2, 0,Modules)) {
                    state = 1;
                }
                else {
                    
                }
                break;
            case 1:
                state = 2;
                break;
            case 2:
                state = 0;
                break;
        }
        super.Update(Modules);
    }
    @Override
    public Image getSprite() {
        switch(state) {
            case 0:
                return (Frame.toolkit.getImage(Frame.imagesdir + "electronl.png"));
            case 1:
                return (Frame.toolkit.getImage(Frame.imagesdir + "electronl_lit.png"));
            case 2:
                return (Frame.toolkit.getImage(Frame.imagesdir + "electronl_drained.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "electronl.png"));
        }
    }
}
