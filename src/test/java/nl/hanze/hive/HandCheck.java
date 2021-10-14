package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

import static org.junit.jupiter.api.Assertions.*;

public class HandCheck {
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
    void correctStartingHandHandWhenSameThenTrue() {
        Hand hand = new Hand(Player.BLACK);

        hand.startingHand();

        assertSame(hand.getAmountOfSpecificStone(Tile.QUEEN_BEE), 1);
        assertSame(hand.getAmountOfSpecificStone(Tile.SPIDER), 2);
        assertSame(hand.getAmountOfSpecificStone(Tile.BEETLE), 2);
        assertSame(hand.getAmountOfSpecificStone(Tile.GRASSHOPPER), 3);
        assertSame(hand.getAmountOfSpecificStone(Tile.SOLDIER_ANT), 3);
    }
}
