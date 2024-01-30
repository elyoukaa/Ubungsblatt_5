package Exercise_5.Commands;
import Exercise_5.model.Player;

public class QuitCommand implements Command{
    String SUCCESS_MESSAGE = "";
    String FAILURE_MESSAGE = "";
    final static int argumentNumber = 0;
    public int getArgumentNumber() {
        return argumentNumber;
    }
    public boolean execute(Player player, String[] commandArguments) {
        if (commandArguments.length != getArgumentNumber()) {
            FAILURE_MESSAGE = "Illegal amount of arguments!";
            return false;
        }
        SUCCESS_MESSAGE = null;
        return true;
    }
}
