package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.command.Command;

public class QuitCommand implements Command {
    @Override
    public boolean availableInGame() {
        return true;
    }

    @Override
    public void execute(Game game) {
        game.setInGame(false);
        System.out.println("return to lobby");
    }

    @Override
    public String getInfo() {
        return "quit - end the current game and return to lobby";
    }
}
