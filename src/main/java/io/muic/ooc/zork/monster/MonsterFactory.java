package io.muic.ooc.zork.monster;

import java.util.HashMap;
import java.util.Map;

public class MonsterFactory {

    public static Monster createMonster(String type) {
        switch (type) {
            case "s":
                return new Monster(50,10);
            case "m":
                return new Monster(100,20);
            case "l":
                return new Monster(200, 50);
            default:
                return null;
        }
    }
}
