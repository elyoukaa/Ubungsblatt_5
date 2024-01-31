package five.commands.godfavor;

/**
 * class for god-favor Thrymr's Theft.
 * @author uuxxo
 */
public class ThrymrsTheft extends Godfavour {
    static int level;
    String sign = "TT";
    int[] cost = { 3, 6, 9 };
    /**
     * Thrymr's theft effect: decreases the opponents godfavour-attack by effect[level].
     * If the opponents attack reaches zero, it becomes ineffective.
     */
    int[] effect = { 1, 2, 3 };
    @Override
    String getSign() {
        return sign;
    }
    @Override
    int getEffect(int level) {
        return effect[level - 1];
    }
    @Override
    int getCost(int level) {
        return cost[level - 1];
    }
}
