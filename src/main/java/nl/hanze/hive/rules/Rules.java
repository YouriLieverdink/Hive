package nl.hanze.hive.rules;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hive.models.Board;
import nl.hanze.hive.models.Position;

public abstract class Rules {
	/**
	 * Whether the provided move is allowed.
	 * 
	 * @param board The current board.
	 * @param from  The positon to move from.
	 * @param to    The positon to move to.
	 * @return Whether the move is allowed.
	 */
	public boolean isAllowedToMove(Board board, Position from, Position to) {

		return this.getPossibleMoves(board, from).contains(to);
	}

	/**
	 * Retrieve a list of possible moves.
	 * 
	 * @param board The current board.
	 * @param from  The position of the stone.
	 * @return A list of the possible moves.
	 */
	protected abstract List<Position> getPossibleMoves(Board board, Position from);

	/**
	 * Retrieve a list of possible plays.
	 * 
	 * @param board The current board.
	 * @return A list of possible plays.
	 */
	public List<Position> getPossiblePlays(Board board) {

		return null;
	}

	/**
	 * Whether a stone is allowed to make a slide.
	 * 
	 * @param board The current board.
	 * @param from  The position to slide form.
	 * @param to    The position to slide to.
	 * @return Whether it is possible.
	 */
	protected boolean isAllowedToSlide(Board board, Position from, Position to) {
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
}
