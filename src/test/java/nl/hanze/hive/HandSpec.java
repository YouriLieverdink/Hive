package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class HandSpec {
    @Test
    // Add stone to hand
    void addStoneToHandWhenNotSameThenFalse() {
        Hand hand = new Hand(Player.BLACK);
        Stone queenBee = new Stone(Player.BLACK, Tile.QUEEN_BEE);
        hand.add(queenBee, 1);

        assertNotSame(hand.getStones().get(0).getTile(), Tile.SPIDER);
    }

    @Test
    // Add stone to hand
    void addStoneToHandWhenSameThenTrue() {
        Hand hand = new Hand(Player.BLACK);
        Stone queenBee = new Stone(Player.BLACK, Tile.QUEEN_BEE);
        hand.add(queenBee, 1);

        assertSame(hand.getStones().get(0).getTile(), Tile.QUEEN_BEE);
    }

    @Test
    // Requirements 1c.
    void correctStartingHandWhenSameThenTrue() {
        Hand hand = new Hand(Player.BLACK);
        ArrayList<Tile> tiles = hand.getTileNames();

        assertSame(Collections.frequency(tiles, Tile.QUEEN_BEE), 1);
        assertSame(Collections.frequency(tiles, Tile.SPIDER), 2);
        assertSame(Collections.frequency(tiles, Tile.BEETLE), 2);
        assertSame(Collections.frequency(tiles, Tile.GRASSHOPPER), 3);
        assertSame(Collections.frequency(tiles, Tile.SOLDIER_ANT), 3);
    }
}
