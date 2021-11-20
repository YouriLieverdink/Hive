package nl.hanze.hive.rules;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hive.models.Board;
import nl.hanze.hive.models.Position;

public class GrassHopper extends Rules {

	@Override
	protected List<Position> getPossibleMoves(Board board, Position from) {
		// Create a list for the jumps and retrieve the position's neighbours.
		ArrayList<Position> possibleMoves = new ArrayList<>();
		ArrayList<Position> neighbours = from.getNeighbours();

		for (Position n : neighbours) {
			// Continue if the neighbour is empty.
			if (board.getStone(n) == null) {
				continue;
			}

			// There is a stone, continue in that direction.
			possibleMoves.add(hop(board, from, neighbours.indexOf(n)));
		}

		return possibleMoves;
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
		// Retrieve the next position.w
		Position next = position.getNeighbours().get(direction);

		if (board.getStone(next) == null) {
			// We found a valid position.
			return next;
		}

		// Continue the hop.
		return hop(board, next, direction);
	}
}
