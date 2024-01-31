package five.commands;
import five.model.Player;

/**
 * class for quit-command.
 * @author uuxxo
 */
public class QuitCommand implements Command {
    static final int ARGUMENT_NUMBER = 0;

    /**
     * gets the number of arguments required for this command.
     * @return number of required arguments.
     */
    public int getArgumentNumber() {
        return ARGUMENT_NUMBER;
    }

    /**
     * executes quit-command.
     * @param player the player that is on turn.
     * @param commandArguments the string arguments needed for the command.
     * @return true, if the command was executed successfully.
     */
    public boolean execute(Player player, String[] commandArguments) {
        if (commandArguments.length != getArgumentNumber()) {
            OUTPUT_MESSAGE[1] = "Illegal amount of arguments!";
            return false;
        }
        OUTPUT_MESSAGE[0] = null;
        return true;
    }
}
