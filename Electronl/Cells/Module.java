package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;
import java.awt.Color;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class Module {
    public boolean emitslight = false;
    public boolean in = false;
    public int brightness = 0;
    public String name = "Module";
    public int index = 0;
    public int state = 0;
    public Image sprite;
    public int x = 0;
    public int y = 0;
    public int movex = 0;
    public int movey = 0;
    boolean bufferenabled = false;
    int bufferstate = 0;

    boolean moved = false;

    //For Override
    public Module() {}
    public Image getSprite() {return null;}
    public void Update(Module[] Modules) {
        moved = false;
        //Clear BufferState
        if(bufferenabled) {
            state = bufferstate;
            bufferenabled = false;
        }}
    public void LateUpdate() {}

    //Util
    public int getPrivate(String var) {
        switch(var) {
            case "index":
                return index;
            default:
                System.out.println("No var '" + var + "'");
                return 0;
        }
    }
    public void setPrivate(String var,int val) {
        switch(var) {
            case "index":
                index = val;
                break;
            default:
                System.out.println("No var '" + var + "'");
                break;
        }
    }
    boolean getIn() {
        return in;
    }
    void move() {
        if(moved) {
            boolean obstructed = false;
            for(Module item : CellManager.Cells) {
                if(x == item.x && y == item.y) {
                    obstructed = true;
                    break;
                }
            }
            if(!obstructed) {
                return;
            }
        }
        else {
            moved = true;
        }
        x += movex;
        y += movey;
        for(Module item : CellManager.Cells) {
            if(x == item.x && y == item.y && item.name != "Air" && item.index != index && item.movex == 0 && item.movey == 0) {
                item.movex = movex;
                item.movey = movey;
                item.move();
                break;
            }
        }
        
        movex = 0;
        movey = 0;
    }
    boolean nextTo(String cell,Module[] Modules) {
        for(Module item : Modules) {
            if(item.name.equals(cell)) {
                if( Math.sqrt( ((x - item.x) * (x - item.x)) + ((y - item.y) * (y - item.y)) ) <= 1.5) {
                    return true;
                }
            }
        }
        return false;
    }
    boolean nextTo(String cell,int s,Module[] Modules) {
        for(Module item : Modules) {
            if(item.name.equals(cell)) {
                if(item.getState() == s) {
                    if (Math.abs(x - item.x) <= 1 && Math.abs(y - item.y) <= 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    boolean replaceState(String cell,int s,int s2,Module[] Modules) {
        //System.out.println(cellManager.modules.length);
        int i = 0;
        for(Module item : Modules) {
            if(item.name.equals(cell)) {
                if(item.getState() == s) {
                    if (Math.abs(x - item.x) <= 1 && Math.abs(y - item.y) <= 1) {
                        Modules[i].state = s2;
                        //Modules[i].bufferenabled = true;
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }
    boolean replaceStateBuffered(String cell,int s,int s2,Module[] Modules) {
        //System.out.println(cellManager.modules.length);
        int i = 0;
        for(Module item : Modules) {
            if(item.name.equals(cell)) {
                if(item.getState() == s) {
                    if (Math.abs(x - item.x) <= 1 && Math.abs(y - item.y) <= 1) {
                        Modules[i].bufferstate = s2;
                        Modules[i].bufferenabled = true;
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }
    public int getState() {
        return state;
    }

    public void render(Graphics g, Frame frame) {
        sprite = getSprite();
        g.drawImage(sprite, frame.getGridCentre("x") + (int)Camera.x + (x * (4 * frame.getSizeMultiplier())), frame.getGridCentre("y") + (int)Camera.y + (y * (4 * frame.getSizeMultiplier())),4 * frame.getSizeMultiplier(),4  * frame.getSizeMultiplier(), frame);
        if(brightness >= 0) {
            g.setColor(new Color(255,255,255,brightness));
        }
        else {
            g.setColor(new Color(0,0,0,0 - brightness));
        }
        g.fillRect(frame.getGridCentre("x") + (int)Camera.x + (x * (4 * frame.getSizeMultiplier())), frame.getGridCentre("y") + (int)Camera.y + (y * (4 * frame.getSizeMultiplier())),4 * frame.getSizeMultiplier(),4  * frame.getSizeMultiplier());
    }
}
