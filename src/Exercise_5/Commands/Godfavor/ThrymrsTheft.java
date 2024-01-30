package Exercise_5.Commands.Godfavor;

import Exercise_5.model.Player;

public class ThrymrsTheft extends Godfavour{
    static final String sign = "TT";
    static int level;
    int cost[] = { 3, 6, 9 };
    /**
     * Thrymr's theft effect: decreases the opponents godfavour-attack by effect[level].
     * If the opponents attack reaches zero, it becomes ineffective.
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
