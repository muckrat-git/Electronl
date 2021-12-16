package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class Air extends Module {
    public Air() {
        name = "Air";
    }
    @Override
    public Image getSprite() {
        return (Frame.toolkit.getImage(Frame.imagesdir + "cursor.png"));
    }
}