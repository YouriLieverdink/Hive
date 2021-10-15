package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardSpec {
	@Test
	// Requirement 2c.
	public void whenNewBoardThenEmpty() {
		Board b = new Board();

		assertTrue(b.positions.size() == 0);
	}
}
