package Exercise_5.Commands.Godfavor;

import Exercise_5.model.Player;

public class ThorsHammer extends Godfavour{
    static final String sign = "TS";
    static int level;
    int cost[] = { 4, 8, 12 };
    /**
     * Thor's Hammer effect: deal effect[level] damage. Works even if health points reach zero.
     */
    int[] effect = { 2, 4, 8 };

    /*@Override
    public boolean execute(Player player, String[] commandArguments) {
        level = Integer.parseInt(commandArguments[1]);
        setGodFavor(player, sign);
        setEffect(player, effect[level - 1]);
        return true;
    }*/

}
