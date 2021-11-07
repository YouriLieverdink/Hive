package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import nl.hanze.hive.Hive.Player;

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
			Stone s1 = existingStones.remove(existingStones.size() - 1);

			if (existingStones.isEmpty()) {
				// Remove the entry when no more stones are left.
				positions.remove(position);
			}

			return s1;
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

	/**
	 * Counts the number of stones on the board for a player.
	 * 
	 * @param player The player to count the stones for.
	 * @return The number of stones.
	 */
	public Integer getNumberOfStones(Player player) {
		// The count.
		int numberOf = 0;

		// Walk through all the positions on the board.
		for (ArrayList<Stone> stones : positions.values()) {
			// Walk through all the stones on the position.
			for (Stone stone : stones) {

				if (stone.belongsTo(player)) {
					numberOf += 1;
				}
			}
		}

		return numberOf;
	}

	/**
	 * Whether the hive is currently connected.
	 * 
	 * @return Whether the hive is connected.
	 */
	public boolean isConnected() {
		// Check whether the board has any stones.
		if (positions.isEmpty()) {
			return true;
		}

		// Retrieve the starting position.
		Position initial = positions.keySet().iterator().next();

		ArrayList<Position> todo = new ArrayList<>(Arrays.asList(initial));
		ArrayList<Position> visited = new ArrayList<>(Arrays.asList(initial));

		while (!todo.isEmpty()) {
			// Retrieve the position.
			Position position = todo.remove(0);

			// Walk through its neighbours.
			for (Position p : position.getNeighbours()) {

				if (getStone(p) != null && !visited.contains(p)) {
					// There is a stone and its position has not yet been visited.
					todo.add(p);
					visited.add(p);
				}
			}
		}

		return visited.containsAll(positions.keySet());
	}
}
