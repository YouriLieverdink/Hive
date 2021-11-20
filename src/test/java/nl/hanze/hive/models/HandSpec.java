package nl.hanze.hive.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class HandSpec {
	@Test
	// Requirement 1c.
	void givenStoneWhenStoneRemovedThenTrue() {
		Hand h1 = new Hand();
		Stone s1 = new Stone(Player.WHITE);

		h1.add(s1);

		assertTrue(h1.remove(s1));
	}

	@Test
	// Requirement 1c.
	void whenStoneRemovedThenFalse() {
		Hand h1 = new Hand();
		Stone s1 = new Stone(Player.WHITE);

		assertFalse(h1.remove(s1));
	}

	@Test
	// Requirement 1c.
	void givenStoneAndIntegerWhenStoneRemovedThenTrue() {
		Hand h1 = new Hand();
		Stone s1 = new Stone(Player.WHITE);

		h1.add(s1, 2);
		h1.remove(s1);

		assertTrue(h1.remove(s1));
	}

	@Test
	// Requirement 1c.
	void givenPlayerWhenStoneRemovedThenTrue() {
		Hand h1 = new Hand();
		Stone s1 = new Stone(Player.WHITE, Tile.QUEEN_BEE);
		h1.add(s1);

		assertTrue(h1.remove(new Stone(Player.WHITE, Tile.QUEEN_BEE)));
	}

	@Test
	void whenEmptyThenTrue() {
		Hand h1 = new Hand();
		Stone s1 = new Stone(Player.WHITE);
		h1.add(s1);
		h1.remove(s1);

		assertTrue(h1.isEmpty());
	}
}
