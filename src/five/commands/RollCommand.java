package five.commands;
import five.model.Player;

/**
 * class for roll-command.
 * @author uuxxo
 */
public class RollCommand implements Command {
    static final int ARGUMENT_NUMBER = 6;
    private static final int ADD_ONE = 1;
    private static final String AXE_MOVE = "MA";
    private static final String HELMET_MOVE = "MD";
    private static final String GP_HELMET_MOVE = "GMD";
    private static final String BOW_MOVE = "RA";
    private static final String GP_BOW_MOVE = "GRA";
    private static final String SHIELD_MOVE = "RD";
    private static final String GP_SHIELD_MOVE = "GRD";
    private static final String STEAL_MOVE = "ST";
    private static final String GP_STEAL_MOVE = "GST";
    private static boolean moveExists = true;

    /**
     * gets the number of arguments required.
     * @return required number.
     */
    public int getArgumentNumber() {
        return ARGUMENT_NUMBER;
    }
    private void simulateFightElements(Player player, String fightElement) {
        switch (fightElement) {
            case AXE_MOVE:
                player.setCloseAttack(player.getCloseAttack() + ADD_ONE);
                break;
            case GP_HELMET_MOVE:
                player.changeGP(ADD_ONE);
            case HELMET_MOVE:
                player.setCloseShield(player.getCloseShield() + ADD_ONE);
                break;
            case GP_BOW_MOVE:
                player.changeGP(ADD_ONE);
            case BOW_MOVE:
                player.setRangedAttack(player.getRangedAttack() + ADD_ONE);
                break;
            case GP_SHIELD_MOVE:
                player.changeGP(ADD_ONE);
            case SHIELD_MOVE:
                player.setRangedShield(player.getRangedShield() + ADD_ONE);
                break;
            case GP_STEAL_MOVE:
                player.changeGP(ADD_ONE);
            case STEAL_MOVE:
                player.setStealingPoints(player.getStealingPoints() + ADD_ONE);
                break;
            default:
                OUTPUT_MESSAGE[1] = "non-existing move!";
                moveExists = false;
                break;
        }
    }

    /**
     * executes the roll command.
     * @param player the player that is on turn.
     * @param commandArguments the string arguments needed for the command.
     * @return true, if the command was executed successfully.
     */
    public boolean execute(Player player, String[] commandArguments) {
        if (CommandHandler.phase >= 2) {
            OUTPUT_MESSAGE[1] = "wrong command!";
            return false;
        }
        if (commandArguments.length != getArgumentNumber()) {
            OUTPUT_MESSAGE[1] = "illegal number of arguments!";
            return false;
        }
        for (int i = 0; i < commandArguments.length; i++) {
            simulateFightElements(player, commandArguments[i]);
            if (!moveExists) {
                return false;
            }
        }
        CommandHandler.phase++;
        OUTPUT_MESSAGE[0] = Player.playersTurn();
        return true;
    }
}
