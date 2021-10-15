package nl.hanze.hive;

import java.util.List;
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
	 * Class constructor which specifies the q and r coordinate.
	 *
	 * @param q The q coordinate.
	 * @param r The r coordinate.
	 */
	public Position(int q, int r) {
		this.q = q;
		this.r = r;
	}

	/**
	 * The neighbouring positions for this.
	 *
	 * @return List<Position>
	 */
	public List<Position> getNeighbours() {
		return List.of(
				new Position(q + 1, r - 1),
				new Position(q + 1, r),
				new Position(q, r  + 1),
				new Position(q - 1, r + 1),
				new Position(q - 1, r),
				new Position(q, r - 1)
		);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		Position position = (Position) obj;

		return this.q == position.q && this.r == position.r;
	}

	@Override
	public int hashCode() {

		return Objects.hash(q, r);
	}
}
