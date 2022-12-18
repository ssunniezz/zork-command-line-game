package io.muic.ooc.zork.monster;

public class Monster {
    private int hp;
    private int attack;

    public Monster(int hp, int attack) {
        this.hp = hp;
        this.attack = attack;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public String getStats() {
        return "Monster with " + hp + " hp and " + attack + " attack damage";
    }

}
