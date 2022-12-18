package io.muic.ooc.zork.command;

import io.muic.ooc.zork.Game;

public interface Command {

    public boolean availableInGame();
    public void execute(Game game);
    public String getInfo();
}
