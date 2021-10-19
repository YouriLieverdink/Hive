package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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

    @Test
    public void givenTileAndPositionWhenPositionNotEmptyThenIllegalMove() {
        Map<Position, List<Stone>> positions = Map.of(
                new Position(0, 0),
                List.of(new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE))
        );

        Board b = new Board(positions);
        Controller c = new Controller(b);

        try {
            c.play(Hive.Tile.QUEEN_BEE, 0, 0);

            assertThrows(Hive.IllegalMove.class, () -> c.play(Hive.Tile.BEETLE, 0, 0));
        }
        catch (Hive.IllegalMove e) {

        }

    }

    @Test
    public void givenPlayerWhenWinnerThenTrue() {
        Map<Position, List<Stone>> positions = Map.of(
                new Position(0, 0),
                List.of(new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE)),
                // Surround the queen bee with tiles.
                new Position(1, -1),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE)),
                new Position(1, 0),
                List.of(new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER)),
                new Position(0, 1),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)),
                new Position(-1, 1),
                List.of(new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER)),
                new Position(-1, 0),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER)),
                new Position(0, -1),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE))
        );

        Board b = new Board(positions);
        Controller c = new Controller(b);

        assertTrue(c.isWinner(Hive.Player.WHITE));
    }

    @Test
    public void whenBlackIsWinnerAndWhiteIsWinnerThenTrue() {
        Map<Position, List<Stone>> positions = Map.of(
                // The queen bees.
                new Position(0, 0),
                List.of(new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE)),
                new Position(0, 1),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE)),
                // The surrounding tiles.
                new Position(1, -1),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE)),
                new Position(1, 0),
                List.of(new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER)),
                new Position(1, 1),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT)),
                new Position(0, 2),
                List.of(new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER)),
                new Position(-1, 2),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER)),
                new Position(-1, 1),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER)),
                new Position(-1, 0),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER)),
                new Position(0, -1),
                List.of(new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE))
        );

        Board b = new Board(positions);
        Controller c = new Controller(b);

        assertTrue(c.isDraw());
    }
}
