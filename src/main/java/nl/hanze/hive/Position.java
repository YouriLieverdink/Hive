package nl.hanze.hive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Position {
	/**
	 * The q coordinate.
	 */
	private int q;

	/**
	 * The r coordinate.
	 */
	private int r;

	/**
	 * Class constructor which specifies the coordinates.
	 * 
	 * @param q The q coordinate.
	 * @param r The r coordinate.
	 */
	public Position(int q, int r) {
		this.q = q;
		this.r = r;
	}

	/**
	 * The neighbouring positions of this.
	 *
	 * @return A list of neighbouring positions.
	 */
	public ArrayList<Position> getNeighbours() {
		return new ArrayList<>(Arrays.asList(new Position(q + 1, r - 1), new Position(q + 1, r), new Position(q, r + 1),
				new Position(q - 1, r + 1), new Position(q - 1, r), new Position(q, r - 1)));
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		Position s1 = (Position) object;

		return s1.q == q && s1.r == r;
	}

	@Override
	public int hashCode() {

		return Objects.hash(q, r);
	}
}
