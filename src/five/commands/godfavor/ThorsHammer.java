package five.commands.godfavor;

/**
 * class for god-favor Thor's Hammer (Thor's Blitz).
 * @author uuxxo
 */
public class ThorsHammer extends Godfavour {
    static int level;
    String sign = "TS";
    int[] cost = { 4, 8, 12 };
    /**
     * Thor's Hammer effect: deal effect[level] damage. Works even if health points reach zero.
     */
    int[] effect = { 2, 5, 8 };
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
