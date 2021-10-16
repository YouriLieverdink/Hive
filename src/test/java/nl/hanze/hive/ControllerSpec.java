package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerSpec {
    @Test
    public void whenNewControllerWhenPlayerWhiteThenTrue() {
        Controller c = new Controller();

        assertTrue(c.getTurn() == Hive.Player.WHITE);
    }

    @Test
    public void whenNewControllerThenBlackStartingHand() {
        Controller c = new Controller();
        Hand h = new Hand(Hive.Player.BLACK);

        assertTrue(c.getBlack().equals(h));
    }

    @Test
    public void whenNewControllerThenWhiteStartingHand() {
        Controller c = new Controller();
        Hand h = new Hand(Hive.Player.WHITE);

        assertTrue(c.getWhite().equals(h));
    }

    @Test
    public void givenTileAndPositionWhenRemainingThenContinue() {
        Controller c = new Controller();

        assertDoesNotThrow(() -> c.play(Hive.Tile.QUEEN_BEE, 0, 0));
    }

    @Test
    public void givenTileAndPositionWhenGoneThenIllegalMove() {
        Controller c = new Controller();
        c.getWhite().remove(new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));

        assertThrows(Hive.IllegalMove.class, () -> c.play(Hive.Tile.QUEEN_BEE, 0, 0));
    }
}
