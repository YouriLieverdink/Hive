package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	public void givenPositionWhenFilledThenFalse() {
		Position p = new Position(0, 0);
		Stone s = new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);
		Map<Position, List<Stone>> positions = Map.of(p, List.of(s));

		Board b = new Board(positions);

		assertFalse(b.isEmpty(p));
	}

	@Test
	public void givenStoneWhenFoundThenPosition() {
		Position p = new Position(4, -3);
		Stone s = new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);

		Map<Position, List<Stone>> positions = Map.of(p, List.of(s));
		Board b = new Board(positions);

		assertTrue(p.equals(b.getPosition(s)));
	}

	@Test
	public void givenStoneWhenNotFoundThenNull() {
		Position p = new Position(4, -3);
		Stone s = new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE);

		Board b = new Board();

		assertNull(b.getPosition(s));
	}
}
