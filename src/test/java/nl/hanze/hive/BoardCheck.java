package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

		assertTrue(b1.remove(p1));
	}

	@Test
	// Requirement 2a.
	void whenRemovedThenFalse() {
		Board b1 = new Board();
		Position p1 = new Position(-1, 1);

		assertFalse(b1.remove(p1));
	}

	@Test
	// Requirement 3c.
	void givenPositionWhenEmptyThenTrue() {
		Board b1 = new Board();
		Position p1 = new Position(-1, 1);

		assertTrue(b1.isEmpty(p1));
	}

	@Test
	// Requirement 3c.
	void givenPositonWhenNotEmptyThenFalse() {
		Board b1 = new Board();
		Position p1 = new Position(-1, 1);
		Stone s1 = new Stone(Player.WHITE);

		b1.add(p1, s1);

		assertFalse(b1.isEmpty(p1));
	}
}
