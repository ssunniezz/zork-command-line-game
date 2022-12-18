package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.Player;
import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.item.potion.Potion;

public class UseCommand implements Command {

    @Override
    public boolean availableInGame() {
        return true;
    }

    @Override
    public void execute(Game game) {
        Player player = game.getPlayer();
        Potion item = (Potion) player.getInventories()[1];

        if (item == null) {
            System.out.println("No item in inventories!!");
            return;
        }

        switch (item.getType()) {
            case HEAL:
                int hp = player.getHp();
                int maxHP = player.getMaxHP();

                player.setHp(Math.min(hp + item.getBuf(), maxHP));
                System.out.println("You have healed " + Math.min(maxHP-hp, item.getBuf()) + " hp");
                break;
            case DAMAGE:
                player.setAttackPower(player.getAttackPower() + item.getBuf());
                System.out.println("Your attack power increased by " + item.getBuf() + " damage");
        }

        player.getInventories()[1] = null;

    }

    @Override
    public String getInfo() {
        return "use - use the item that is not a weapon in your inventories";
    }
}
