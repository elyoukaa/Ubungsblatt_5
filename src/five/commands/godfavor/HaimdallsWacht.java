package five.commands.godfavor;

/**
 * class for the god-favor HaimdallsWacht.
 * @author uuxxo
 */

public class HaimdallsWacht extends Godfavour {
    static int level;
    String sign = "HW";
    int[] cost = { 4, 7, 10 };
    /**
     * Haimdall's Wacht effect: heal effect[level] health points per blocked damage point.
     */
    int[] effect = { 1, 2, 3 };

    @Override
    String getSign() {
        return sign;
    }
}
