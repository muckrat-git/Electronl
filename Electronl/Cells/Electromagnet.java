package Electronl.Cells;

import Electronl.Frame;

import Electronl.CellManager;
import java.awt.*;

public class Electromagnet extends Module {
    String direction = "y";
    public Electromagnet(String dir) {
        name = "Emagnet";
        direction = dir;
        state = 0;
    }

    @Override
    public void Update(Module[] Modules) {
        Module[] adj = getAdjacent("ElectronL",1,Modules);
        Module[] tomove = getAdjacent("Grabber", Modules);
        switch(direction) {
            case "x":
                if(state == 1) {
                    bufferstate = 2;
                    bufferenabled = true;
                    for(Module cell : tomove) {
                        if(cell.y != y && cell.x != x) {
                            CellManager.Cells[cell.index].movex = x - cell.x;
                            CellManager.Cells[cell.index].move();
                        }
                    }
                    break;
                }
                if(state == 2) {
                    bufferstate = 0;
                    bufferenabled = true;
                    break;
                }
                for(Module cell : adj) {
                    if(cell.y == y && (cell.x == x - 1 || cell.x == x + 1)) {
                        cell.state = 0;
                        bufferstate = 1;
                        bufferenabled = true;
                    }
                }
                adj = getAdjacent("Emagnet",1,Modules);
                for(Module cell : adj) {
                    if(cell.y == y && (cell.x == x - 1 || cell.x == x + 1)) {
                        bufferstate = 1;
                        bufferenabled = true;
                    }
                }
                break;
            case "y":
                if(state == 1) {
                    bufferstate = 2;
                    bufferenabled = true;
                    for(Module cell : tomove) {
                        if(cell.y != y && cell.x != x) {
                            CellManager.Cells[cell.index].movey = y - cell.y;
                            CellManager.Cells[cell.index].move();
                        }
                    }
                    break;
                }
                if(state == 2) {
                    bufferstate = 0;
                    bufferenabled = true;
                    break;
                }
                for(Module cell : adj) {
                    if(cell.x == x && (cell.y == y - 1 || cell.y == y + 1)) {
                        cell.state = 0;
                        bufferstate = 1;
                        bufferenabled = true;
                    }
                }
                adj = getAdjacent("Emagnet",1,Modules);
                for(Module cell : adj) {
                    if(cell.x == x && (cell.y == y - 1 || cell.y == y + 1)) {
                        bufferstate = 1;
                        bufferenabled = true;
                    }
                }
                break;
        }
        super.Update(Modules);
    }

    @Override
    public Image getSprite() {
        switch (direction) {
            case "x":
                if(state == 0) {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "emagnet_x.png"));
                }
                else {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "emagnet_x_lit.png"));
                }
            case "y":
                if(state == 0) {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "emagnet_y.png"));
                }
                else {
                    return (Frame.toolkit.getImage(Frame.imagesdir + "emagnet_y_lit.png"));
                }
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "cursor.png"));
        }
    }
}