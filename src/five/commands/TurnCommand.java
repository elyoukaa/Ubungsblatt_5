package five.commands;

import five.model.Player;
/**
 * class for turn-command.
 * @author uuxxo
 */
public class TurnCommand implements Command {
    private static final int ARGUMENT_NUMBER = 0;

    /**
     * gets the number of arguments required for this command.
     * @return the required amount of arguments.
     */
    @Override
    public int getArgumentNumber() {
        return ARGUMENT_NUMBER;
    }

    /**
     * executes turn-command.
     * @param player the player that is on turn.
     * @param commandArguments the string arguments needed for the command.
     * @return true, if the command was executed successfully.
     */
    @Override
    public boolean execute(Player player, String[] commandArguments) {
        if (CommandHandler.phase < 2 || CommandHandler.phase > 3) {
            OUTPUT_MESSAGE[1] = "this command can't be used yet!";
            return false;
        }
        if (commandArguments.length != getArgumentNumber()) {
            OUTPUT_MESSAGE[1] = "Illegal amount of arguments!";
            return false;
        }
        player.setGodfavor("turn");
        player.setGfEffect(0);
        CommandHandler.phase++;
        if (CommandHandler.phase == 3) {
            OUTPUT_MESSAGE[0] = Player.playersTurn();
        } else {
            OUTPUT_MESSAGE[0] = "OK, all players are now ready to evaluate!";
        }
        return true;
    }
}
