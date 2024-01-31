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
    /*@Override
    public boolean execute(Player player, String[] commandArguments) {
        level = Integer.parseInt(commandArguments[1]);
        setGodFavor(player, sign);
        setEffect(player, effect[level - 1]);
        return true;
    }*/
}
