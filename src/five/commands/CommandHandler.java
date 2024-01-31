package five.commands;

import java.util.Arrays;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import five.model.Player;
import java.util.HashSet;
import five.commands.godfavor.Godfavour;
/**
 * This class handles the user input and execute the commands.
 * This class was inspired by the model solution in Task 4A by @Programmieren-Team and therefore
 * contains several code-snippets.
 *
 * @author uuxxo
 * @author Programmieren-Team
 */
public class CommandHandler {
    /**
     * phase determines in which game-phase we are at the moment.
     * int value 0-5: 0,1 phase1; 2,3 phase2, 4,5 phase 3
     */
    public static int phase = 0;
    /**
     * regular expression.
     */
    public static final String EMPTYSTRING = "";
    private static final String COMMAND_SEPARATOR_REGEX = " +";
    private static final String ERROR_PREFIX = "Error, ";
    private static final String COMMAND_NOT_FOUND_FORMAT = "command '%s' not found!";
    private static final String WRONG_ARGUMENTS_COUNT_FORMAT = "wrong number of arguments for command '%s'!";
    private static final String ROLL_COMMAND_NAME = "roll";
    private static final String GODFAVOR_COMMAND_NAME = "godfavor";
    private static final String TURN_COMMAND_NAME = "turn";
    private static final String PRINT_COMMAND_NAME = "print";
    private static final String QUIT_COMMAND_NAME = "quit";
    private static final String EVAL_COMMAND_NAME = "evaluate";
    private boolean running = false;
    private String[] commandLine;
    private final Map<String, Command> commands;

    /**
     * constructor. innitializes HashMap and adds the god-favor tags.
     * @param commandLine the command line argument args.
     */
    public CommandHandler(String[] commandLine) {
        this.commands = new HashMap<>();
        this.initCommands();
        this.commandLine = commandLine;
    }
    private static void addGF(HashSet<String> set) {
        set.add("TT");
        set.add("TS");
        set.add("VB");
        set.add("MW");
        set.add("IR");
        set.add("HW");
    }
    private boolean assessCommandLine(String[] split) {
        if (split.length != 6) {
            System.out.println("Illegal amount of arguments!");
            this.running = false;
            return false;
        }
        String firstName = split[0];
        String[] godfavors = split[1].split(";");
        String secondName = split[2];
        String[] secondGodFavors = split[3].split(";");
        if (godfavors.length != 3 || secondGodFavors.length != 3) {
            System.out.println("Illegal god-favors!");
            this.running = false;
            return false;
        }
        String health = split[4];
        String power = split[5];
        if (firstName.contains(";") || secondName.contains(";")) {
            System.out.println("Names don't contain a semicolon!");
            this.running = false;
            return false;
        }
        HashSet<String> godFavorsAvailable = new HashSet<>();
        addGF(godFavorsAvailable);
        HashSet<String> godFavorOne = new HashSet<>();
        HashSet<String> godFavorTwo = new HashSet<>();
        for (int i = 0; i < godfavors.length; i++) {
            if (!godFavorsAvailable.contains(godfavors[i]) || !(godFavorsAvailable.contains(secondGodFavors[i]))) {
                System.out.println("This god-favor does not exist!");
                this.running = false;
                return false;
            }
            godFavorOne.add(godfavors[i]);
            godFavorTwo.add(secondGodFavors[i]);
        }
        if (godFavorOne.size() != 3 || godFavorTwo.size() != 3) {
            System.out.println("No duplicate god-favors allowed!");
            this.running = false;
            return false;
        }
        try {
            int hp = Integer.parseInt(health);
            int godPower = Integer.parseInt(power);
            if (hp < 5) {
                System.out.println("starting hp can't be low than 5!");
                this.running = false;
                return false;
            } else if (godPower < 0) {
                System.out.println("God-Power can't be negative!");
                this.running = false;
                return false;
            }
            return true;
        } catch (NumberFormatException notInt) {
            System.out.println("health and power must be integers!");
            this.running = false;
            return false;
        }
    }

    /**
     * starts the program.
     */
    public void handleUserInput() {
        this.running = true;

        try (Scanner scanner = new Scanner(System.in)) {
            if (!assessCommandLine(this.commandLine)) {
                return;
            }
            String[] split = commandLine;
            String firstName = split[0];
            String[] godfavors = split[1].split(";");

            String secondName = split[2];
            String[] secondGodFavors = split[3].split(";");

            int health = Integer.parseInt(split[4]);
            int godPower = Integer.parseInt(split[5]);
            Player firstPlayer = new Player(firstName, health);
            Player secondPlayer = new Player(secondName, health);
            firstPlayer.changeGP(godPower);
            secondPlayer.changeGP(godPower);
            for (int i = 0; i < godfavors.length; i++) {
                firstPlayer.playerGodFavor.add(godfavors[i]);
                secondPlayer.playerGodFavor.add(secondGodFavors[i]);
            }
            while (running && scanner.hasNextLine()) {
                executeCommand(scanner.nextLine());
            }
        }

    }

    /**
     * snippet from Programmieren-Team. Slightly changed.
     * @param commandWithArguments
     */
    public void executeCommand(String commandWithArguments) {
        String[] splittedCommand = commandWithArguments.trim().split(COMMAND_SEPARATOR_REGEX);
        String commandName = splittedCommand[0];
        String[] commandArguments = Arrays.copyOfRange(splittedCommand, 1, splittedCommand.length);

        executeCommand(commandName, commandArguments);
    }

    void executeCommand(String commandName, String[] commandArguments) {
        if (!this.commands.containsKey(commandName)) {
            System.out.println(ERROR_PREFIX + COMMAND_NOT_FOUND_FORMAT.formatted(commandName));
        } else if (commands.get(commandName).getArgumentNumber() != commandArguments.length) {
            System.out.println(ERROR_PREFIX + WRONG_ARGUMENTS_COUNT_FORMAT.formatted(commandName));
        } else {
            String message;
            if (this.commands.get(commandName).execute(currentlyPlaying(), commandArguments)) {
                phase++;
                message = this.commands.get(commandName).OUTPUT_MESSAGE[0];
                if (message == null) {
                    this.running = false;
                } else {
                    System.out.println(message);
                }
            } else {
                message = this.commands.get(commandName).OUTPUT_MESSAGE[1];
                if (!message.equals("quit")) {
                    System.out.println("ERROR: " + message);
                }
            }
        }
    }
    private void initCommands() {
        this.addCommand(GODFAVOR_COMMAND_NAME, new Godfavour());
        this.addCommand(TURN_COMMAND_NAME, new TurnCommand());
        this.addCommand(PRINT_COMMAND_NAME, new PrintCommand());
        this.addCommand(QUIT_COMMAND_NAME, new QuitCommand());
        this.addCommand(ROLL_COMMAND_NAME, new RollCommand());
        this.addCommand(EVAL_COMMAND_NAME, new EvaluateCommand());
    }
    private void addCommand(String commandName, Command command) {
        this.commands.put(commandName, command);
    }
    private Player currentlyPlaying() {
        if (phase % Player.PLAYER_NUMBER == 0) {
            return Player.getPlayerOne();
        }
        return Player.getPlayerTwo();
    }
}
