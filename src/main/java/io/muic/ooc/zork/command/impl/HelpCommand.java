package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.command.CommandFactory;

public class HelpCommand implements Command {

    @Override
    public boolean availableInGame() {
        return true;
    }

    @Override
    public void execute(Game game) {
        for (Command command: CommandFactory.registeredCommand.values()) {
            if (command.availableInGame() == game.isInGame()) {
                System.out.println(command.getInfo());
            }

        }
    }

    @Override
    public String getInfo() {
        return "help - print all commands";
    }
}
