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

		int n1 = board.getNumberOfStones(common.get(0));
		int n2 = board.getNumberOfStones(common.get(1));
		int a = board.getNumberOfStones(from);
		int b = board.getNumberOfStones(to);

		if (Math.min(n1, n2) > Math.max(a - 1, b)) {
			// 6b. The stone must be slid between the positions.
			return false;
		}

		if (board.getStone(common.get(0)) == null && board.getStone(common.get(1)) == null && board.getStone(to) == null) {
			// 6c. A stone must always be in contact with another stone.
			return false;
		}

		return true;
	}
}
