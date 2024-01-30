package Exercise_5.Commands.Godfavor;
import Exercise_5.model.Player;
import Exercise_5.Commands.Command;
import Exercise_5.Commands.CommandHandler;
import java.util.HashMap;

public abstract class Godfavour implements Command {
    String SUCCESS_MESSAGE;
    String FAILURE_MESSAGE;
    String sign;
    static HashMap<String, Godfavour> godfavors;
    static final int argumentNumber = 2;
    public int getArgumentNumber() {
        return argumentNumber;
    }
    static void setGodFavor(Player player, String godfavor) {
        player.setGodfavor(godfavor);
    }
    static void setEffect(Player player, int effect) {
        player.setGfEffect(effect);
    }
    int[] cost;
    int[] effect;
    public boolean execute(Player player, String[] commandArguments) {
        if (CommandHandler.phase < 2 || CommandHandler.phase > 4 ) {
            FAILURE_MESSAGE = "wrong command!";
            return false;
        }
        if (commandArguments.length != 1) {
            FAILURE_MESSAGE = "illegal number of arguments!";
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
            FAILURE_MESSAGE = "no such godfavor move!";
            return false;
        }
        int level = Integer.parseInt(commandArguments[1]);
        return godfavors.get(commandArguments[0]).execute(player, level);
    }
    public boolean execute(Player player, int level) {
        if (!player.playerGodFavor.contains(sign)) {
            FAILURE_MESSAGE = "unable to use this move!";
            return false;
        }
        setGodFavor(player, sign);
        setEffect(player, effect[level - 1]);
        SUCCESS_MESSAGE = Player.playersTurn();
        return true;
    }
}
