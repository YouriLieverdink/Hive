package nl.hanze.hive;

import java.util.List;

import org.junit.jupiter.api.Test;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;
import nl.hanze.hive.Stone.Trait;
import static org.junit.jupiter.api.Assertions.*;

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

	@Test
	// Requirement 1b.
	void givenSameTileThenEqual() {
		Stone s1 = new Stone(Player.WHITE, Tile.BEETLE);
		Stone s2 = new Stone(Player.WHITE, Tile.BEETLE);

		assertEquals(s1, s2);
	}

	@Test
	// Requirement 1b.
	void givenDifferentTileThenNotEqual() {
		Stone s1 = new Stone(Player.WHITE, Tile.BEETLE);
		Stone s2 = new Stone(Player.BLACK, Tile.GRASSHOPPER);

		assertNotEquals(s1, s2);
	}

	@Test
	// Requirement 4e.
	void givenPlayerWhenBelongsToThenTrue() {
		Stone s1 = new Stone(Player.WHITE);

		assertTrue(s1.belongsTo(Player.WHITE));
	}

	@Test
	// Requirement 2e/5e.
	void whenTraitsEqualThenTrue() {
		Stone s1 = new Stone(Player.WHITE, Tile.QUEEN_BEE);

		List<Trait> traits = List.of(Trait.moveOne);

		assertEquals(traits, s1.getTraits());
	}

	@Test
		// not able to move when removing tile creates two groups of tiles
	void tileCantMoveWhenTilesSplitWhenNotAbleToMoveThenFalse() {
		Board board = new Board();
		Stone WQUEEN_BEE = new Stone(Player.WHITE, Tile.QUEEN_BEE);
		Stone WGRASSHOPPER1 = new Stone(Player.WHITE, Tile.GRASSHOPPER);
		Stone WGRASSHOPPER2 = new Stone(Player.WHITE, Tile.GRASSHOPPER);
		board.add(new Position(0, 0), WQUEEN_BEE);
		board.add(new Position(1, 0), WGRASSHOPPER1);
		board.add(new Position(2, 0), WGRASSHOPPER2);

		assertFalse(WGRASSHOPPER1.canMove(board));
	}

	@Test
		// not able to move when another tile is on top
	void tileCantMoveWhenOtherTileOnTopWhenNotAbleToMoveThenFalse() {
		Board board = new Board();
		Stone WQUEEN_BEE = new Stone(Player.WHITE, Tile.QUEEN_BEE);
		Stone WBEETLE = new Stone(Player.WHITE, Tile.BEETLE);
		board.add(new Position(0, 0), WQUEEN_BEE);
		board.add(new Position(0, 0), WBEETLE);

		assertFalse(WQUEEN_BEE.canMove(board));
	}

	@Test
		// Requirements 2e.
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

		Stone stone = board.getStone(new Position(-1, 0));
		List<Position> moves = stone.getPossibleMoves(board);
		assertEquals(moves, List.of(new Position(-1, -1), new Position(-2, 1)));
	}

	@Test
		// Requirements 2e.
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

		Stone stone = board.getStone(new Position(-1, 1));
		List<Position> moves = stone.getPossibleMoves(board);
		assertEquals(moves, List.of(new Position(-1, -1), new Position(-1, 3)));
	}

	@Test
		// Requirements 2e.
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

		Stone stone = board.getStone(new Position(2, -2));
		List<Position> moves = stone.getPossibleMoves(board);
		assertEquals(moves, List.of(new Position(1, -2), new Position(2, -3), new Position(3, -3), new Position(3, -2), new Position(2, -1)));
	}

	@Test
		// Requirements 2e.
	void availableGrasshopperMovesWhenEqualsThenTrue() {
		Board board = new Board();
		board.add(new Position(-1, 0), new Stone(Hive.Player.WHITE, Hive.Tile.QUEEN_BEE));
		board.add(new Position(0, -1), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
		board.add(new Position(0, 0), new Stone(Hive.Player.WHITE, Hive.Tile.GRASSHOPPER));
		board.add(new Position(-1, 1), new Stone(Hive.Player.WHITE, Hive.Tile.SPIDER));
		board.add(new Position(2, 0), new Stone(Hive.Player.BLACK, Hive.Tile.SOLDIER_ANT));
		board.add(new Position(1, 1), new Stone(Hive.Player.BLACK, Hive.Tile.QUEEN_BEE));
		board.add(new Position(1, 0), new Stone(Hive.Player.BLACK, Hive.Tile.SPIDER));
		board.add(new Position(0, 2), new Stone(Hive.Player.BLACK, Hive.Tile.GRASSHOPPER));

		Stone stone = board.getStone(new Position(0, -1));
		List<Position> moves = stone.getPossibleMoves(board);
		assertEquals(moves, List.of(new Position(2, -3), new Position(0, 2)));
	}

	@Test
		// Requirements 2e.
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

		Stone stone = board.getStone(new Position(2, -1));
		List<Position> moves = stone.getPossibleMoves(board);
		assertEquals(moves, List.of(new Position(4, -3), new Position(3, -3), new Position(2, -3), new Position(1, -3), new Position(0, -2),
				new Position(-1, -1), new Position(-1, 0), new Position(-2, 1), new Position(-2, 2), new Position(-1, 2), new Position(0, 2),
				new Position(1, 1), new Position(2, 0), new Position(3, -1), new Position(4, -2)));
	}

}
