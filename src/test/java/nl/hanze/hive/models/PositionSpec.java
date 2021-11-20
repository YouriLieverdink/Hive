package nl.hanze.hive.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PositionSpec {
	@Test
	// Requirement 2a.
	void givenSameCoordinatesThenEqual() {
		Position p1 = new Position(-1, 2);
		Position p2 = new Position(-1, 2);

		assertEquals(p1, p2);
	}

	@Test
	// Requirement 2b.
	public void givenPositionWhenNeighbouringEqualsThenTrue() {
		Position p = new Position(0, 0);

		List<Position> neighbours = List.of(new Position(1, -1), new Position(1, 0), new Position(0, 1),
				new Position(-1, 1), new Position(-1, 0), new Position(0, -1));

		assertEquals(p.getNeighbours(), neighbours);
	}
}
