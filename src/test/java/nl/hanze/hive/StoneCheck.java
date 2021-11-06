package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class StoneCheck {
	@Test
	// Requirement 1a.
	void givenSamePlayerThenEqual() {
		Stone s1 = new Stone(Player.WHITE);
		Stone s2 = new Stone(Player.WHITE);

		assertEquals(s1, s2);
	}

	@Test
	// Requirement 1a.
	void givenDifferentPlayerThenNotEqual() {
		Stone s1 = new Stone(Player.WHITE);
		Stone s2 = new Stone(Player.BLACK);

		assertNotEquals(s1, s2);
	}

	@Test
	// Requirement 1b.
	void givenSameTileThenEqual() {
		Stone s1 = new Stone(Player.WHITE, Tile.BEETLE);
		Stone s2 = new Stone(Player.WHITE, Tile.BEETLE);

		assertEquals(s1, s2);
	}

	@Test
	// Requirement 1b.
	void givenDifferentTileThenNotEqual() {
		Stone s1 = new Stone(Player.WHITE, Tile.BEETLE);
		Stone s2 = new Stone(Player.BLACK, Tile.GRASSHOPPER);

		assertNotEquals(s1, s2);
	}

	@Test
	// Requirement 4e.
	void givenPlayerWhenBelongsToThenTrue() {
		Stone s1 = new Stone(Player.WHITE);

		assertTrue(s1.belongsTo(Player.WHITE));
	}
}
