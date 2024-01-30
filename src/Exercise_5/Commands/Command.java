package Exercise_5.Commands;
import Exercise_5.model.Player;

public interface Command {
     String SUCCESS_MESSAGE = CommandHandler.EMPTYSTRING;
     String FAILURE_MESSAGE = CommandHandler.EMPTYSTRING;
     boolean execute(Player player, String[] commandArguments);
     int getArgumentNumber();
}
