package Electronl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {
    Frame frame;

    public static boolean mouseDown = false;

    public MouseInput(Frame jframe) {
        frame = jframe;
    }

    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {
        mouseDown = false;
    }

    public void mouseClicked(MouseEvent e) {}
}