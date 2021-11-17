package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

import static nl.hanze.hive.Rules.isAllowedToMove;

public class Stone {
	/**
	 * The owner of the stone.
	 */
	private Player player;

	/**
	 * The stone's face.
	 */
	private Tile tile;

	/**
	 * The stones available moves.
	 */
	private ArrayList<Position> availableMoves;

	/**
	 * Class constructor which specifies the player.
	 * 
	 * @param Player The player.
	 */
	public Stone(Player player) {
		this(player, Tile.BEETLE);
	}

	/**
	 * Class constructor which specifies the player and tile.
	 * 
	 * @param Player The player.
	 * @param Tile   The tile.
	 */
	public Stone(Player player, Tile tile) {
		this.player = player;
		this.tile = tile;
	}

	/**
	 * Whether the player is owner of this stone.
	 * 
	 * @param player The player to compare against.
	 * @return Whether the player is the owner.
	 */
	public boolean belongsTo(Player player) {
		return this.player == player;
	}

	/**
	 * Returns the traits based on the face of this stone.
	 * 
	 * @return A list of traits.
	 */
	public List<Trait> getTraits() {

		switch (tile) {
		case BEETLE:
			return List.of(Trait.moveOne, Trait.stack);

		case GRASSHOPPER:
			return List.of(Trait.jump);

		case QUEEN_BEE:
			return List.of(Trait.moveOne);

		case SOLDIER_ANT:
			return List.of(Trait.move);

		case SPIDER:
			return List.of(Trait.moveThree);

		default:
			return List.of();
		}
	}

	/**
	 * Types of traits.
	 */
	enum Trait {
		// Allowed to move indefinitely .
		move,
		// Allowed to move one times.
		moveOne,
		// Allowed to move three times.
		moveThree,
		// Allowed to jump over stones.
		jump,
		// Allowed to stack on top of others.
		stack,
	}

	/**
	 * Find all possible moves using dfs
	 *
	 * @param board The board.
	 * @param position The position of the tile.
	 * @return List<Position> list of positions where the stone can be moved to
	 */
	public List<Position> getPossibleMoves (Board board, Position position) {
		availableMoves = new ArrayList<>();
		int distanceLimit = 0;
		boolean stack = getTraits().contains(Trait.stack);
		boolean indefinitely  = getTraits().contains(Trait.move);

		if (getTraits().contains(Trait.moveOne)) {
			distanceLimit = 1;
		} else if (getTraits().contains(Trait.moveThree)) {
			distanceLimit = 3;
		}

		if (getTraits().contains(Trait.jump)) {
			return jump(board, position);
		}

		board.remove(position);
		dfs(board, position, new HashSet<Position>(), 1, distanceLimit, stack, indefinitely);
		board.add(position, this);
		return new ArrayList<>(availableMoves);
	}

	/**
	 * Find all possible moves using dfs
	 *
	 * @param board The board.
	 * @param current The current position being tested.
	 * @param visited All visited positions.
	 * @param distance The current distance of the algorithm.
	 * @param distanceLimit the distance limit of the stone.
	 * @param stack if the stone is allowed to be stacked on other stones.
	 */
	public void dfs (Board board, Position current, HashSet<Position> visited, int distance, int distanceLimit, boolean stack, boolean indefinitely) {
		visited.add(current);
		for (Position neighbour : current.getNeighbours()) {
			if (!visited.contains(neighbour) && isAllowedToMove(board, current, neighbour) && (stack || board.getStone(neighbour) == null)) {
				if (distance >= distanceLimit) {
					if (!indefinitely) {
						availableMoves.add(neighbour);
						continue;
					}
				}
				dfs(board, neighbour, visited, distance + 1, distanceLimit, stack, indefinitely);
				if (indefinitely) {
					availableMoves.addAll(visited);
				}
			}
		}
	}

	/**
	 * Test all possible directions for possible jumps
	 *
	 * @param board The board.
	 * @param position TThe position of the tile.
	 * @return ArrayList<Position> positions the jump can reach.
	 */
	private ArrayList<Position> jump (Board board, Position position) {
		ArrayList<Position> moves = new ArrayList<>();
		int[][] orientations = {{0, 1}, {0, -1}, {-1, 0}, {-1, 1}, {1, 0}, {1, -1}};
		for (int[] orientation : orientations) {
			Position nextPosition = new Position(position.getQ() + orientation[0], position.getR() + orientation[1]);
			if(board.getStone(nextPosition) != null) {
				moves.add(hop(board, position, orientation[0], orientation[1]));
			}
		}
		return moves;
	}

	/**
	 * Find all possible moves using hops
	 *
	 * @param board The board.
	 * @param from The position where to hop from.
	 * @param Q the Q coordinate.
	 * @param R the R coordinate.
	 * @return ArrayList<Position> positions the jump can reach.
	 */
	private Position hop(Board board, Position from, int Q, int R) {
		Position next = new Position(from.getQ() + Q, from.getR() + R);
		if (board.getStone(next) != null) {
			return hop(board, next, Q, R);
		} else {
			return next;
		}
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		Stone s1 = (Stone) object;

		return s1.player == player && s1.tile == tile;
	}

	@Override
	public int hashCode() {

		return Objects.hash(player, tile);
	}
}
