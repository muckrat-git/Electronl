package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class Diode extends Module {
    public Diode(boolean in2) {
        name = "Diode";
        state = 0;
        in = in2;
    }
    @Override
    public void Update(Module[] Modules) {
        switch(state) {
            case 0:
                if(in) {
                    if(replaceState("ElectronL", 1, 2,Modules)) {
                        state = 1;
                    }
                }
                break;
            case 1:
                if(in) {
                    int i = 0;
                    for(Module item : Modules) {
                        if(item.name.equals("Diode")) {
                            if(item.getState() == 0 && item.index != index && !item.in) {
                                if (Math.abs(x - item.x) <= 1 && Math.abs(y - item.y) <= 1) {
                                    CellManager.Cells[i].bufferstate = 1;
                                    CellManager.Cells[i].bufferenabled = true;
                                    break;
                                }
                            }
                        }
                        else if(item.name.equals("Switch")) {
                            if(item.getState() == 0 && item.index != index && item.state == 0 && item.getIn()) {
                                if (Math.abs(x - item.x) <= 1 && Math.abs(y - item.y) <= 1) {
                                    CellManager.Cells[i].bufferstate = 1;
                                    CellManager.Cells[i].bufferenabled = true;
                                    break;
                                }
                            }
                        }
                        i++;
                    }
                }
                if(!in) {
                    replaceStateBuffered("ElectronL", 0, 1, CellManager.Cells);
                }
                bufferstate = 0;
                bufferenabled = true;
                break;
        }
        super.Update(Modules);
    }
    @Override
    public Image getSprite() {
        switch(state) {
            case 0:
                if(in) {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "diode_in.png"));
                }
                else {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "diode_out.png"));
                }
            case 1:
                return (Frame.toolkit.getImage(Frame.imagesdir + "diode_lit.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "diode_in.png"));
        }
    }
}