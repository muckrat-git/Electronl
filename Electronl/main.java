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
        int x = (int) Math.floor((((Camera.mouseX - (frame.getGridCentre("x") + (int)Camera.x)) + (2 * frame.getSizeMultiplier())) / (4 * frame.getSizeMultiplier())));
        int y = (int) Math.floor((((Camera.mouseY - (frame.getGridCentre("y") + (int)Camera.y)) + (2 * frame.getSizeMultiplier())) / (4 * frame.getSizeMultiplier()))) ;
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
    @Override
    public void keyPressed(KeyEvent e) {
        main.handleInput(e.getKeyCode());
    }
}

public class main {
    public static boolean update = false;
    static int sizeMultiplier = 1;
    public static void handleInput(int key) {
        CellManager cellManager = new CellManager();
        switch(key) {
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
            default:
                System.out.println(key);
                break;
        }
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
        CellManager.createCell(0,0,new Electronl(0));
        CellManager.createCell(1,0,new Electronl(0));
        while(true) {
            GUIManager.Update(frame);
            System.out.print("");
            if(Camera.mouseX != MouseInfo.getPointerInfo().getLocation().getX() || Camera.mouseY != MouseInfo.getPointerInfo().getLocation().getY()) {
                Camera.mouseX = MouseInfo.getPointerInfo().getLocation().getX();
                Camera.mouseY = MouseInfo.getPointerInfo().getLocation().getY();
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
            delay(10);
        }
    }
}
