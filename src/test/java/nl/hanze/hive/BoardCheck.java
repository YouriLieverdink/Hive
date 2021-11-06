package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;

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
}
