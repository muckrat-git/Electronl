package Electronl.Cells;

import Electronl.CellManager;
import Electronl.Frame;

import java.awt.*;

public class InputGate extends Module {
    boolean charged = false;
    public InputGate(int s) {
        name = "Input";
        state = s;
        type = s;
        charged = false;
    }
    int type = 0;
    @Override
    public void Update(Module[] Modules) {
        super.Update(Modules);
        switch(state) {
            case 0:
                bufferstate = type;
                bufferenabled = true;
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
                    i++;
                }
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }
    @Override
    public Image getSprite() {
        switch(state) {
            case 0:
                return (Frame.toolkit.getImage(Frame.imagesdir + "diode_lit.png"));
            case 1:
                if(charged) {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "input_key.png"));
                }
                else {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "input_key_unlit.png"));
                }
            case 2:
                if(charged) {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "input_mouse.png"));
                }
                else {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "input_mouse_unlit.png"));
                }
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "diode_lit.png"));
        }
    }
    @Override
    public int getPrivate(String var) {
        switch(var) {
            case "index":
                return index;
            case "charged":
                if(charged) {
                    return 1;
                }
                else {
                    return 0;
                }
            default:
                System.out.println("No var '" + var + "'");
                return 0;
        }
    }
    @Override
    public void setPrivate(String var, int val) {
        switch(var) {
            case "index":
                index = val;
                break;
            case "charged":
                charged = (val == 1);
                break;
            default:
                System.out.println("No var '" + var + "'");
                break;
        }
    }
}