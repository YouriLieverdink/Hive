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
	 * Add a stone to this board.
	 * 
	 * @param position The position to place the stone.
	 * @param stone    The stone to add.
	 */
	public void add(Position position, Stone stone) {
		// Create a list around the stone to add.
		ArrayList<Stone> newStones = new ArrayList<>(Arrays.asList(stone));

		// Attempt to add the stones to the position.
		ArrayList<Stone> existingStones = positions.putIfAbsent(position, newStones);

		if (existingStones != null) {
			// The provided position already has one or more stones.
			existingStones.addAll(newStones);
			positions.put(position, existingStones);
		}
	}

	/**
	 * Remove the top stone from the provided position.
	 * 
	 * @param position The position to remove the stone from.
	 * @return The stone or null if not found.
	 */
	public Stone remove(Position position) {
		// Retrieve the stones from the position.
		ArrayList<Stone> existingStones = positions.get(position);

		if (existingStones != null && existingStones.size() > 0) {
			// Remove the top stone.
			return existingStones.remove(existingStones.size() - 1);
		}

		return null;
	}

	/**
	 * Locate the position of the stone.
	 * 
	 * @param stone The stone to locate.
	 * @return The position or null if not found.
	 */
	public Position getPosition(Stone stone) {
		// Walk through every occupied position on the board.
		for (Map.Entry<Position, ArrayList<Stone>> position : positions.entrySet()) {

			if (position.getValue().contains(stone)) {
				// The position contains the stone.
				return position.getKey();
			}
		}

		return null;
	}

	/**
	 * Returns the top stone on the provided position.
	 * 
	 * @param position The position to check.
	 * @return The stone or null if not found.
	 */
	public Stone getStone(Position position) {
		// Retrieve the stones from the position.
		ArrayList<Stone> existingStones = positions.get(position);

		if (existingStones != null && existingStones.size() > 0) {
			// Return only the top stone.
			return existingStones.get(existingStones.size() - 1);
		}

		return null;
	}

	/**
	 * Whether the board is empty.
	 */
	public boolean isPure() {

		return positions.isEmpty();
	}
}
