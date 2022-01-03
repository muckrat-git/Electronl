package Electronl.Cells;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import Electronl.*;

public class Distributor extends Module {
    public Distributor() {
        name = "Distributor";
    }
    @Override
    public Image getSprite() {
        return (Frame.toolkit.getImage(Frame.imagesdir + "distributor_lit.png"));
    }
}