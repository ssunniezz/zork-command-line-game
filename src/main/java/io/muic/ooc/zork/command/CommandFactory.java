package io.muic.ooc.zork.command;

import io.muic.ooc.zork.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    public static Map<String, Command> registeredCommand = new HashMap<>() {{
        put("info", new InfoCommand());
        put("help", new HelpCommand());
        put("exit", new ExitCommand());
        put("take", new TakeCommand());
        put("use", new UseCommand());
        put("drop", new DropCommand());
        put("attack", new AttackCommand());
        put("map", new MapCommand());
        put("quit", new QuitCommand());
        put("play", new PlayCommand());
        put("go", new GoCommand());
        put("load", new LoadCommand());
        put("save", new SaveCommand());
    }};

    public static Command getCommand(String input) {

        String[] split = input.split(" ");
        Command command = registeredCommand.getOrDefault(split[0], registeredCommand.get("help"));

        if (split.length > 1 && command instanceof CommandWithArguments) {
            CommandWithArguments cmdArg = (CommandWithArguments) command;
            cmdArg.setArguments(split[1]);
        }

        return command;
    }
}
