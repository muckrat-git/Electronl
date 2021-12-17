package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class Insulator extends Module {
    public Insulator() {
        name = "Insulator";
    }
    @Override
    public Image getSprite() {
        return (Frame.toolkit.getImage(Frame.imagesdir + "insulator.png"));
    }
}
