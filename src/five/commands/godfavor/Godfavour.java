package five.commands.godfavor;
import five.model.Player;
import five.commands.Command;
import five.commands.CommandHandler;
import java.util.HashMap;

/**
 * this is the superclass for all god-favor moves.
 * @author uuxxo
 */
public class Godfavour implements Command {
    /**
     * this HashSet links the Strings of a god-favor with an object of the corresponding class.
     * like CommandHandler
     */
    public static HashMap<String, Godfavour> godfavors;
    static final int ARGUMENT_NUMBER = 2;
    String sign;
    int[] cost;
    int[] effect;

    /**
     * Getter.
     * @return the number of arguments the god-favour command should have.
     */
    public int getArgumentNumber() {
        return ARGUMENT_NUMBER;
    }
    String getSign() {
        return sign;
    }
    int getEffect(int level) {
        return effect[level - 1];
    }
    int getCost(int level) {
        return cost[level - 1];
    }
    static void setGodFavor(Player player, String godfavor) {
        player.setGodfavor(godfavor);
    }
    static void setEffect(Player player, int effect) {
        player.setGfEffect(effect);
    }

    /**
     * determines which god-favor is being used and.
     * @param player the player that is using it
     * @param commandArguments the string array containing god-favor name and level
     * @return whether argument-wise the god-favour can be used.
     */
    public boolean execute(Player player, String[] commandArguments) {
        if (CommandHandler.phase < 2 || CommandHandler.phase > 3) {
            OUTPUT_MESSAGE[1] = "wrong command!";
            return false;
        }
        if (commandArguments.length != getArgumentNumber()) {
            OUTPUT_MESSAGE[1] = "illegal number of arguments!";
            return false;
        }
        godfavors = new HashMap<>();
        godfavors.put("TT", new ThrymrsTheft());
        godfavors.put("TS", new ThorsHammer());
        godfavors.put("IR", new IdunsRegeneration());
        godfavors.put("HW", new HaimdallsWacht());
        godfavors.put("VB", new VarsBond());
        godfavors.put("MW", new MimirsWisdom());
        if (!godfavors.containsKey(commandArguments[0])) {
            OUTPUT_MESSAGE[1] = "no such god-favor move!";
            return false;
        }
        try {
            int level = Integer.parseInt(commandArguments[1]);
            return godfavors.get(commandArguments[0]).execute(player, level);
        } catch (NumberFormatException notInt) {
            OUTPUT_MESSAGE[1] = "not an int!";
            return false;
        }
    }

    /**
     * the sets the parameters of the god-favor, depending on which god-favor and level.
     * String godFavor, int gfEffect, int cost, int gfLevel of class player are being changed.
     * @param player the player that is using it.
     * @param level of god-favor.
     * @return true, if the changes were successful.
     */
    public boolean execute(Player player, int level) {
        if (!player.playerGodFavor.contains(getSign())) {
            OUTPUT_MESSAGE[1] = "unable to use this move!";
            return false;
        }
        if (level < 1 || level > 3) {
            OUTPUT_MESSAGE[1] = "illegal level!";
            return false;
        }
        setGodFavor(player, getSign());
        setEffect(player, getEffect(level));
        player.gfLevel = level;
        player.setCost(getCost(level));
        CommandHandler.phase++;
        if (CommandHandler.phase == 3) {
            OUTPUT_MESSAGE[0] = Player.playersTurn();
        } else {
            OUTPUT_MESSAGE[0] = "OK, all players are now ready to evaluate!";
        }
        return true;
    }
}
