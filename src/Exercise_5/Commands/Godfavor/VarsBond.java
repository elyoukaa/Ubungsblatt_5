package Exercise_5.Commands.Godfavor;

import Exercise_5.model.Player;

public class VarsBond extends Godfavour{
    static final String sign = "VB";
    static int level;
    int cost[] = { 10, 14, 18 };
    /**
     * Var's Bond.
     * Increases the health points by effect[level] per godpower used by the opposing player.
     */
    int[] effect = { 1, 2, 3 };
    /*@Override
    public boolean execute(Player player, String[] commandArguments) {
        level = Integer.parseInt(commandArguments[1]);
        setGodFavor(player, sign);
        setEffect(player, effect[level - 1]);
        return true;
    }*/
}
