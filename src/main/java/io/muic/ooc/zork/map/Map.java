package io.muic.ooc.zork.map;

import java.util.Arrays;
import java.util.List;

public class Map {

    private int numMonster;
    private Room startingRoom;
    private Room currentRoom;
    private List<Room[]> map;

    public Map(int numMonster, Room startingRoom, List<Room[]> map) {
        this.numMonster = numMonster;
        this.startingRoom = startingRoom;
        this.currentRoom = startingRoom;
        this.map = map;
    }

    public int getNumMonster() {
        return numMonster;
    }

    public void setNumMonster(int numMonster) {
        this.numMonster = numMonster;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public Room getStartingRoom() {
        return startingRoom;
    }

    public List<Room[]> getMap() {
        return map;
    }
}
