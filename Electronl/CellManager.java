package Electronl;

import Electronl.Cells.Module;
import java.util.Arrays;
import Electronl.*;
import Electronl.Cells.*;

public class CellManager {
    static int tickSpeed = 10;
    public Module[] modules = {
            new Air(),
            new Electronl(0),//Neutral
            new Electronl(1),//Powered
            new Mover(),
            new PowerCell(0),//Drained
            new PowerCell(1),//Charged
            new Grabber(),
            new Insulator(),
            new Diode(false),
            new Diode(true)
    };
    public static int mod_i = 0;
    public static Module[] Cells = new Module[0];

    static void OrderCells() {
        int i = 0;
        for(Module item : Cells) {
            if(item.name.equals("ElectronL")) {
                item.index = i;
                i++;
            }
        }
        for(Module item : Cells) {
            if(!item.name.equals("ElectronL")) {
                item.index = i;
                i++;
            }
        }
        Module[] CellBuffer = new Module[Cells.length];
        for(Module item : Cells) {
            CellBuffer[item.index] = item;
        }
        Cells = CellBuffer;
    }

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
            CellBuffer[i].index = Cells[i].index;
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
                OrderCells();
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
        Cells[Cells.length - 1].index = Cells.length - 1;
        OrderCells();
        return Cells;
    }
}
