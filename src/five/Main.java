package five;
import five.commands.CommandHandler;
public class Main {
    public static void main(String[] args) {

        CommandHandler commandHandler = new CommandHandler();

        commandHandler.handleUserInput();
    }
}
