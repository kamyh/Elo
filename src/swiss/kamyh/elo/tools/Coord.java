package swiss.kamyh.elo.tools;

import swiss.kamyh.elo.gui.Menu;

/**
 * Created by Vincent on 05.06.2016.
 */
public class Coord {

    private Menu menu;
    private int x;
    private int y;

    public Coord(Menu menu, int x, int y) {
        this.menu = menu;
        this.x = x;
        this.y = y;
    }

    public Coord(Menu menu, int slot) {
        this.menu = menu;
        this.x = this.calculateCoordinates(slot)[0];
        this.y = this.calculateCoordinates(slot)[1];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Menu getMenu() {
        return menu;
    }

    public int asSlotNumber() {
        return this.toSlotNumber();
    }

    private int[] calculateCoordinates(int slot) {
        //system.out.println("Calculating by slot: " + slot);
        int slotx = (slot % 9) + 1;
        int sloty = (slot / 9) + 1;

        //system.out.println("RETURN: " + (slotx++) + ", " + (sloty++));
        return new int[]{slotx, sloty};
    }

    private int toSlotNumber() {
        return ((this.y - 1) * 9) + (this.x - 1);
    }
}

