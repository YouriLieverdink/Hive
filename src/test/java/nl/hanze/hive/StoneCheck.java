package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;

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
}
