package five.commands;
import five.commands.godfavor.Godfavour;
import five.model.Player;

/**
 * objects of this class execute the evaluate command.
 * @author uuxxo
 */
public class EvaluateCommand implements Command {
    static final String[] MOVE_NAMES = { "TT", "TS", "IR", "HW", "VB", "MW" };
    private static int argumentNumber = 0;
    private static int thorsHammerDamage;
    Player firstPlayer;
    Player secondPlayer;

    /**
     * determines which player uses godfavor first.
     * @param playerOne player that started the game.
     * @param playerTwo second player.
     */
    public void godFavorOrder(Player playerOne, Player playerTwo) {
        for (int i = 0; i < MOVE_NAMES.length; i++) {
            if (playerOne.getGodfavor().equals(MOVE_NAMES[i])) {
                firstPlayer = playerOne;
                secondPlayer = playerTwo;
                break;
            } else if (playerTwo.getGodfavor().equals(MOVE_NAMES[i])) {
                firstPlayer = playerTwo;
                secondPlayer = playerOne;
                break;
            }
        }
    }

    @Override
    public int getArgumentNumber() {
        return argumentNumber;
    }

    /**
     * simulates the changes of using the fight-elements from rolling the dices.
     * @param sam a player
     * @param eddie the other player
     */
    private void evaluateDices(Player sam, Player eddie) {
        int theftS = sam.getStealingPoints();
        int theftE = eddie.getStealingPoints();
        theftS -= theftE;
        theftE -= (theftS + theftE);
        if (theftS > 0 && eddie.getGodPower() > 0) {
            sam.changeGP(Math.min(theftS, eddie.getGodPower()));
            eddie.changeGP(Math.min(theftS, eddie.getGodPower()) * (-1));
            System.out.println("ZUI");
        } else if (theftE > 0 && sam.getGodPower() > 0) {
            eddie.changeGP(Math.min(theftE, sam.getGodPower()));
            sam.changeGP(Math.min(theftE, sam.getGodPower()) * (-1));
        }
        int damageReceivedS = damage(eddie.getCloseAttack(), sam.getCloseShield())
                + damage(eddie.getRangedAttack(), sam.getRangedShield());
        sam.changeHP(damageReceivedS * (-1));
        int damageReceivedE = damage(sam.getCloseAttack(), eddie.getCloseShield())
                + damage(sam.getRangedAttack(), eddie.getRangedShield());
        eddie.changeHP(damageReceivedE * (-1));
    }
    private int damage(int attack, int defense) {
        if (defense > attack) {
            return 0;
        }
        return attack - defense;
    }
    private void useGodfavor(Player attacker, Player defender) {
        if (attacker.getCost() > attacker.getGodPower()) {
            return;
        }
        if (attacker.getHP() <= 0 && !(attacker.getGodfavor()).equals("TS")) {
            return;
        }
        attacker.changeGP(attacker.getCost() * (-1));
        switch (attacker.getGodfavor()) {
            case "TT":
                if (attacker.gfLevel >= defender.gfLevel) {
                    defender.setGodfavor("turn");
                } else {
                    Godfavour.godfavors.get(defender.getGodfavor()).execute(defender, defender.gfLevel - attacker.gfLevel);
                }
            case "TS":
                defender.changeHP(attacker.getGfEffect() * (-1));
                thorsHammerDamage = attacker.getGfEffect();
                break;
            case "IR":
                attacker.changeHP(attacker.getGfEffect());
                break;
            case "HW":
                int blockedDamage = Math.min(attacker.getCloseShield(), defender.getCloseAttack())
                        + Math.min(attacker.getRangedShield(), defender.getRangedAttack());
                attacker.changeHP(blockedDamage * attacker.getGfEffect());
                break;
            case "VB":
                if (defender.getCost() > defender.getGodPower()) {
                    break;
                }
                attacker.changeHP(defender.getCost() * attacker.getGfEffect());
            case "MW":
                int damageReceived = damage(defender.getCloseAttack(), attacker.getCloseShield())
                        + damage(defender.getRangedAttack(), attacker.getRangedShield());
                damageReceived += thorsHammerDamage;
                attacker.changeGP(damageReceived * attacker.getGfEffect());
            default:
                break;
        }
    }

    /**
     * resets the player values after the evaluation.
     */
    public void reset() {
        Player playerOne = Player.getPlayerOne();
        Player playerTwo = Player.getPlayerTwo();

        playerOne.setStealingPoints(0);
        playerOne.setCloseShield(0);
        playerOne.setGfEffect(0);
        playerOne.setCloseAttack(0);
        playerOne.setRangedAttack(0);
        playerOne.setRangedShield(0);
        playerOne.setGodfavor("");
        playerOne.gfLevel = 0;

        playerTwo.setStealingPoints(0);
        playerTwo.setCloseShield(0);
        playerTwo.setGfEffect(0);
        playerTwo.setCloseAttack(0);
        playerTwo.setRangedAttack(0);
        playerTwo.setRangedShield(0);
        playerTwo.setGodfavor("");
        playerTwo.gfLevel = 0;
    }

    @Override
    public boolean execute(Player player, String[] commandArguments) {
        if (CommandHandler.phase != 4) {
            OUTPUT_MESSAGE[1] = "illegal command!";
            return false;
        }
        if (commandArguments.length != getArgumentNumber()) {
            OUTPUT_MESSAGE[1] = "illegal number of arguments!";
            return false;
        }
        evaluateDices(Player.getPlayerOne(), Player.getPlayerTwo());
        godFavorOrder(Player.getPlayerOne(), Player.getPlayerTwo());
        useGodfavor(firstPlayer, secondPlayer);
        useGodfavor(secondPlayer, firstPlayer);
        reset();
        PrintCommand printCommand = new PrintCommand();
        String[] arr = new String[0];
        printCommand.execute(player, arr);
        OUTPUT_MESSAGE[0] += "\n";
        if (Player.getPlayerOne().getHP() > 0 && Player.getPlayerOne().getHP() > 0) {
            OUTPUT_MESSAGE[0] += Player.playerOneTurn;
        } else if (Player.getPlayerOne().getHP() <= 0 && Player.getPlayerTwo().getHP() <= 0) {
            OUTPUT_MESSAGE[0] += "draw";
        } else if (Player.getPlayerOne().getHP() <= 0) {
            OUTPUT_MESSAGE[0] += Player.getPlayerTwo().getName() + " wins!";
        } else if (Player.getPlayerTwo().getHP() <= 0) {
            OUTPUT_MESSAGE[0] += Player.getPlayerOne().getName() + " wins!";
        }
        CommandHandler.phase = 0;
        return true;
    }
}
