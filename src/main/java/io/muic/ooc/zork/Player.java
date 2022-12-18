package io.muic.ooc.zork;

import io.muic.ooc.zork.item.Item;
import io.muic.ooc.zork.item.weapon.Weapon;

public class Player {
    private String name;
    private int maxHP;
    private int hp;
    private int attackPower;
    private Item[] inventories;

    public Player(String name) {
        this.name = name;
        maxHP = 100;
        hp = 100;
        attackPower = 10;
        inventories = new Item[2];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInventories(Item[] inventories) {
        this.inventories = inventories;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public Item[] getInventories() {
        return inventories;
    }

    public String getStats() {
        StringBuilder stats = new StringBuilder();
        stats.append("Max HP: ")
                .append(maxHP)
                .append(", current HP: ")
                .append(hp)
                .append(", attack Power: ")
                .append(attackPower)
                .append("\n")
                .append("Inventories: ");

        if (inventories[0] != null) {
            stats.append(inventories[0].getStats()).append(", ");
        } else {
            stats.append("No weapon, ");
        }

        if (inventories[1] != null) {
            stats.append(inventories[1].getStats()).append("");
        } else {
            stats.append("No item");
        }
        return stats.toString();
    }
}
