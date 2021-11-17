package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class BoardCheck {
	@Test
	// Requirement 2a.
	void givenPositionStoneWhenRemovedThenTrue() {
		Board b1 = new Board();
		Position p1 = new Position(-1, 1);
		Stone s1 = new Stone(Player.WHITE);

		b1.add(p1, s1);

		assertNotNull(b1.remove(p1));
	}

	@Test
	// Requirement 2a.
	void whenRemovedThenFalse() {
		Board b1 = new Board();
		Position p1 = new Position(-1, 1);

		assertNull(b1.remove(p1));
	}

	@Test
	// Requirement 2a.
	void whenRemovedTwiceThenEqual() {
		Board b1 = new Board();
		Position p1 = new Position(0, 0);
		Stone s1 = new Stone(Player.WHITE, Tile.BEETLE);
		Stone s2 = new Stone(Player.WHITE, Tile.GRASSHOPPER);
		b1.add(p1, s1);
		b1.add(p1, s2);

		b1.remove(p1);

		assertEquals(s1, b1.remove(p1));
	}

	@Test
	// Requirement 3c.
	void givenPositionWhenNullThenTrue() {
		Board b1 = new Board();
		Position p1 = new Position(-1, 1);

		assertNull(b1.getStone(p1));
	}

	@Test
	// Requirement 3c.
	void givenPositonWhenNullThenFalse() {
		Board b1 = new Board();
		Position p1 = new Position(-1, 1);
		Stone s1 = new Stone(Player.WHITE);

		b1.add(p1, s1);

		assertNotNull(b1.getStone(p1));
	}

	@Test
	// Requirement 4e.
	void givenPlayerWhenEqualsOneThenTrue() {
		Board b1 = new Board();
		b1.add(new Position(0, 1), new Stone(Player.WHITE));
		b1.add(new Position(4, -2), new Stone(Player.BLACK));

		assertEquals(1, b1.getNumberOfStones(Player.WHITE));
	}

	@Test
	// Requirement 5d.
	void whenConnectedThenTrue() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.WHITE));
		b1.add(new Position(1, 0), new Stone(Player.WHITE));
		b1.add(new Position(2, 0), new Stone(Player.WHITE));

		assertTrue(b1.isConnected());
	}

	@Test
	// Requirement 5d.
	void whenNotConnectedThenFalseOne() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.WHITE));
		b1.add(new Position(2, 0), new Stone(Player.WHITE));

		assertFalse(b1.isConnected());
	}

	@Test
	// Requirement 6b.
	void givenPositionWhenNumberOfStonesEqualThenTrue() {
		Board b1 = new Board();
		Position p1 = new Position(0, 0);
		b1.add(p1, new Stone(Player.WHITE));
		b1.add(p1, new Stone(Player.WHITE));

		assertEquals(2, b1.getNumberOfStonesOnPosition(p1));
	}
}
