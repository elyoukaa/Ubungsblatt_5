package five.commands.godfavor;

/**
 * class for god-favor Mimir's Wisdom.
 * @author uuxxo
 */
public class MimirsWisdom extends Godfavour {
    static int level;
    String sign = "MW";
    int[] cost = { 3, 5, 7 };
    /**
     * Mimir's Wisdom effect: heal effect[level] hp per received damage.
     */
    int[] effect = { 3, 5, 7 };
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
