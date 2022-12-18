package io.muic.ooc.zork.command.impl;

import io.muic.ooc.zork.Game;
import io.muic.ooc.zork.Player;
import io.muic.ooc.zork.command.Command;
import io.muic.ooc.zork.map.Room;
import io.muic.ooc.zork.monster.Monster;

import java.util.Random;

public class AttackCommand implements Command {

    @Override
    public boolean availableInGame() {
        return true;
    }

    @Override
    public void execute(Game game) {
        Room curRoom = game.getMap().getCurrentRoom();
        Player player = game.getPlayer();
        Monster monster = curRoom.getMonster();

        if (monster == null) {
            System.out.println("There is no monster in this room!!");
            return ;
        }

        monster.setHp(monster.getHp() - player.getAttackPower());

        if (monster.getHp() <= 0) {
            curRoom.setMonster(null);
            game.getMap().setNumMonster(game.getMap().getNumMonster()-1);
            player.setAttackPower(player.getAttackPower() + 10);
            System.out.println("You defeated the monster");
            System.out.println("Your attack power increased by 10 damage");
        } else {
            System.out.println("You hit the monster with " + player.getAttackPower() + " damage");
            Random rand = new Random();
            int damage = rand.nextInt(monster.getAttack());
            player.setHp(player.getHp() - damage);
            System.out.println("You got hit " + damage + " damage");
        }
    }

    @Override
    public String getInfo() {
        return "attack with - attack a monster in the current room";
    }
}
