package io.muic.ooc.zork.map;

import io.muic.ooc.zork.item.Item;
import io.muic.ooc.zork.monster.Monster;

public class Room {
    private Monster monster;
    private Item item;
    private int[] location;
    private boolean[] hasDoors;
    private String[] direction = new String[] {"north", "south", "west", "east"};

    public Room() {

    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public void setHasDoors(boolean[] hasDoors) {
        this.hasDoors = hasDoors;
    }

    public boolean[] getHasDoors() {
        return hasDoors;
    }

    @Override
    public String toString() {
        return "Room{" + getMonster().getStats() +
                "}";
    }

    public String getStats() {
        StringBuilder stats = new StringBuilder();
        if (getMonster() != null) {
            stats.append(getMonster().getStats())
                    .append(", ");
        }
        if (getItem() != null) {
            if (stats.length() == 0) {
                stats.append("No Monster, ");
            }
            stats.append(getItem().getStats());
        }
        if (getItem() == null && getMonster() == null) {
            stats.append("No monster, No item");
        }

        stats.append("\n");
        stats.append("Doors available on:");
        for (int i=0; i<hasDoors.length; i++) {
            if (hasDoors[i]) {
                stats.append(" ").append(direction[i]);
            }
        }
        return stats.toString();
    }
}
