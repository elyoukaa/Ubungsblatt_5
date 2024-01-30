package Exercise_5.Commands.Godfavor;

import Exercise_5.model.Player;

public class IdunsRegeneration extends Godfavour{
    static final String sign = "IR";
    static int level;
    int cost[] = { 4, 7, 10 };
    /**
     * Idun's regeneration effect: heal effect[level] health points.
     */
    int[] effect = { 2, 4, 6 };
}
