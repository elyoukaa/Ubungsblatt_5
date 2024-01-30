package Exercise_5.Commands.Godfavor;

import Exercise_5.model.Player;

public class MimirsWisdom extends Godfavour{
    static final String sign = "MW";
    static int level;
    int cost[] = { 3, 5, 7 };
    /**
     * Mimir's Wisdom effect: give effect[level] godpower.
     */
    int[] effect = { 3, 5, 7 };
    /*@Override
    public boolean execute(Player player, String[] commandArguments) {
        level = Integer.parseInt(commandArguments[1]);
        setGodFavor(player, sign);
        setEffect(player, effect[level - 1]);
        return true;
    }*/
}
