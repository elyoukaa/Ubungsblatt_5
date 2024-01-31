package five.commands;
import five.model.Player;

/**
 * interface Command for the user-input commands.
 * @author uuxxo
 * @author Programmieren-Team
 */
public interface Command {
    /**
     * the message to be output, depending on success and failure.
     */
    String[] OUTPUT_MESSAGE = new String[2];

    /**
     * method to be executed. Depends on which command has been used.
     * @param player the player that is on turn.
     * @param commandArguments the string arguments needed for the command.
     * @return true, if the execution was successful.
     */
    boolean execute(Player player, String[] commandArguments);

    /**
     * the amount of arguments the command accepts.
     * @return true, if the number is right
     */
    int getArgumentNumber();
}
