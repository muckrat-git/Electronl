package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class LEDPanel extends Module {
    public LEDPanel() {
        name = "LED Panel";
        state = 0;
    }
    int charge = 0;
    @Override
    public void Update(Module[] Modules) {
        super.Update(Modules);
        switch(state) {
            case 0:
                if(nextTo("ElectronL",1,CellManager.Cells)) {
                    state = 1;
                    charge = 16;
                    emitslight = true;
                }
                emitslight = false;
                break;
            case 1:
                emitslight = true;
                charge--;
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
                return (Frame.toolkit.getImage(Frame.imagesdir + "led_panel_lit.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "led_panel.png"));
        }
    }
}
