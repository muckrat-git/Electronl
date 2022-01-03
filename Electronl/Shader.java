package Electronl;

import Electronl.Cells.Module;
import java.util.Arrays;

public class Shader {
    public Shader() {}

    public void runShader() {
        //3rd party shaders should be possible soon
        int[] lightindex = new int[0];
        for(Module item : CellManager.Cells) {
            if(item.emitslight) {
                lightindex = Arrays.copyOf(lightindex, lightindex.length + 1);
                lightindex[lightindex.length - 1] = item.index;
            }
        }
        int i = 0;
        int shaderconstrast = 10;
        for(Module item : CellManager.Cells) {
            int distance = shaderconstrast;
            for(int index : lightindex) {
                int x = CellManager.Cells[index].x;
                int y = CellManager.Cells[index].y;
                double b = Math.sqrt( ((x - item.x) * (x - item.x)) + ((y - item.y) * (y - item.y)) );
                if(b < distance) {
                    distance = (int)b;
                }
            }
            if(distance > shaderconstrast) {
                distance = shaderconstrast;
            }
            CellManager.Cells[i].brightness = (distance - (shaderconstrast / 2)) * -6;
            i++;
        }
    }
}
