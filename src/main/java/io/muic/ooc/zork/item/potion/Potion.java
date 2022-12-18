package io.muic.ooc.zork.item.potion;

import io.muic.ooc.zork.item.Item;

public class Potion implements Item {

    private int buf;
    private PotionType type;

    public Potion(int buf, PotionType type) {
        this.buf = buf;
        this.type = type;
    }

    public int getBuf() {
        return buf;
    }

    public PotionType getType() {
        return type;
    }

    @Override
    public String getStats() {
        switch (type) {
            case HEAL:
                return type + " potion can heal up to " + buf + " hp";
            case DAMAGE:
                return type + " potion increase " + buf + " attack damage";
        }
        return null;
    }
}
