package Electronl;

import java.awt.*;
import java.awt.image.*;

import java.io.*;
import javax.imageio.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import Electronl.Shader;
import Electronl.Cells.Module;
import Electronl.Cells.*;
import Electronl.UI.*;

public class Frame extends JFrame {
    public boolean fullscreen = true;
    public GraphicsDevice device;
    public static Shader shader = new Shader();
    public static String imagesdir = "Electronl/images/";
    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image that is transparent
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
    public int getScreenCentre(String d){
        if(d == "x"){
            return (int)(getWidth() / 2);
        }
        else if(d == "y"){
            return (int)(getHeight() / 2);
        }
        else{
            return 0;
        }
    }
    public int getGridCentre(String d){
        if(d == "x"){
            return (int)(((getWidth() / 2)) / (4 * getSizeMultiplier())) * (4 * getSizeMultiplier());
        }
        else if(d == "y"){
            return (int)(((getHeight() / 2)) / (4 * getSizeMultiplier())) * (4 * getSizeMultiplier());
        }
        else{
            return 0;
        }
    }
    public int getSizeMultiplier() {
        int sizeMultiplier = 0;
        try {
            sizeMultiplier = (int) (((getHeight() / 400) * 4) * Camera.zoom);
        }
        catch (Exception e) {
            sizeMultiplier = (int) (((getHeight() / 400) * 4) * 1);
        }
        if(sizeMultiplier == 0) {
            sizeMultiplier = 1;
        }
        return sizeMultiplier;
    }
    public KeyInput input = new KeyInput();
    public Frame() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        device = ge.getDefaultScreenDevice();
        ImageIcon icon = new ImageIcon(imagesdir + "icon.png");

        setTitle("Electronl");
        setSize(600, 400);
        setIconImage(icon.getImage());
        setExtendedState(JFrame.EXIT_ON_CLOSE);
        fullscreen();
        setVisible(true);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        MouseInput mouseInput = new MouseInput(this);
        addKeyListener(input);
        addMouseListener(mouseInput);

        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        getContentPane().setCursor(blankCursor);

        //Init ui manager
        GUIManager.init(this);
    }

    public void fullscreen() {
        fullscreen = true;
        dispose();
        setUndecorated(true);
        device.setFullScreenWindow(this);
    }

    public void fullscreenOff() {
        fullscreen = false;
        device.setFullScreenWindow(null);
        dispose();
        setUndecorated(false);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        shader.runShader();
        BufferedImage buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = buffer.createGraphics();
        //super.paint(g2d);
        g2d.setColor(Color.WHITE);
        g2d.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
        for(Module cell : CellManager.Cells) {
            if(!(cell instanceof Air)) {
                cell.render(g2d, this);
            }
        }
        int x = (int)(((Camera.mouseX - (getGridCentre("x") + Camera.x)) / (4 * getSizeMultiplier())));
        int y = (int)(((Camera.mouseY - (getGridCentre("y") + Camera.y)) / (4 * getSizeMultiplier())));
        g2d.drawImage((toolkit.getImage(imagesdir + "cursor.png")), getGridCentre("x") + (int)Camera.x + (x * (4 * getSizeMultiplier())), getGridCentre("y") + (int)Camera.y + (y * (4 * getSizeMultiplier())),4 * getSizeMultiplier(),4  * getSizeMultiplier(), this);
        GUIManager.render(g2d,this);

        g.drawImage(buffer,0,0,this);
    }
}