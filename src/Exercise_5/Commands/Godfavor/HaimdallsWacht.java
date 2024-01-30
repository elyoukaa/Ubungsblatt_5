package Exercise_5.Commands.Godfavor;

import Exercise_5.model.Player;

public class HaimdallsWacht extends Godfavour{
    static final String sign = "HW";
    static int level;
    int cost[] = { 4, 7, 10 };
    /**
     * Haimdall's Wacht effect: heal effect[level] health points per blocked damage point.
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
