package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.command.CommandWithArguments;
import io.muic.ooc.zork.item.Item;
import io.muic.ooc.zork.item.potion.Potion;
import io.muic.ooc.zork.item.weapon.Weapon;
import io.muic.ooc.zork.item.weapon.WeaponType;
import io.muic.ooc.zork.map.Room;
import io.muic.ooc.zork.monster.Monster;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SaveCommand implements CommandWithArguments {

    private String savedName;

    @Override
    public boolean availableInGame() {
        return true;
    }

    @Override
    public void execute(Game game) {
        if (savedName == null) {
            System.out.println("saved point name missing");
            return;
        }

        try {
            PrintWriter writer = new PrintWriter(savedName+".txt", StandardCharsets.UTF_8);

            StringBuilder playerInfo = new StringBuilder();
            playerInfo.append("*")
                    .append(game.getPlayer().getName()).append(",")
                    .append(game.getPlayer().getMaxHP()).append(",")
                    .append(game.getPlayer().getHp()).append(",")
                    .append(game.getPlayer().getAttackPower()).append(",");

            Item[] inventory = game.getPlayer().getInventories();

            for (Item item: inventory) {
                if (item == null) { playerInfo.append("-"); continue; }
                if (item instanceof Weapon) {
                    switch (((Weapon) item).getType()) {
                        case SWORD:
                            playerInfo.append("s");
                            break;
                        case GUN:
                            playerInfo.append("g");
                            break;
                    }
                } else if (item instanceof Potion) {
                    switch (((Potion) item).getType()) {
                        case HEAL:
                            playerInfo.append("h");
                            break;
                        case DAMAGE:
                            playerInfo.append("d");
                            break;
                    }
                }
            }

            writer.println(playerInfo);

            List<Room[]> map = game.getMap().getMap();

            for (Room[] rooms: map) {
                StringBuilder info = new StringBuilder();
                for (Room room : rooms) {

                    boolean[] doors = room.getHasDoors();
                    for (boolean bool : doors) {
                        info.append(bool ? "x" : "-");
                    }

                    Item item = room.getItem();
                    if (item instanceof Weapon) {
                        switch (((Weapon) item).getType()) {
                            case SWORD:
                                info.append("s-");
                                break;
                            case GUN:
                                info.append("g-");
                                break;
                        }
                    } else if (item instanceof Potion) {
                        switch (((Potion) item).getType()) {
                            case HEAL:
                                info.append("-h");
                                break;
                            case DAMAGE:
                                info.append("-d");
                                break;
                        }
                    } else {
                        info.append("--");
                    }

                    Monster monster = room.getMonster();
                    if (monster == null) {
                        info.append("-");
                    } else {
                        switch (monster.getAttack()) {
                            case 10:
                                info.append("s");
                                break;
                            case 20:
                                info.append("m");
                                break;
                            case 50:
                                info.append("l");
                                break;
                        }
                        info.append(monster.getHp());
                    }

                    info.append(game.getMap().getCurrentRoom() == room ? "s," : "-,");
                }
                writer.println(info.deleteCharAt(info.length()-1));
            }

            writer.close();
            game.addSavedPoint(savedName);
            System.out.println("game saved");
        } catch (IOException ignored) {}
    }

    @Override
    public String getInfo() {
        return "save {saved-point-name} - load game state from saved point";
    }

    @Override
    public void setArguments(String arg) {
        savedName = arg;
    }
}
