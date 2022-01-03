package Electronl;

import java.awt.MouseInfo;

public class Camera {
    public static double zoom = 1;
    public static double x = 0;
    public static double y = 0;
    public static double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
    public static double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
}