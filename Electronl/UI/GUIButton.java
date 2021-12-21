package Electronl.UI;

import Electronl.Camera;
import Electronl.MouseInput;

import java.awt.*;

public class GUIButton extends GUIElement {
    private boolean pressed = false;

    public void onPressed() {}
    public void Update() {}

    @Override
    void updateMethods(){
        int XInnerBounds = x;
        int YInnerBounds = y;
        int XOuterBounds = x + width;
        int YOuterBounds = y + height;
        if(Camera.mouseX > XInnerBounds && Camera.mouseX < XOuterBounds && Camera.mouseY > YInnerBounds && Camera.mouseY < YOuterBounds){
            if(MouseInput.mouseDown && !pressed) {
                pressed = true;
                onPressed();
            }
        }
        else {
            pressed = false;
        }
    }
}