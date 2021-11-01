package nl.hanze.hive;

import java.util.Objects;

import nl.hanze.hive.Hive.Player;

public class Stone {
	/**
	 * To whom the stone belongs.
	 */
	private Player player;

	/**
	 * Class constructor which specifies the player.
	 * 
	 * @param Player The player.
	 */
	public Stone(Player player) {
		this.player = player;
	}

	@Override
	public boolean equals(Object object) {

		if (this == object) {
			return true;
		}

		if (object == null || getClass() != object.getClass()) {
			return false;
		}

		Stone s1 = (Stone) object;

		return s1.player == player;
	}

	@Override
	public int hashCode() {

		return Objects.hash(player);
	}
}
