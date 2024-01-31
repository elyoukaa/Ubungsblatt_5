package five;
import five.commands.CommandHandler;

/**
 * Main class.
 * @author uuxxo
 * @author Programmieren-Team
 */
public final class Main {
    private Main() {
        throw new UnsupportedOperationException("Utility classes cannot be instantiated");
    }
    /**
     * main method.
     * @param args
     */
    public static void main(String[] args) {

        CommandHandler commandHandler = new CommandHandler();
        commandHandler.handleUserInput();
    }
}
