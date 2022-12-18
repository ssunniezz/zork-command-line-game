package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.command.Command;

public class ExitCommand implements Command {

    @Override
    public boolean availableInGame() {
        return false;
    }
    @Override
    public void execute(Game game) {
        game.setExit(true);
    }

    @Override
    public String getInfo() {
        return "exit - exit whole game";
    }
}
