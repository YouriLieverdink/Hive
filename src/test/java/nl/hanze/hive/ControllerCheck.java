package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class ControllerCheck {
	@Test
	// Requirement 3c.
	void givenPlayerWhenWinnerThenTrue() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.WHITE, Tile.QUEEN_BEE));
		b1.add(new Position(0, -1), new Stone(Player.BLACK));
		b1.add(new Position(1, -1), new Stone(Player.BLACK));
		b1.add(new Position(1, 0), new Stone(Player.BLACK));
		b1.add(new Position(0, 1), new Stone(Player.BLACK));
		b1.add(new Position(-1, 1), new Stone(Player.BLACK));
		b1.add(new Position(-1, 0), new Stone(Player.BLACK));
		Controller c1 = new Controller(b1);

		assertTrue(c1.isWinner(Player.BLACK));
	}

	@Test
	// Requirement 3d.
	void whenBlackIsWinnerAndWhiteIsWinnerThenTrue() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.WHITE, Tile.QUEEN_BEE));
		b1.add(new Position(0, -1), new Stone(Player.BLACK, Tile.QUEEN_BEE));
		b1.add(new Position(-1, -1), new Stone(Player.BLACK));
		b1.add(new Position(0, -2), new Stone(Player.BLACK));
		b1.add(new Position(1, -2), new Stone(Player.BLACK));
		b1.add(new Position(1, -1), new Stone(Player.BLACK));
		b1.add(new Position(1, 0), new Stone(Player.BLACK));
		b1.add(new Position(0, 1), new Stone(Player.BLACK));
		b1.add(new Position(-1, 1), new Stone(Player.BLACK));
		b1.add(new Position(-1, 0), new Stone(Player.BLACK));
		Controller c1 = new Controller(b1);

		assertTrue(c1.isDraw());
	}

	@Test
	// Requirement 4a.
	void givenTileAndPositionWhenUnavailableThenIllegalMove() {
		Controller c1 = new Controller();

		try {

			c1.play(Tile.QUEEN_BEE, 0, 0);
			c1.play(Tile.QUEEN_BEE, 0, 1);

			assertThrows(Hive.IllegalMove.class, () -> c1.play(Tile.QUEEN_BEE, 1, 0));

		} //
		catch (Exception e) {
		}
	}

	@Test
	// Requirement 4b.
	void givenTileAndPositionWhenTakenThenIllegalMove() {
		Controller c1 = new Controller();

		try {

			c1.play(Tile.QUEEN_BEE, 0, 0);

			assertThrows(Hive.IllegalMove.class, () -> c1.play(Tile.QUEEN_BEE, 0, 0));
		} //
		catch (Exception e) {
		}
	}
}
