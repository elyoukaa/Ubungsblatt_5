package five.model;
import five.commands.CommandHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/**
 * this class represents a player.
 * it contains the players stats received from rolling the dices and important
 * information containing the god-favors.
 * @author uuxxo
 */
public class Player {
    /**
     * playerOneTurn: The string is output when it's the first player's turn.
     */
    public static String playerOneTurn;
    /**
     * playerTwoTurn: The string is output when it's the first player's turn.
     */
    public static String playerTwoTurn;
    /**
     * the number of players playing Orlog.
     */
    public static final int PLAYER_NUMBER = 2;
    /**
     * the default int value to be assigned to an int.
     */
    static final int DEFAULT_INT_VALUE = 0;
    private static final List<Player> PLAYERS = new ArrayList<>();
    /**
     * this HashSet represents the three god-favors a player has selected at the start.
     */
    public HashSet<String> playerGodFavor;
    /**
     * the level of a godfavor of the player.
     * Needs to be used in the evaluate()-method at Thrymrs Theft.
     */
    public int gfLevel;
    String name;
    long hp;
    private int cost;
    private int closeAttack;
    private int rangedAttack;
    private int stealGP;
    private int closeShield;
    private int rangedShield;
    private int godPower;
    private String godfavor;
    private int gfEffect;

    /**
     * this consctructor sets the name and hp of the player to the required value.
     * default values are set to zero.
     * @param name the  string that is the name of the player
     * @param hp the starting hp
     */
    public Player(String name, long hp) {
        this.name = name;
        this.hp = hp;
        rangedAttack = DEFAULT_INT_VALUE;
        closeAttack = DEFAULT_INT_VALUE;
        closeShield = DEFAULT_INT_VALUE;
        rangedShield = DEFAULT_INT_VALUE;
        stealGP = DEFAULT_INT_VALUE;
        playerGodFavor = new HashSet<>();
        gfLevel = 0;
        PLAYERS.add(this);
        if (PLAYERS.size() == 1) {
            playerOneTurn = "OK, " + name + "'s turn";
        } else if (PLAYERS.size() == 2) {
            playerTwoTurn = "OK, " + name + "'s turn";
        }
    }

    /**
     * this method determines which player is to play next and returns the String to be output.
     * @return the String "OK, playername's turn!
     */
    public static String playersTurn() {
        if (CommandHandler.phase % 2 == 0) {
            return playerOneTurn;
        }
        return playerTwoTurn;
    }

    /**
     * Setter: close attack is gained through rolling axe.
     * @param val the amount of close attack the player has.
     */
    public void setCloseAttack(int val) {
        this.closeAttack = val;
    }

    /**
     * Getter to the Setter above.
     * @return the amount of close attack the player has.
     */
    public int getCloseAttack() {
        return this.closeAttack;
    }

    /**
     * Setter: ranged attack is gained through rolling bow.
     * @param val the amount of ranged attack the player has.
     */
    public void setRangedAttack(int val) {
        this.rangedAttack = val;
    }

    /**
     * Getter to the above Setter.
     * @return the amount of ranged attack the player has.
     */
    public int getRangedAttack() {
        return this.rangedAttack;
    }

    /**
     * Setter: close shield is gained through rolling helmet.
     * @param val the amount of close shield the player has.
     */
    public void setCloseShield(int val) {
        this.closeShield = val;
    }

    /**
     * Getter to the above Setter.
     * @return the amount of close shield the player has.
     */
    public int getCloseShield() {
        return this.closeShield;
    }

    /**
     * Setter for ranged shield. Gained through rolling shield.
     * @param val amount of ranged shield.
     */
    public void setRangedShield(int val) {
        this.rangedShield = val;
    }

    /**
     * Getter to the above Setter.
     * @return amount of ranged shield.
     */
    public int getRangedShield() {
        return this.rangedShield;
    }

    /**
     * This method sets the effect-points of the players god-favor, depending on it's level.
     * The points will later be evaluated with their corresponding god-favor.
     * @param val
     */
    public void setGfEffect(int val) {
        this.gfEffect = val;
    }

    /**
     * Getter to the above Setter.
     * @return the amount of god-favor effect-points.
     */
    public int getGfEffect() {
        return this.gfEffect;
    }

    /**
     * this methods changes the god-power the player has. If it does not drop below zero.
     * @param val the value by which the god-power changes. Can be negative.
     * @return whether or not the change has been executed.
     */
    public boolean changeGP(int val) {
        if (this.godPower + val >= 0) {
            this.godPower += val;
            return true;
        }
        return false;
    }

    /**
     * Setter: stealing points are gained by rolling theft.
     * @param val  the amount of stealing points the player has.
     */
    public void setStealingPoints(int val) {
        this.stealGP = val;
    }
    /**
     * Getter to the above Setter.
     * @return amount of stealing points.
     */
    public int getStealingPoints() {
        return this.stealGP;
    }

    /**
     * Get the name of the first player.
     * @return name
     */
    public static Player getPlayerOne() {
        return PLAYERS.getFirst();
    }

    /**
     * Get the name of the second player.
     * @return name
     */
    public static Player getPlayerTwo() {
        return PLAYERS.getLast();
    }

    /**
     * get the name of the player that is using this method.
     * @return player-name
     */
    public String getName() {
        return this.name;
    }

    /**
     * this method returns the amount of hp a player has.
     * @return hp
     */
    public long getHP() {
        return this.hp;
    }

    /**
     * method to get the amount of god-power the player has.
     * @return god-power
     */
    public int getGodPower() {
        return this.godPower;
    }

    /**
     * Setter: sets the god-favor of a player.
     * According to the tags specified on the exercise sheet.
     * @param godfavor the tag of the god-favor
     */
    public void setGodfavor(String godfavor) {
        this.godfavor = godfavor;
    }

    /**
     * Getter: gets the String-tag of the god-favor of the player.
     * @return the player's god-favor's tag.
     */
    public String getGodfavor() {
        return this.godfavor;
    }

    /**
     * set the cost the player has to pay to use the god-favor.
     * @param cost the amount of cost
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * gets the amount of cost the player has to use.
     * @return cost
     */
    public int getCost() {
        return this.cost;
    }

    /**
     * changes the  hp of the player by the specified amount.
     * @param change can be negative
     */
    public void changeHP(int change) {
        this.hp += change;
    }
}
