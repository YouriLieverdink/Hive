package nl.hanze.hive.rules;

import java.util.ArrayList;
import java.util.List;

import nl.hanze.hive.models.Board;
import nl.hanze.hive.models.Position;
import nl.hanze.hive.models.Stone;

public class QueenBee extends Rules {

	@Override
	protected List<Position> getPossibleMoves(Board board, Position from) {
		// Create a list for the possible moves.
		ArrayList<Position> possibleMoves = new ArrayList<>();

		for (Position n : from.getNeighbours()) {
			// Continue if it is not allowed to slide.
			if (!isAllowedToSlide(board, from, n)) {
				continue;
			}

			// Continue if the position is not empty.
			if (board.getStone(n) != null) {
				continue;
			}

			// Temporarely update the board.
			Stone s1 = board.remove(from);
			board.add(n, s1);

			// Check if the board is still connected.
			if (board.isConnected()) {
				// It is a possible move.
				possibleMoves.add(n);
			}

			// Reset the board.
			board.add(from, s1);
			board.remove(n);
		}

		return possibleMoves;
	}

}
