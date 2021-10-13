package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;

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
}
