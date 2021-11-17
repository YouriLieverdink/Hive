package nl.hanze.hive;

import java.util.*;

import nl.hanze.hive.Stone.Trait;

public class Rules {
	/**
	 * Whether this move is allowed.
	 * 
	 * @param board The current board.
	 * @param from  The position to move from.
	 * @param to    The position to move to.
	 * @return Whether the move is allowed.
	 */
	public static boolean isAllowedToMove(Board board, Position from, Position to) {

		return getPossibleMoves(board, from).contains(to);
	}

	/**
	 * Find all possible moves using dfs
	 *
	 * @param board    The board.
	 * @param position The position of the tile.
	 * @return List<Position> list of positions where the stone can be moved to
	 */
	public static List<Position> getPossibleMoves(Board board, Position position) {
		// Retrieve the stone from the board.
		Stone stone = board.getStone(position);

		if (stone == null) {
			// Return an empty list when no stones have been found.
			return List.of();
		}

		if (stone.hasTrait(Trait.jump)) {
			// Return the possibel jump moves.
			return getPossibleJumps(board, position);
		}

		// Determine the depth of the search.
		int depth = 0;

		if (stone.hasTrait(Trait.move)) {
			depth = 99;
		} //
		else if (stone.hasTrait(Trait.moveOne)) {
			depth = 1;
		} //
		else if (stone.hasTrait(Trait.moveThree)) {
			depth = 3;
		}

		if (depth == 0) {
			// The stone is not allowed to move.
			return List.of();
		}

		// Temporarely remove the stone from the board.
		board.remove(position);

		// Determine the possible moves.
		ArrayList<Position> availableMoves = new ArrayList<>();
		dfs(board, position, stone, new HashSet<Position>(), depth - 1, availableMoves);

		// Re-add the stone.
		board.add(position, stone);

		return availableMoves;
	}

	/**
	 * Whether this slide is allowed.
	 * 
	 * @param board The current board.
	 * @param from  The position to slide from.
	 * @param to    The position to slide to.
	 * @return Whether the slide is allowed.
	 */
	private static boolean isAllowedToSlide(Board board, Position from, Position to) {
		// Retrieve the neighbouring positions for both positions.
		ArrayList<Position> fromNeighbours = from.getNeighbours();
		ArrayList<Position> toNeighbours = to.getNeighbours();

		// The common neighbours of both positions.
		ArrayList<Position> common = fromNeighbours;
		common.retainAll(toNeighbours);

		if (common.size() <= 1) {
			// 6b. The positions are not adjecent.
			return false;
		}

		int n1 = board.getNumberOfStonesOnPosition(common.get(0));
		int n2 = board.getNumberOfStonesOnPosition(common.get(1));
		int a = board.getNumberOfStonesOnPosition(from);
		int b = board.getNumberOfStonesOnPosition(to);

		if (Math.min(n1, n2) > Math.max(a - 1, b)) {
			// 6b. The stone must be slid between the positions.
			return false;
		}

		if (board.getStone(common.get(0)) == null && board.getStone(common.get(1)) == null
				&& board.getStone(to) == null) {
			// 6c. A stone must always be in contact with another stone.
			return false;
		}

		return true;
	}

	/**
	 * Find all possible moves using dfs.
	 * 
	 * @param board          The current board.
	 * @param position       The current position.
	 * @param stone          The stone that is being moved.
	 * @param visited        The visited locations.
	 * @param depth          The depth of the search.
	 * @param availableMoves The list to store the available moves.
	 */
	private static void dfs(Board board, Position position, Stone stone, HashSet<Position> visited, int depth,
			ArrayList<Position> availableMoves) {
		// Add the current position to the visited list.
		visited.add(position);

		// Check every neighbouring position.
		for (Position n : position.getNeighbours()) {

			// Continue if we have already been here.
			if (visited.contains(n)) {
				continue;
			}

			// Continue if the slide is not allowed.
			if (!isAllowedToSlide(board, position, n)) {
				continue;
			}

			// Continue if the position is occupied and we are not allowed to stack.
			if (board.getStone(n) != null && !stone.hasTrait(Trait.stack)) {
				continue;
			}

			// Add and continue when the maximum depth has been reached.
			if (depth <= 0 && !stone.hasTrait(Trait.move)) {
				availableMoves.add(n);
				continue;
			}

			// Continue the search.
			dfs(board, n, stone, visited, depth - 1, availableMoves);

			// Add all the visited positions when there are no more positions to visit.
			if (stone.hasTrait(Trait.move)) {
				availableMoves.addAll(visited);
			}
		}
	}

	/**
	 * Test all possible directions for possible jumps
	 *
	 * @param board    The current board.
	 * @param position The position of the tile.
	 * @return The possible jump locations.
	 */
	private static ArrayList<Position> getPossibleJumps(Board board, Position position) {
		// Create a list for the jumps and retrieve the position's neighbours.
		ArrayList<Position> possibleJumps = new ArrayList<>();
		ArrayList<Position> neighbours = position.getNeighbours();

		for (Position n : neighbours) {
			// Continue if the neighbour is empty.
			if (board.getStone(n) == null) {
				continue;
			}

			// There is a stone, continue in that direction.
			possibleJumps.add(hop(board, position, neighbours.indexOf(n)));
		}

		return possibleJumps;
	}

	/**
	 * Find all possible moves using hops
	 *
	 * @param board     The current board.
	 * @param from      The current position.
	 * @param direction The direction to hop to.
	 * @return The first empty position in the given direction.
	 */
	private static Position hop(Board board, Position position, int direction) {
		// Retrieve the next position.
		Position next = position.getNeighbours().get(direction);

		if (board.getStone(next) == null) {
			// We found a valid position.
			return next;
		}

		// Continue the hop.
		return hop(board, next, direction);
	}

	public static boolean hasPossibleMoves(Board board, Hive.Player player) {
		Map<Position, ArrayList<Stone>> stonesOfPlayer = board.getStonesFromPlayer(player);

		for (Map.Entry<Position, ArrayList<Stone>> location : stonesOfPlayer.entrySet()) {
			ArrayList<Stone> stones = location.getValue();
			Position position = location.getKey();

			for (Stone stone : stones) {
				if (board.getStone(position).equals(stone)) { //check if stone on top
					if (stone.belongsTo(player)) { //check if stone really belongs to player
						board.remove(position);
						if (board.isConnected()) {
							if (!getPossibleMoves(board, position).isEmpty()){
								return true;
							}
							board.add(position, stone);
						}
					}
				}
			}
		}

		return false;
	}

}
