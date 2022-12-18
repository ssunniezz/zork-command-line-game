package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.item.Item;
import io.muic.ooc.zork.map.Room;

import java.util.Scanner;

public class DropCommand implements Command {

    @Override
    public boolean availableInGame() {
        return true;
    }
    @Override
    public void execute(Game game) {
        while (true) {
            System.out.print("What do you want to drop? [Weapon/Item]: ");
            Scanner in = new Scanner(System.in);
            String toDrop = in.nextLine();

            Room curRoom = game.getMap().getCurrentRoom();
            Item[] inventories = game.getPlayer().getInventories();

            if (toDrop.equalsIgnoreCase("weapon")) {
                if (inventories[0] == null) {
                    System.out.println("You have no weapon!!");
                    return;
                }
                if (curRoom.getItem() == null) {
                    curRoom.setItem(inventories[0]);
                }
                inventories[0] = null;
                game.getPlayer().setAttackPower(10);
                System.out.println("Weapon dropped");
                break;
            }
            else if (toDrop.equalsIgnoreCase("item")) {
                if (inventories[1] == null) {
                    System.out.println("You have no item!!");
                    return;
                }
                if (curRoom.getItem() == null) {
                    curRoom.setItem(inventories[1]);
                }
                inventories[1] = null;
                System.out.println("Item dropped");
                break;
            }
            else {
                System.out.println("Again?");
            }

        }
    }

    @Override
    public String getInfo() {
        return "drop - drop item of choice that you currently carry";
    }
}
