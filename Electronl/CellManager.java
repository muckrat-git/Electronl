package Electronl;

import java.util.Arrays;
import Electronl.*;
import Electronl.Cells.*;

public class CellManager {
    static int tickSpeed = 10;
    public Module[] modules = {
            new Air(),
            new Electronl(0),//Neutral
            new Electronl(1),//Powered
            new Electronl(2),//Drained
            new Mover()
    };
    public static int mod_i = 0;
    public static Module[] Cells = new Module[0];

    public static void Update() {
        //Buffer to avoid instant update
        Module[] CellBuffer = new Module[Cells.length];
        int i = 0;
        for(Module item : CellBuffer) {
            CellBuffer[i] = new Module();
            CellBuffer[i].name = Cells[i].name;
            CellBuffer[i].state = Cells[i].state;
            CellBuffer[i].x = Cells[i].x;
            CellBuffer[i].y = Cells[i].y;
            i++;
        }
        //Update every cell/module
        for(Module item : Cells) {
            item.Update(CellBuffer);
            item.LateUpdate();
        }
    }

    public static Module[] createCell(int x, int y, Module cell) {
        int i = 0;
        for(Module item : Cells) {
            if(item.x == x && item.y == y) {
                Cells[i] = cell;
                Cells[i].x = x;
                Cells[i].y = y;
                return Cells;
            }
            i++;
        }
        if(cell instanceof Air) {
            return Cells;
        }
        Cells = Arrays.copyOf(Cells, Cells.length + 1);
        Cells[Cells.length - 1] = cell;
        Cells[Cells.length - 1].x = x;
        Cells[Cells.length - 1].y = y;
        return Cells;
    }
}