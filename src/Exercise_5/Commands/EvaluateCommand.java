package Exercise_5.Commands;
import Exercise_5.Commands.Godfavor.Godfavour;
import Exercise_5.Commands.Godfavor.ThrymrsTheft;
import Exercise_5.model.Player;
public class EvaluateCommand implements Command{
    static final String[] moveNames = { "TT", "TS", "IR", "HW", "VB", "MW" };
    private static int argumentNumber = 0;
    private static int thorsHammerDamage;
    String SUCCESS_MESSAGE;
    String FAILURE_MESSAGE;
    Player firstPlayer;
    Player secondPlayer;

    public void godFavorOrder (Player playerOne, Player playerTwo) {
        for (int i = 0; i < moveNames.length; i++) {
            if (playerOne.getGodfavor().equals(moveNames[i])) {
                firstPlayer = playerOne;
                secondPlayer = playerTwo;
                break;
            }
            else if (playerTwo.getGodfavor().equals(moveNames[i])) {
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
     * @param Sam a player
     * @param Eddie the other player
     */
    private void evaluateDices(Player Sam, Player Eddie) {
        int theftS = Sam.getStealingPoints();
        int theftE = Eddie.getStealingPoints();
        theftS -= theftE;
        theftE -= (theftS + theftE);
        if (theftS > 0 && Eddie.getGodPower() > 0) {
            Sam.changeGP(Math.min(theftS, Eddie.getGodPower()));
        } else if (theftE > 0 && Sam.getGodPower() > 0) {
            Eddie.changeGP(Math.min(theftE, Sam.getGodPower()));
        }
        int damageReceivedS = damage(Eddie.getCloseAttack(), Sam.getCloseShield())
                + damage(Eddie.getRangedAttack(), Sam.getRangedShield());
        Sam.changeHP(damageReceivedS * (-1));
        int damageReceivedE = damage(Sam.getCloseAttack(), Eddie.getCloseShield())
                + damage(Sam.getRangedAttack(), Eddie.getRangedShield());
        Eddie.changeHP(damageReceivedS * (-1));
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
        switch (attacker.getGodfavor()) {
            case "TT":
                if (attacker.getGfLevel() >= defender.getGfLevel()) {
                    defender.setGodfavor("turn");
                } else {
                    ThrymrsTheft thief = new ThrymrsTheft();
                    thief.execute(defender, defender.getGfLevel() - attacker.getGfLevel());
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
            case "turn":
                break;
        }
    }
    public void reset() {
        Player playerOne = Player.getPlayerOne();
        Player playerTwo = Player.getPlayerTwo();

        playerOne.setStealingPoints(0);
        playerOne.setCloseShield(0);
        playerOne.setGfEffect(0);
        playerOne.setCloseAttack(0);
        playerOne.setRangedAttack(0);
        playerOne.setRangedShield(0);;
        playerOne.setGodfavor("");

        playerTwo.setStealingPoints(0);
        playerTwo.setCloseShield(0);
        playerTwo.setGfEffect(0);
        playerTwo.setCloseAttack(0);
        playerTwo.setRangedAttack(0);
        playerTwo.setRangedShield(0);;
        playerTwo.setGodfavor("");
    }

    @Override
    public boolean execute(Player player, String[] commandArguments) {
        if (commandArguments.length == 0) {
            FAILURE_MESSAGE = "illegal number of arguments!";
            return false;
        }
        evaluateDices(Player.getPlayerOne(), Player.getPlayerTwo());
        godFavorOrder(Player.getPlayerOne(), Player.getPlayerOne());
        useGodfavor(firstPlayer, secondPlayer);
        useGodfavor(secondPlayer, firstPlayer);
        reset();
        if (Player.getPlayerOne().getHP() > 0 && Player.getPlayerOne().getHP() > 0) {
            SUCCESS_MESSAGE = Player.playerOneTurn;
        } else if (Player.getPlayerOne().getHP() <= 0 && Player.getPlayerTwo().getHP() <= 0) {
            SUCCESS_MESSAGE = "draw";
        } else if (Player.getPlayerOne().getHP() <= 0) {
            SUCCESS_MESSAGE = Player.getPlayerTwo().getName() + " wins!";
        } else if (Player.getPlayerTwo().getHP() <= 0) {
            SUCCESS_MESSAGE = Player.getPlayerOne().getName() + " wins!";
        }
        CommandHandler.phase = 0;
        return true;
    }
}
