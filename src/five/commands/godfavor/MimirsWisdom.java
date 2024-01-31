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
     * Mimir's Wisdom effect: give effect[level] godpower.
     */
    int[] effect = { 3, 5, 7 };
    @Override
    String getSign() {
        return sign;
    }
}
