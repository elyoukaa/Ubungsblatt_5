package Exercise_5.Commands;
import Exercise_5.model.Player;

public class GodfavorCommand implements Command {
    String SUCCESS_MESSAGE = "";
    String FAILURE_MESSAGE = "";
    final static int argumentNumber = 1;
    public int getArgumentNumber() {
        return argumentNumber;
    }

    @Override
    public boolean execute(Player player, String[] commandArguments) {
        return false;
    }
}
