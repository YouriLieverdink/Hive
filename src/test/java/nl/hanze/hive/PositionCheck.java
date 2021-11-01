package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class PositionCheck {
	@Test
	// Requirement 2a.
	void givenSameCoordinatesThenEqual() {
		Position p1 = new Position(-1, 2);
		Position p2 = new Position(-1, 2);

		assertEquals(p1, p2);
	}
}
