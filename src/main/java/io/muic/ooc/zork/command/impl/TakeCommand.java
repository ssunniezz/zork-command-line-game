package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.Player;
import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.item.Item;
import io.muic.ooc.zork.item.weapon.Weapon;
import io.muic.ooc.zork.map.Room;

public class TakeCommand implements Command {

    @Override
    public boolean availableInGame() {
        return true;
    }

    @Override
    public void execute(Game game) {
        Room currentRoom = game.getMap().getCurrentRoom();
        Item item = currentRoom.getItem();
        Player player = game.getPlayer();

        if (item == null) {
            System.out.println("No item in this room!!");
            return;
        }

        if (item instanceof Weapon) {
            player.getInventories()[0] = item;
            player.setAttackPower(((Weapon) item).getDamage());
        } else {
            player.getInventories()[1] = item;
        }

        currentRoom.setItem(null);

        System.out.println("You picked up " + item.getStats());
    }

    @Override
    public String getInfo() {
        return "take - pick up the item in the current room";
    }
}
