package Electronl.Cells;

import Electronl.CellManager;
import Electronl.Frame;

import java.awt.*;

public class Switch extends Module {
    public Switch() {
        name = "Switch";
        state = 0;
        in = false;
    }
    @Override
    public void Update(Module[] Modules) {
        switch(state) {
            case 0:
                if(replaceState("ElectronL", 1, 2,Modules)) {
                    if(in) {
                        in = false;
                    }
                    else {
                        in = true;
                    }
                }
                break;
            case 1:
                int i = 0;
                for(Module item : Modules) {
                    if(item.name.equals("Diode")) {
                        if(item.getState() == 0 && item.index != index && !item.getIn()) {
                            if (Math.abs(x - item.x) <= 1 && Math.abs(y - item.y) <= 1) {
                                CellManager.Cells[i].bufferstate = 1;
                                CellManager.Cells[i].bufferenabled = true;
                                break;
                            }
                        }
                    }
                    i++;
                }
                bufferstate = 0;
                bufferenabled = true;
                break;
            default:
                break;
        }
        super.Update(Modules);
    }
    @Override
    public Image getSprite() {
        switch(state) {
            case 0:
                if(in) {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "switch_on.png"));
                }
                else {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "switch_off.png"));
                }
            case 1:
                return (Frame.toolkit.getImage(Frame.imagesdir + "diode_lit.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "switch_off.png"));
        }
    }
}