package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.command.Command;

public class InfoCommand implements Command {

    @Override
    public boolean availableInGame() {
        return true;
    }

    @Override
    public void execute(Game game) {
        System.out.println("Player info: " + game.getPlayer().getStats() + "\n" +
                "Room info: " + game.getMap().getCurrentRoom().getStats());
    }

    @Override
    public String getInfo() {
        return "info - print out information of the player, and the room you are currently in";
    }
}
