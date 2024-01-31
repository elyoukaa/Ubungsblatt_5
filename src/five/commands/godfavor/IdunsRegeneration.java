package five.commands.godfavor;

/**
 * class for god-favor Iduns Regeneration.
 * @author uuxxo
 */
public class IdunsRegeneration extends Godfavour {
    static int level;
    String sign = "IR";
    int[] cost = { 4, 7, 10 };
    /**
     * Idun's regeneration effect: heal effect[level] health points.
     */
    int[] effect = { 2, 4, 6 };
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
