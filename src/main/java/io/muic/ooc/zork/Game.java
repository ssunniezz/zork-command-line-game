package io.muic.ooc.zork;

import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.command.CommandFactory;
import io.muic.ooc.zork.map.Map;
import io.muic.ooc.zork.map.MapLoader;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Game {

    private Player player;

    private Map map;

    private boolean exit;

    private boolean inGame = false;

    private String[] allMap = new String[] {"Map1", "Map2"};

    private Set<String> allSavePoint = new HashSet<>();

    public Game() {
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() { return map; }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void setInGame(boolean inGame) { this.inGame = inGame; }

    public boolean isInGame() {
        return inGame;
    }

    public void addSavedPoint(String savedName) {
        allSavePoint.add(savedName);
    }

    public void setPlayer() {
        Scanner in = new Scanner(System.in);
        while (player == null) {
            System.out.print("Please enter your name: ");
            String name = in.nextLine();
            if (!StringUtils.isBlank(name)) {
                this.player = new Player(name);
            }
        }
    }

    private void lobby() {
        while (!exit) {
            System.out.print("Available maps: ");
            for (String mapName: allMap) {
                System.out.print(mapName + " ");
            }
            System.out.println();

            System.out.print("Available save points: ");
            for (String savedName: allSavePoint) {
                System.out.print(savedName + " ");
            }
            System.out.println();

            Scanner in = new Scanner(System.in);
            Command cmd = CommandFactory.getCommand(in.nextLine());
            if (!cmd.availableInGame()) {
                cmd.execute(this);
            } else {
                CommandFactory.getCommand("help").execute(this);
            }
        }
    }

    public void play() {
        while (isInGame()) {
            if (getMap().getNumMonster() == 0) {
                System.out.println("Congratulations! You win");
                System.out.println("return to lobby");
                setInGame(false);
                continue;
            }
            Scanner in = new Scanner(System.in);
            Command cmd = CommandFactory.getCommand(in.nextLine());
            if (cmd.availableInGame()) {
                cmd.execute(this);
            } else {
                CommandFactory.getCommand("help").execute(this);
            }
        }
    }

    public void start() {
        System.out.println("Hello" + " type help for more info");
        lobby();
    }

}
