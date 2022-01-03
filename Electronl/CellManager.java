package Electronl;

import java.util.Arrays;
import Electronl.Cells.*;

//ElectronL by Scratchdragon
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
        new Diode(false),//Out diode
        new Diode(true),//In diode
        new Switch(),//Switch
        new LEDPanel(),
        new InputGate(1),//Key input
        new InputGate(2),//Mouse input
        new IonPowerUnit(0),//Drained
        new IonPowerUnit(1)//Charged
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
            //bro this one line of code cost me 3 hours of debugging
            CellBuffer[i].in = Cells[i].in;
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
            if(cell == item) {
                return Cells;
            }
            else if(item.x == x && item.y == y) {
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
