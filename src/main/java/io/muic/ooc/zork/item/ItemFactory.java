package io.muic.ooc.zork.item;

import io.muic.ooc.zork.item.potion.Potion;
import io.muic.ooc.zork.item.potion.PotionType;
import io.muic.ooc.zork.item.weapon.Weapon;
import io.muic.ooc.zork.item.weapon.WeaponType;

public class ItemFactory {

    public static Item createItem(char itemCode) {
        switch (itemCode) {
            case 's':
                return new Weapon(50, WeaponType.SWORD);
            case 'g':
                return new Weapon(100, WeaponType.GUN);
            case 'h':
                return new Potion(50, PotionType.HEAL);
            case 'd':
                return new Potion(30, PotionType.DAMAGE);
            default:
                return null;
        }
    }
}
