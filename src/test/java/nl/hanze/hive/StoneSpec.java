package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class StoneSpec {
	@Test
	// Requirements 1a.
	void givenSamePlayerWhenEqualsThenTrue() {
		Stone a = new Stone(Player.BLACK);
		Stone b = new Stone(Player.BLACK);

		assertTrue(a.equals(b));
	}

	@Test
	// Requirements 1a.
	void givenDifferentPlayerWhenEqualsThenFalse() {
		Stone a = new Stone(Player.BLACK);
		Stone b = new Stone(Player.WHITE);

		assertFalse(a.equals(b));
	}

	@Test
	// Requirements 1b.
	void givenSameTileWhenEqualsThenTrue() {
		Stone a = new Stone(Player.BLACK, Tile.BEETLE);
		Stone b = new Stone(Player.BLACK, Tile.BEETLE);

		assertTrue(a.equals(b));
	}

	@Test
	// Requirements 1b.
	void givenDifferentTileWhenEqualsThenFalse() {
		Stone a = new Stone(Player.BLACK, Tile.BEETLE);
		Stone b = new Stone(Player.BLACK, Tile.GRASSHOPPER);

		assertFalse(a.equals(b));
	}
}
