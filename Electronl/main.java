package Electronl;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import java.awt.event.*;

import java.util.Arrays;

import Electronl.Cells.*;
import Electronl.UI.*;
import Electronl.Frame;

import java.util.concurrent.TimeUnit;

class MouseInput implements MouseListener {
    Frame frame;

    public MouseInput(Frame jframe) {
        frame = jframe;
    }

    public void mousePressed(MouseEvent e) {
        int x = (int)(((Camera.mouseX - (frame.getGridCentre("x") + Camera.x)) / (4 * frame.getSizeMultiplier())));
        int y = (int)(((Camera.mouseY - (frame.getGridCentre("y") + Camera.y)) / (4 * frame.getSizeMultiplier())));
        CellManager cellManager = new CellManager();
        CellManager.createCell(x, y, cellManager.modules[cellManager.mod_i]);
        main.update = true;
    }

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mouseClicked(MouseEvent e) {}
}

class KeyInput extends KeyAdapter {
    int keytick = 0;
    int[] keysdown = new int[3];
    int keyindex = 0;
    @Override
    public void keyPressed(KeyEvent e) {
        for(int key : keysdown) {
            if(key == e.getKeyCode()) {
                return;
            }
        }
        main.handleInput(e.getKeyCode());
        if(e.getKeyCode() == 27 || e.getKeyCode() == 122) {
            return;
        }
        keysdown[keyindex] = e.getKeyCode();
        keyindex++;
        if(keyindex >= 3) {
            keyindex = 0;
        }
        keytick = 0;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int i = 0;
        for(int key : keysdown) {
            if(key == e.getKeyCode()) {
                keysdown[i] = 0;
            }
            i++;
        }
    }
}

public class main {
    static Frame globalframe;
    public static boolean update = false;
    static int sizeMultiplier = 1;
    public static void handleInput(int key) {
        CellManager cellManager = new CellManager();
        switch(key) {
            case 27:
                globalframe.fullscreenOff();
                break;
            case 40:
                Camera.zoom = Camera.zoom / 1.2;
                break;
            case 38:
                Camera.zoom = Camera.zoom * 1.2;
                break;
            case 87:
                Camera.y += 16 * (sizeMultiplier);
                break;
            case 65:
                Camera.x += 16 * (sizeMultiplier);
                break;
            case 83:
                Camera.y -= 16 * (sizeMultiplier);
                break;
            case 68:
                Camera.x -= 16 * (sizeMultiplier);
                break;
            case 39:
                //right
                CellManager.mod_i++;
                if(CellManager.mod_i >= cellManager.modules.length) {
                    CellManager.mod_i = 0;
                }
                break;
            case 37:
                //left
                CellManager.mod_i--;
                if(CellManager.mod_i < 0) {
                    CellManager.mod_i = cellManager.modules.length - 1;
                }
                break;
            case 122:
                if(globalframe.fullscreen) {
                    globalframe.fullscreenOff();
                    globalframe.fullscreen = false;
                }
                else {
                    globalframe.fullscreen();
                    globalframe.fullscreen = true;
                }
                break;
            default:
                break;
        }
        int x = (int)Math.floor(((Camera.x + (2 * globalframe.getSizeMultiplier())) / (4 * globalframe.getSizeMultiplier())));
        int y = (int)Math.floor(((Camera.y + (2 * globalframe.getSizeMultiplier())) / (4 * globalframe.getSizeMultiplier())));
        Camera.x = (x * (4 * globalframe.getSizeMultiplier()));
        Camera.y = (y * (4 * globalframe.getSizeMultiplier()));
        update = true;
    }
    public static void delay(int time){
        try{
            Thread.sleep(time);
        } catch(InterruptedException e){
            System.out.println("Time error");
        }
    }
    static int ticks = 0;
    public static void main(String[] args) {
        Frame frame = new Frame();
        globalframe = frame;
        CellManager.createCell(0,0,new Electronl(0));
        CellManager.createCell(1,0,new Electronl(0));
        while(true) {
            GUIManager.Update(frame);
            Point point = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(point, globalframe.getComponent(0));
            System.out.print("");
            if(Camera.mouseX != point.getX() || Camera.mouseY != point.getY()) {
                Camera.mouseX = point.getX();
                Camera.mouseY = point.getY();
                frame.repaint();
                update = false;
            }
            sizeMultiplier = frame.getSizeMultiplier() / 4;
            if(update) {
                update = false;
                frame.repaint();
            }

            ticks++;
            if(ticks > (100 / CellManager.tickSpeed)) {
                ticks = 0;
                CellManager.Update();
                frame.repaint();
            }

            frame.input.keytick++;
            if(frame.input.keytick > 15) {
                //Handle input
                for (int key : frame.input.keysdown) {
                    handleInput(key);
                }
                frame.input.keytick = 0;
            }
            delay(10);
        }
    }
}
