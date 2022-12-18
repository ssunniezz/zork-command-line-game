package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.map.Room;

import java.util.List;

public class MapCommand implements Command {

    @Override
    public boolean availableInGame() {
        return true;
    }

    @Override
    public void execute(Game game) {
        List<Room[]> map = game.getMap().getMap();

        System.out.println("x - You are here");
        System.out.println("i - Weapon/Item in this room");
        System.out.println("m - Monster in this room\n");

        for (Room[] rooms: map) {
            for (Room room: rooms) {
                System.out.print("+---");
            }
            System.out.println("+");

            for (Room room: rooms) {
                System.out.print("|");
                System.out.print(room == game.getMap().getCurrentRoom()? "x": " ");
                System.out.print(room.getItem() != null? "i": " ");
                System.out.print(room.getMonster() != null? "m": " ");
            }
            System.out.println("|");
        }

        for (Room room: map.get(0)) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    @Override
    public String getInfo() {
        return "map - print 2D map";
    }
}
