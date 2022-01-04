package Electronl.Cells;

import Electronl.Frame;
import Electronl.CellManager;

import java.awt.*;

public class OcularSensor extends Module {
    public OcularSensor() {
        name = "OcularSensor";
        state = 0;
    }

    Module[] prev = new Module[0];
    @Override
    public void Update(Module[] Modules) {
        super.Update(Modules);
        Module[] adj = getAdjacent("_ALL",Modules);
        if(state == 1) {
            boolean changed = false;
            if(prev.length != adj.length) {
                changed = true;
            }
            else {
                int i = 0;
                for(Module cell : adj) {
                    if(cell.index == index || cell.name.equals("ElectronL") || cell.name.equals("Diode")) {}
                    else if(cell.name != prev[i].name) {
                        changed = true;
                    }
                    else if(cell.x != prev[i].x || cell.y != prev[i].y) {
                        changed = true;
                    }
                    else if(cell.state != prev[i].state) {
                        changed = true;
                    }
                    i++;
                }
            }
            if(changed) {
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
                state = 0;
                prev = adj;
            }
        }
        else {
            prev = adj;
        }
    }

    @Override
    public Image getSprite() {
        switch(state) {
            case 0:
                return (Frame.toolkit.getImage(Frame.imagesdir + "ocular_sensor_unlit.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "ocular_sensor.png"));
        }
    }
}