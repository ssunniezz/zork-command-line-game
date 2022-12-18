package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.Player;
import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.command.CommandFactory;
import io.muic.ooc.zork.command.CommandWithArguments;
import io.muic.ooc.zork.item.Item;
import io.muic.ooc.zork.item.ItemFactory;
import io.muic.ooc.zork.map.Map;
import io.muic.ooc.zork.map.MapLoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class LoadCommand implements CommandWithArguments {

    private String savedName;

    @Override
    public boolean availableInGame() {
        return false;
    }

    @Override
    public void execute(Game game) {
        try {
            FileReader fr = new FileReader(savedName+".txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();

            String[] playerInfo = line.split(",");
            Player player = game.getPlayer();

            //s,100,98,10,-h

            player.setName(playerInfo[0].substring(1));
            player.setMaxHP(Integer.parseInt(playerInfo[1]));
            player.setHp(Integer.parseInt(playerInfo[2]));
            player.setAttackPower(Integer.parseInt(playerInfo[3]));

            char[] inventoryInfo = playerInfo[4].toCharArray();
            Item[] inventory = new Item[2];

            for (int i=0; i<inventoryInfo.length; i++) {
                switch (inventoryInfo[i]) {
                    case 's': case 'g':
                        inventory[0] = ItemFactory.createItem(inventoryInfo[i]);
                        break;
                    case 'h': case 'd':
                        inventory[1] = ItemFactory.createItem(inventoryInfo[i]);
                        break;
                    default:
                        inventory[i] = null;
                }
            }

            player.setInventories(inventory);


            Map loaded = MapLoader.loadMap(savedName+".txt");
            game.setMap(loaded);
            game.setInGame(true);
            System.out.println("loaded from " + savedName);

            game.play();

        } catch (IOException e ) {
            System.out.println("Invalid saved point name");
        }
    }

    @Override
    public String getInfo() {
        return "load {saved-point-name} - load game state from saved point";
    }

    @Override
    public void setArguments(String arg) {
        savedName = arg;
    }
}
