package five.commands.godfavor;

/**
 * class for god-favor Var's Bond.
 * @author uuxxo
 */
public class VarsBond extends Godfavour {
    static int level;
    String sign = "VB";
    int[] cost = { 10, 14, 18 };
    /**
     * Var's Bond.
     * Increases the health points by effect[level] per godpower used by the opposing player.
     */
    int[] effect = { 1, 2, 3 };
}
