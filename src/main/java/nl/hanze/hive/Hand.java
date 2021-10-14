package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Collections;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class Hand {
    /**
     * The player to which the hand belongs.
     */
    private final Player player;

    /**
     * The hand that contains all the Stones of the player.
     */
    private ArrayList<Stone> stones;

    /**
     * Class constructor which specifies the player and stones.
     *
     * @param player The player.
     */
    public Hand(Player player) {
        this.player = player;
        this.stones = new ArrayList<Stone>();
    }

    /**
     * Adds a specific stone a certain amount of times to the Hand.
     *
     * @param stone The stone.
     * @param amount number of times the stone has to be added
     */
    public void add(Stone stone, int amount){
        for (int i = 0; i < amount; i++) {
            stones.add(stone);
        }
    }

    /**
     * Removes a specific stone from the Hand.
     *
     * @param stone The stone.
     */
    public void remove(Stone stone){
        stones.remove(stone);
    }

    /**
     * Removes all stones from the Hand.
     */
    public void clear(){
        stones.clear();
    }


    /**
     * Fill the Hand with all starting stones.
     */
    public void startingHand(){
        this.clear();
        this.add(new Stone(this.player, Tile.QUEEN_BEE), 1);
        this.add(new Stone(this.player, Tile.SPIDER), 2);
        this.add(new Stone(this.player, Tile.BEETLE), 2);
        this.add(new Stone(this.player, Tile.GRASSHOPPER), 3);
        this.add(new Stone(this.player, Tile.SOLDIER_ANT), 3);
    }

    /**
     * Returns list with only tile names from the stones that Hand has
     *
     * @return tileNames List of tile names
     */
    private ArrayList<Tile> listOfTiles(){
        ArrayList<Tile> tileNames = new ArrayList<>();
        for (Stone tile : stones){
            tileNames.add(tile.getTile());
        }
        return tileNames;
    }

    /**
     * Fill the Hand with all starting stones.
     *
     * @return number of stones if none return False
     */
    public int getAmountOfSpecificStone(Tile tile){
        return Collections.frequency(listOfTiles(), tile);
    }

    /**
     * Get the list of stones
     *
     * @return stones The list of stones
     */
    public ArrayList<Stone> getStones() {
        return stones;
    }
}
