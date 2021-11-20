package nl.hanze.hive;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class RulesSpec {

	@Test
	// Requirements 8.
	void availableQueenBeeMovesWhenEqualsThenTrue() {
		Board board = new Board();
		board.add(new Position(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
		board.add(new Position(0, -1), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
		board.add(new Position(0, 0), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
		board.add(new Position(-1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
		board.add(new Position(2, 0), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
		board.add(new Position(1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));
		board.add(new Position(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));

		List<Position> moves = Rules.getPossibleMoves(board, new Position(-1, 0));
		assertTrue(moves.containsAll(List.of(new Position(-1, -1), new Position(-2, 1))));
	}

	@Test
	// Requirements 10.
	void availableSpiderMovesWhenEqualsThenTrue() {
		Board board = new Board();
		board.add(new Position(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
		board.add(new Position(0, -1), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
		board.add(new Position(0, 0), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
		board.add(new Position(-1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
		board.add(new Position(2, 0), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
		board.add(new Position(1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));
		board.add(new Position(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));

		List<Position> moves = Rules.getPossibleMoves(board, new Position(-1, 1));
		assertTrue(moves.containsAll(List.of(new Position(-1, -1), new Position(-1, 3))));
	}

	@Test
	// Requirements 7.
	void availableBeetleMovesWhenEqualsThenTrue() {
		Board board = new Board();
		board.add(new Position(0, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
		board.add(new Position(1, -2), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(2, -1), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(2, -2), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
		board.add(new Position(0, -1), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
		board.add(new Position(3, -2), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(0, 1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
		board.add(new Position(-1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));

		List<Position> moves = Rules.getPossibleMoves(board, new Position(2, -2));
		assertTrue(moves.containsAll(List.of(new Position(1, -2), new Position(2, -3), new Position(3, -3),
				new Position(3, -2), new Position(2, -1))));
	}

	@Test
	// Requirements 11.
	void availableGrasshopperMovesWhenEqualsThenTrue() {
		Board board = new Board();
		board.add(new Position(0, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
		board.add(new Position(1, -2), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(2, -1), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(2, -2), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
		board.add(new Position(0, -1), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
		board.add(new Position(3, -2), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(0, 1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
		board.add(new Position(-1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));

		List<Position> moves = Rules.getPossibleMoves(board, new Position(0, -1));
		assertTrue(moves.containsAll(List.of(new Position(2, -3), new Position(0, 2))));
	}

	@Test
	// Requirements 9.
	void availableSoldierAntMovesWhenEqualsThenTrue() {
		Board board = new Board();
		board.add(new Position(0, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
		board.add(new Position(1, -2), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(2, -1), new Stone(Hive.Player.WHITE, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(2, -2), new Stone(Hive.Player.WHITE, Hive.Tile.BEETLE));
		board.add(new Position(0, -1), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
		board.add(new Position(3, -2), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(0, 1), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
		board.add(new Position(-1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));

		List<Position> moves = Rules.getPossibleMoves(board, new Position(2, -1));
		assertTrue(moves.containsAll(List.of(new Position(4, -3), new Position(3, -3), new Position(2, -3),
				new Position(1, -3), new Position(0, -2), new Position(-1, -1), new Position(-1, 0),
				new Position(-2, 1), new Position(-2, 2), new Position(-1, 2), new Position(0, 2), new Position(1, 1),
				new Position(2, 0), new Position(3, -1), new Position(4, -2))));
	}

	@Test
	// Requirement 6b.
	void givenPlayerWhenPossibleMovesThenTrue() {
		Board b1 = new Board();
		b1.add(new Position(0, 0), new Stone(Player.WHITE, Tile.QUEEN_BEE));
		b1.add(new Position(0, 1), new Stone(Player.BLACK, Tile.QUEEN_BEE));

		assertTrue(Rules.hasPossibleMove(b1, Player.WHITE));
	}

	@Test
	// Requirement 6b.
	void givenPlayerWhenNoPossibleMovesThenFalse() {
		Board b1 = new Board();
		b1.add(new Position(0, -1), new Stone(Player.WHITE, Tile.QUEEN_BEE));
		b1.add(new Position(0, 0), new Stone(Player.BLACK, Tile.QUEEN_BEE));
		b1.add(new Position(0, 1), new Stone(Player.WHITE, Tile.SOLDIER_ANT));

		assertFalse(Rules.hasPossibleMove(b1, Player.BLACK));
	}
}
