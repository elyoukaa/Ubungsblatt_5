package Exercise_5.Commands;

import Exercise_5.model.Player;

public class TurnCommand implements Command{
    String FAILURE_MESSAGE;
    String SUCCESS_MESSAGE;
    private static final int argumentNumber = 0;

    @Override
    public int getArgumentNumber() {
        return argumentNumber;
    }

    @Override
    public boolean execute(Player player, String[] commandArguments) {
        if (commandArguments.length != getArgumentNumber()) {
            FAILURE_MESSAGE = "Illegal amount of arguments!";
            return false;
        }
        player.setGodfavor("turn");
        player.setGfEffect(0);
        return true;
    }
}
