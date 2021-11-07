package nl.hanze.hive;

import java.util.ArrayList;

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

		return isAllowedToSlide(board, from, to);
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
		// Retrieve the common neighbours of the from and to positions.
		ArrayList<Position> neighbours = from.getNeighbours();
		neighbours.retainAll(to.getNeighbours());

		if (neighbours.size() == 1) {
			// The positions are not adjecent.
			return false;
		}

		int n1 = board.getNumberOfStones(neighbours.get(0));
		int n2 = board.getNumberOfStones(neighbours.get(1));
		int a = board.getNumberOfStones(from);
		int b = board.getNumberOfStones(to);

		return Math.min(n1, n2) <= Math.max(a - 1, b);
	}
}
