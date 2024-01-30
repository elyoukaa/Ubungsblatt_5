package Exercise_5.model;
import Exercise_5.Commands.CommandHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
public class Player {
        final static int DEFAULT_INT_VALUE = 0;
        public HashSet<String> playerGodFavor;
        public static String playerOneTurn;
        public static String playerTwoTurn;
        String name;
        long hp;
        public static final int playerNumber = 2;
        private static final List<Player> players = new ArrayList<>();
        private int cost;
        private int closeAttack;
        private int rangedAttack;
        private int stealGP;
        private int closeShield;
        private int rangedShield;
        private int godPower;

        private String godfavor;
        private int gfEffect;
        private int gfLevel;
        public Player(String name, long hp) {
                this.name = name;
                this.hp = hp;
                rangedAttack = DEFAULT_INT_VALUE;
                closeAttack = DEFAULT_INT_VALUE;
                closeShield = DEFAULT_INT_VALUE;
                rangedShield = DEFAULT_INT_VALUE;
                stealGP = DEFAULT_INT_VALUE;
                playerGodFavor = new HashSet<>();
                players.add(this);
                if (players.size() == 1) {
                        playerOneTurn = "OK, " + name + "'s turn";
                }
                else if (players.size() == 2) {
                        playerTwoTurn = "OK, " + name + "'s turn";
                }
        }
        public static String playersTurn() {
                if (CommandHandler.phase % 2 == 0) {
                        return playerOneTurn;
                }
                return playerTwoTurn;
        }
        public void setCloseAttack(int val) {
             this.closeAttack = val;
        }
        public int getCloseAttack() {
                return this.closeAttack;
        }

        public void setRangedAttack(int val) {
                this.rangedAttack = val;
        }
        public int getRangedAttack() {
                return this.rangedAttack;
        }

        public void setCloseShield(int val) {
                this.closeShield = val;
        }
        public int getCloseShield() {
                return this.closeShield;
        }
        public void setRangedShield(int val) {
                this.rangedShield = val;
        }
        public int getRangedShield() {
                return this.rangedShield;
        }
        public void setGfEffect(int val) {
                this.gfEffect = val;
        }
        public int getGfEffect() {
                return this.gfEffect;
        }
        public boolean changeGP(int val) {
                if (this.godPower + val >= 0) {
                        this.godPower += val;
                        return true;
                }
                return false;
        }
        public int getStealingPoints() {
                return this.stealGP;
        }
        public void setStealingPoints(int val) {
                this.stealGP = val;
        }
        public static Player getPlayerOne() {
                return players.getFirst();
        }
        public static Player getPlayerTwo() {
                return players.getLast();
        }
        public String getName() {
                return this.name;
        }
        public long getHP() {
                return this.hp;
        }
        public int getGodPower() { return this.godPower; }
        public void setGodfavor(String godfavor) { this.godfavor = godfavor; }
        public String getGodfavor() { return this.godfavor; }
        public void setCost(int cost) { this.cost = cost; }
        public int getCost() { return this.cost; }
        public void setGfLevel(int gfLevel) { this.gfLevel = gfLevel; }
        public int getGfLevel() { return this.gfLevel; }
        public void changeHP(int change) {
                this.hp += change;
        }
}
