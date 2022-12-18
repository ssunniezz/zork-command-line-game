package io.muic.ooc.zork.map;

import io.muic.ooc.zork.item.ItemFactory;
import io.muic.ooc.zork.item.potion.Potion;
import io.muic.ooc.zork.item.potion.PotionType;
import io.muic.ooc.zork.item.weapon.Weapon;
import io.muic.ooc.zork.item.weapon.WeaponType;
import io.muic.ooc.zork.monster.Monster;
import io.muic.ooc.zork.monster.MonsterFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {

    public static Map loadMap(String fileName) {
        int numMonster = 0;
        List<Room[]> map = new ArrayList<>();
        Room startingRoom = null;

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            int row = 0;

            while (br.ready()) {
                String line = br.readLine();
                if (line.startsWith("*")) { continue; }
                String[] rooms = line.split(",");
                Room[] roomsArr = new Room[rooms.length];

                for (int i=0; i<rooms.length; i++) {
                    String room = rooms[i];
                    Room tempRoom = new Room();
                    // direction {up, down, left, right}
                    boolean[] doors = new boolean[] {false, false, false, false};

                    for (int j=0; j<7; j++) {
                        switch (j) {
                            case 0: case 1: case 2: case 3:
                                if (room.charAt(j) != '-') {
                                    doors[j] = true;
                                }
                                break;
                            case 4: case 5:
                                tempRoom.setItem(ItemFactory.createItem(room.charAt(j)));
                                break;
                            case 6:
                                Monster mon = MonsterFactory.createMonster(room.substring(j,j+1));
                                if (mon != null) {
                                    tempRoom.setMonster(mon);
                                    numMonster++;
                                }
                                break;
                        }

                        String monsHp = room.substring(7, room.length()-1);
                        if (monsHp.length() > 0 && tempRoom.getMonster() != null) {
                            tempRoom.getMonster().setHp(Integer.parseInt(monsHp));
                        }
                        if (room.charAt(room.length()-1) != '-') {
                            startingRoom = tempRoom;
                        }
                    }

                    tempRoom.setHasDoors(doors);
                    tempRoom.setLocation(new int[] {row, i});
                    roomsArr[i] = tempRoom;
                }

                row++;
                map.add(roomsArr);
            }

            return new Map(numMonster, startingRoom, map);

        } catch (IOException e ) {
            return null;
        }
    }
}
