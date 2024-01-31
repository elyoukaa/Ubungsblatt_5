package five.commands;
import five.model.Player;
public class RollCommand implements Command{
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
    final static int argumentNumber = 6;
    public int getArgumentNumber() {
        return argumentNumber;
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
            default:
                OUTPUT_MESSAGE[1] = "non-existing move!";
                moveExists = false;
        }
    }
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
        OUTPUT_MESSAGE[0] = Player.playersTurn();
        return true;
    }
}
