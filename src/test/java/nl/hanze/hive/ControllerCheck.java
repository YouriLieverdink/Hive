package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class ControllerCheck {
	@Test
	// Requirement 3a.
	void givenTileAndPositionWhenWhiteStoneThenTrue() {
		Board b1 = new Board();
		Controller c1 = new Controller(b1);
		Stone s1 = new Stone(Player.WHITE, Tile.QUEEN_BEE);

		try {

			c1.play(Tile.QUEEN_BEE, 0, 0);
		} //
		catch (Exception e) {
		}

		assertEquals(b1.getStone(new Position(0, 0)), s1);
	}

	@Test
	// Requirement 3b.
	void whenPlayCompletedThenNextPlayer() {
		Board b1 = new Board();
		Controller c1 = new Controller(b1);
		Stone s1 = new Stone(Player.BLACK, Tile.QUEEN_BEE);

		try {

			c1.play(Tile.QUEEN_BEE, 0, 0);
			c1.play(Tile.QUEEN_BEE, 0, 1);

		} //
		catch (Exception e) {
		}

		assertEquals(b1.getStone(new Position(0, 1)), s1);
	}

	@Test
	// Requirement 3b.
	void whenMoveCompletedThenNextPlayer() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.WHITE, Tile.QUEEN_BEE));
		Controller c1 = new Controller(b1);
		Stone s1 = new Stone(Player.BLACK, Tile.QUEEN_BEE);

		try {

			c1.move(0, 0, 0, 1);
			c1.play(Tile.QUEEN_BEE, 1, 0);
		} //
		catch (Exception e) {
			System.out.println(e.toString());
		}

		assertEquals(s1, b1.getStone(new Position(1, 0)));
	}

	@Test
	// Requirement 3b.
	void whenPassCompletedThenNextPlayer() {
		Board b1 = new Board();
		Controller c1 = new Controller(b1);
		Stone s1 = new Stone(Player.BLACK, Tile.QUEEN_BEE);

		try {

			c1.pass();
			c1.play(Tile.QUEEN_BEE, 0, 0);
		} //
		catch (Exception e) {
		}

		assertEquals(s1, b1.getStone(new Position(0, 0)));
	}

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
	// Requirement 3d.
	void givenPlayerWhenNoQueenBeeThenFalse() {
		Controller c1 = new Controller();

		assertFalse(c1.isWinner(Player.WHITE));
	}

	@Test
	// Requirement 4a.
	void givenTileAndPositionWhenUnavailableThenIllegalMove() {
		Controller c1 = new Controller();

		try {

			c1.play(Tile.QUEEN_BEE, 0, 0);
			c1.play(Tile.QUEEN_BEE, 0, 1);
		} //
		catch (Exception e) {
		}

		assertThrows(Hive.IllegalMove.class, () -> c1.play(Tile.QUEEN_BEE, 1, 0));
	}

	@Test
	// Requirement 4b.
	void givenTileAndPositionWhenTakenThenIllegalMove() {
		Controller c1 = new Controller();

		try {

			c1.play(Tile.QUEEN_BEE, 0, 0);
		} //
		catch (Exception e) {
		}

		assertThrows(Hive.IllegalMove.class, () -> c1.play(Tile.QUEEN_BEE, 0, 0));
	}

	@Test
	// Requirement 4c.
	void givenTileAndPositionWhenNoNeighboursThenIllegalMove() {
		Controller c1 = new Controller();

		try {

			c1.play(Tile.QUEEN_BEE, 0, 0);
		} //
		catch (Exception e) {
		}

		assertThrows(Hive.IllegalMove.class, () -> c1.play(Tile.QUEEN_BEE, 2, 2));
	}

	@Test
	// Requirement 4c.
	void givenTileAndPositionWhenNeighboursThenNothing() {
		Controller c1 = new Controller();

		try {

			c1.play(Tile.QUEEN_BEE, 0, 0);
		} //
		catch (Exception e) {
		}

		assertDoesNotThrow(() -> c1.play(Tile.QUEEN_BEE, 1, 0));
	}

	@Test
	// Requirement 4e.
	void givenTileAndPositionWhenFourthPlayAndNotQueenBeeThenIllegalMove() {
		Board b1 = new Board();

		b1.add(new Position(0, 0), new Stone(Player.BLACK, Tile.SOLDIER_ANT));
		b1.add(new Position(1, 0), new Stone(Player.WHITE, Tile.BEETLE));
		b1.add(new Position(0, -1), new Stone(Player.BLACK, Tile.QUEEN_BEE));
		b1.add(new Position(2, -1), new Stone(Player.WHITE, Tile.SOLDIER_ANT));
		b1.add(new Position(0, -2), new Stone(Player.BLACK, Tile.BEETLE));
		b1.add(new Position(2, -2), new Stone(Player.WHITE, Tile.SPIDER));
		b1.add(new Position(-1, -1), new Stone(Player.BLACK, Tile.BEETLE));

		Controller c1 = new Controller(b1);

		assertThrows(Hive.IllegalMove.class, () -> c1.play(Tile.SOLDIER_ANT, 2, -3));
	}

	@Test
	// Requirement 4d.
	void givenTileAndPositionWhenNeighbourIsOpponentAndNotOnlyStoneThenIllegalMove() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.BLACK, Tile.QUEEN_BEE));
		b1.add(new Position(0, 1), new Stone(Player.WHITE, Tile.QUEEN_BEE));
		Controller c1 = new Controller(b1);

		assertThrows(Hive.IllegalMove.class, () -> c1.play(Tile.SOLDIER_ANT, 0, -1));
	}

	@Test
	// Requirement 4d.
	void givenTileAndPositionWhenNeighbourIsOpponentAndOnlyStoneThenNothing() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.BLACK, Tile.QUEEN_BEE));
		Controller c1 = new Controller(b1);

		assertDoesNotThrow(() -> c1.play(Tile.QUEEN_BEE, 0, 1));
	}

	@Test
	// Requirement 5b.
	void givenFromAndToPositionsWhenQueenBeeNotPlayedThenIllegalMove() {
		Controller c1 = new Controller();

		assertThrows(Hive.IllegalMove.class, () -> c1.move(0, 0, 0, 1));
	}

	@Test
	// Requirement 5a.
	void givenFromAndToPostionWhenNotStoneOwnerThenIllegalMove() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.WHITE, Tile.QUEEN_BEE));
		b1.add(new Position(0, -1), new Stone(Player.BLACK, Tile.SOLDIER_ANT));
		b1.add(new Position(1, 0), new Stone(Player.WHITE, Tile.GRASSHOPPER));
		b1.add(new Position(1, -2), new Stone(Player.BLACK, Tile.QUEEN_BEE));
		Controller c1 = new Controller(b1);

		assertThrows(Hive.IllegalMove.class, () -> c1.move(1, -2, 0, -2));
	}

	@Test
	// Requirement 5d.
	void givenFromAndToPositionsWhenHiveBreaksThenIllegalMove() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.WHITE, Tile.GRASSHOPPER));
		b1.add(new Position(0, -1), new Stone(Player.BLACK, Tile.SPIDER));
		b1.add(new Position(1, 0), new Stone(Player.WHITE, Tile.QUEEN_BEE));
		b1.add(new Position(-1, -1), new Stone(Player.BLACK, Tile.QUEEN_BEE));
		Controller c1 = new Controller(b1);

		assertThrows(Hive.IllegalMove.class, () -> c1.move(0, 0, 1, -1));
	}
}
