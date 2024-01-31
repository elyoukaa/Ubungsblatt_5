package five.commands;
import five.model.Player;

/**
 * class for print command.
 * @author uuxxo
 */
public class PrintCommand implements Command {
    static final int ARGUMENT_NUMBER = 0;
    private static final String SEPERATOR = ";";

    /**
     * gets the number of arguments the command requires.
     * @return the argument number.
     */
    public int getArgumentNumber() {
        return ARGUMENT_NUMBER;
    }

    /**
     * print command executed.
     * @param player the player that is on turn.
     * @param commandArguments the string arguments needed for the command.
     * @return true, if the command was executed successfully
     */
    public boolean execute(Player player, String[] commandArguments) {
        if (commandArguments.length != getArgumentNumber()) {
            OUTPUT_MESSAGE[1] = "illegal number of arguments!";
            return false;
        }
        Player firstPlayer = Player.getPlayerOne();
        Player secondPlayer = Player.getPlayerTwo();
        String firstPlayerInfo = firstPlayer.getName() + SEPERATOR + firstPlayer.getHP() + SEPERATOR
                + firstPlayer.getGodPower();
        String secondPlayerInfo = secondPlayer.getName() + SEPERATOR + secondPlayer.getHP() + SEPERATOR
                + secondPlayer.getGodPower();
        OUTPUT_MESSAGE[0] = firstPlayerInfo + "\n" + secondPlayerInfo;
        return true;
    }
}
