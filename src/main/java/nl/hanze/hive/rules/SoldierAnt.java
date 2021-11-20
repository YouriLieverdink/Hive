package nl.hanze.hive.rules;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import nl.hanze.hive.models.Board;
import nl.hanze.hive.models.Position;
import nl.hanze.hive.models.Stone;

public class SoldierAnt extends Rules {

	@Override
	protected List<Position> getPossibleMoves(Board board, Position from) {
		// Create a list for the possible moves.
		ArrayList<Position> possibleMoves = new ArrayList<>();

		// Temporarely remove the stone from the board.
		Stone s1 = board.remove(from);

		// Use dfs to populate the possible moves.
		dfs(board, from, new HashSet<>(), possibleMoves);

		// Re-add the stone.
		board.add(from, s1);

		return possibleMoves;
	}

	/**
	 * Find possible moves using dfs.
	 * 
	 * @param board         The current board.
	 * @param visited       The positions which already have been visited.
	 * @param possibleMoves The list of possible moves.
	 * @param depth         The depth of the search.
	 */
	private void dfs(Board board, Position position, HashSet<Position> visited, ArrayList<Position> possibleMoves) {
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

			// Continue if the position is occupied.
			if (board.getStone(n) != null) {
				continue;
			}

			// Continue the search.
			dfs(board, n, visited, possibleMoves);

			// Add all the visited positions.
			possibleMoves.addAll(visited);
		}
	}
}
