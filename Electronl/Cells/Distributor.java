package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import java.util.Arrays;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class Distributor extends Module {
    public Distributor() {
        state = 0;
        name = "Distributor";
    }
    int toindex = 0;

    @Override
    public void Update(Module[] Modules) {
        switch(state) {
            case 0:
                if(replaceState("ElectronL", 1, 2,Modules)) {
                    replaceState("ElectronL", 1, 2,CellManager.Cells);
                    state = 1;
                }
                break;
            case 1:
                Module[] adj = getAdjacent("ElectronL", 0, Modules);
                int i = 0;
                for(Module item : Modules) {
                    if (item.name.equals("Diode")) {
                        if (item.getState() == 0 && item.index != index && !item.in) {
                            if (Math.abs(x - item.x) <= 1 && Math.abs(y - item.y) <= 1) {
                                adj = Arrays.copyOf(adj, adj.length + 1);
                                adj[adj.length - 1] = item;
                                break;
                            }
                        }
                    }
                }
                if (toindex >= adj.length) {
                    toindex = 0;
                }
                if (adj.length != 0) {
                    CellManager.Cells[adj[toindex].index].state = 1;
                    toindex++;
                }
                state = 0;
                break;
        }
        super.Update(Modules);
    }

    @Override
    public Image getSprite() {
        switch(state) {
            case 0:
                return (Frame.toolkit.getImage(Frame.imagesdir + "distributor.png"));
            default:
                return (Frame.toolkit.getImage(Frame.imagesdir + "distributor_lit.png"));
        }
    }
}