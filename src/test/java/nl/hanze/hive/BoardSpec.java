package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BoardSpec {
	@Test
	public void whenNewBoardWhenEmptyThenTrue() {
		Board b = new Board();

		assertTrue(b.getPositions().size() == 0);
	}

	@Test
	public void givenPositionsWhenNewBoardWhenEmptyThenFalse() {
		Map<Position, List<Stone>> positions = Map.of(
				new Position(0,0),
				List.of(new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE))
		);

		Board b = new Board(positions);

		assertFalse(b.getPositions().size() == 0);
	}

	@Test
	public void givenPositionWhenEmptyThenTrue() {
		Board b = new Board();
		Position p = new Position(0, 0);

		assertTrue(b.isEmpty(p));
	}
}
