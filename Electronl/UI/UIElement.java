package Electronl.UI;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;

public class UIElement {
    public int layer = 0;
    public int x = 0;
    public int y = 0;
    public int width = 0;
    public int height = 0;
    public Image image;

    public UIElement() {}
    public Image getImage() {return image;}
    public void Update() {}
}