package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.command.CommandWithArguments;
import io.muic.ooc.zork.map.Map;
import io.muic.ooc.zork.map.Room;

public class GoCommand implements CommandWithArguments {

    private String direction;

    @Override
    public boolean availableInGame() {
        return true;
    }

    @Override
    public void execute(Game game) {
        Map map = game.getMap();
        Room curRoom = map.getCurrentRoom();
        int[] location = curRoom.getLocation();
        int hp = game.getPlayer().getHp();
        int maxHp = game.getPlayer().getMaxHP();

        switch (direction.toLowerCase()) {
            case "north":
                if (curRoom.getHasDoors()[0]) {
                    map.setCurrentRoom(map.getMap().get(location[0]-1)[location[1]]);
                    System.out.println("You entered the north door");
                    if (hp == maxHp) { return; }
                    game.getPlayer().setHp(Math.min(hp+10, maxHp));
                    System.out.println("Your hp increased by " + Math.min(maxHp-hp, 10));
                    return;
                }
                break;
            case "south":
                if (curRoom.getHasDoors()[1]) {
                    map.setCurrentRoom(map.getMap().get(location[0]+1)[location[1]]);
                    System.out.println("You entered the south door");
                    if (hp == maxHp) { return; }
                    game.getPlayer().setHp(Math.min(hp+10, maxHp));
                    System.out.println("Your hp increased by " + Math.min(maxHp-hp, 10));
                    return;
                }
                break;
            case "west":
                if (curRoom.getHasDoors()[2]) {
                    map.setCurrentRoom(map.getMap().get(location[0])[location[1]-1]);
                    System.out.println("You entered the west door");
                    if (hp == maxHp) { return; }
                    game.getPlayer().setHp(Math.min(hp+10, maxHp));
                    System.out.println("Your hp increased by " + Math.min(maxHp-hp, 10));
                    return;
                }
                break;
            case "east":
                if (curRoom.getHasDoors()[3]) {
                    map.setCurrentRoom(map.getMap().get(location[0])[location[1]+1]);
                    System.out.println("You entered the east door");
                    if (hp == maxHp) { return; }
                    game.getPlayer().setHp(Math.min(hp+10, maxHp));
                    System.out.println("Your hp increased by " + Math.min(maxHp-hp, 10));
                    return;
                }
                break;
        }

        System.out.println("You can't go " + direction.toLowerCase());
    }

    @Override
    public String getInfo() {
        return "go {direction} - move to the room as specified by the direction, e.g north,east,west,south";
    }

    @Override
    public void setArguments(String arg) {
        direction = arg;
    }
}
