package de.byteevolve.gungame.kit;

import de.byteevolve.gungame.kit.kits.*;

public enum Kit {
    LEVEL_0(0, new Level_0()),
    LEVEL_1(1, new Level_1()),
    LEVEL_2(2, new Level_2()),
    LEVEL_3(3, new Level_3()),
    LEVEL_4(4, new Level_4());

    private int id;
    private KitInventory kitInventory;

    public int getId() {
        return id;
    }

    public KitInventory getKitInventory() {
        return kitInventory;
    }

    Kit(int id, KitInventory kitInventory) {
        this.kitInventory = kitInventory;
        this.id = id;
    }

}
