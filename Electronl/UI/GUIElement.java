package Electronl.UI;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;

public class GUIElement {
    public int layer = 0;
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;
    public Image image;

    //For Override
    public GUIElement() {}
    public Image getImage() {return image;}
    public void Update() {}

    //For GUI Manager and other Elements
    void updateMethods() {}

    //Util
}