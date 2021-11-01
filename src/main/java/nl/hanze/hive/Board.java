package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
	/**
	 * The positions and the stones on that position.
	 */
	private Map<Position, ArrayList<Stone>> positions;

	/**
	 * Class constructor.
	 */
	public Board() {
		positions = new HashMap<>();
	}

	/**
	 * Add a stone to the board.
	 * 
	 * @param position The positon to add the stone to.
	 * @param stone    The stone to add.
	 */
	public void add(Position position, Stone stone) {

		ArrayList<Stone> stones = positions.putIfAbsent(position, new ArrayList<>(Arrays.asList(stone)));

		if (stones != null) {
			// The position already had one or more stones.
			stones.add(stone);
			positions.put(position, stones);
		}
	}

	/**
	 * Remove the top stone from the provided position.
	 * 
	 * @param position The position to remove the stone from.
	 */
	public boolean remove(Position position) {

		ArrayList<Stone> stones = positions.get(position);

		if (stones != null && stones.size() > 0) {
			// Return the top stone.
			stones.remove(stones.size() - 1);
			positions.put(position, stones);

			return true;
		}

		return false;
	}
}
