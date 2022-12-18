package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.command.CommandFactory;
import io.muic.ooc.zork.command.CommandWithArguments;
import io.muic.ooc.zork.map.Map;
import io.muic.ooc.zork.map.MapLoader;

import java.util.Scanner;

public class PlayCommand implements CommandWithArguments {

    private String mapName;


    @Override
    public boolean availableInGame() {
        return false;
    }

    @Override
    public void execute(Game game) {
        Map loaded = MapLoader.loadMap(mapName+".txt");
        if (loaded == null) {
            System.out.println("Invalid map name");
            return;
        }
        game.setMap(loaded);
        game.setInGame(true);
        System.out.println(mapName + " has started");

        game.setPlayer();
        System.out.println("Type \"help\" for more info");
        game.play();
    }

    @Override
    public String getInfo() {
        return "play {map -name} - play new game";
    }

    @Override
    public void setArguments(String arg) {
        mapName = arg;
    }
}
