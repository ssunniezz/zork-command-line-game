package io.muic.ooc.zork.item.weapon;

import io.muic.ooc.zork.item.Item;

public class Weapon implements Item {

    private int damage;
    private WeaponType type;

    public Weapon(int damage, WeaponType type) {
        this.damage = damage;
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public WeaponType getType() {
        return type;
    }

    @Override
    public String getStats() {
        return type + " with " + damage + " damage";
    }
}
