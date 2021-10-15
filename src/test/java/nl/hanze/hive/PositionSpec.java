package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class PositionSpec {
	@Test
	public void givenSameCoordinatesWhenEqualsThenTrue() {
		Position a = new Position(-3, 2);
		Position b = new Position(-3, 2);

		assertTrue(a.equals(b));
	}

	@Test
	// Requirement 2b.
	public void givenPositionWhenNeighbouringEqualsThenTrue() {
		Position p = new Position(0, 0);

		List<Position> neighbours = List.of(
				new Position(1, -1),
				new Position(1, 0),
				new Position(0, 1),
				new Position(-1, 1),
				new Position(-1, 0),
				new Position(0, -1)
		);

		assertEquals(p.getNeighbours(), neighbours);
	}
}
