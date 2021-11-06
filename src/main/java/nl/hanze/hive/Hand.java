package nl.hanze.hive;

import java.util.HashMap;
import java.util.Map;

public class Hand {
	/**
	 * The stones at hand an their quantity.
	 */
	private Map<Stone, Integer> stones;

	/**
	 * Class constructor.
	 */
	public Hand() {
		stones = new HashMap<>();
	}

	/**
	 * Adds a stone to the hand.
	 * 
	 * @param stone The stone to add.
	 */
	public void add(Stone stone) {
		add(stone, 1);
	}

	/**
	 * Adds a stone (amount) number of times.
	 * 
	 * @param stone  The stone to add.
	 * @param amount The number of times the stone should be added.
	 */
	public void add(Stone stone, Integer amount) {

		Integer numberOf = stones.putIfAbsent(stone, amount);

		if (numberOf != null) {
			// The stone was already present, increment with 1.
			stones.put(stone, numberOf + amount);
		}
	}

	/**
	 * Removes a stone from the hand. Returns true when successfull.
	 * 
	 * @param stone The stone to remove.
	 * @return Whether the stone was present.
	 */
	public boolean remove(Stone stone) {

		Integer numberOf = stones.get(stone);

		if (numberOf != null && numberOf > 0) {
			// Decrease the numbers of stones with 1.
			stones.put(stone, numberOf - 1);

			return true;
		}

		return false;
	}
}
