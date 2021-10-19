package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardSpec {
	@Test
	// Requirement 2c.
	public void whenNewBoardThenEmpty() {
		Board b = new Board();

		assertTrue(b.getPositions().size() == 0);
	}

	@Test
	public void givenPositionWhenEmptyThenTrue() {
		Board b = new Board();
		Position p = new Position(0, 0);

		assertTrue(b.isEmpty(p));
	}
}
