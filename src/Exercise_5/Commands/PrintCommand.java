package Exercise_5.Commands;
import Exercise_5.model.Player;

public class PrintCommand implements Command{
    private static final String SEPERATOR = ";";
    String SUCCESS_MESSAGE;
    String FAILURE_MESSAGE;
    final static int argumentNumber = 0;
    public int getArgumentNumber() {
        return argumentNumber;
    }
    public boolean execute(Player player, String[] commandArguments) {
        if (commandArguments.length != 0) {
            FAILURE_MESSAGE = "illegal number of arguments!";
            return false;
        }
        Player firstPlayer = Player.getPlayerOne();
        Player secondPlayer = Player.getPlayerTwo();
        String firstPlayerInfo = firstPlayer.getName() + SEPERATOR + firstPlayer.getHP() + SEPERATOR
                + firstPlayer.getGodPower();
        String secondPlayerInfo = secondPlayer.getName() + SEPERATOR + secondPlayer.getHP() + SEPERATOR
                + secondPlayer.getGodPower();
        SUCCESS_MESSAGE = firstPlayerInfo + "\n" + secondPlayerInfo;
        return true;
    }
}
