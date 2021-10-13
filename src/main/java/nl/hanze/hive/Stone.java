package nl.hanze.hive;

import java.util.Objects;

import nl.hanze.hive.Hive.Player;

public class Stone {
	/**
	 * The player to which the stone belongs.
	 */
	private Player player;

	/**
	 * Class constructor which specifies the player.
	 * 
	 * @param player The player.
	 */
	public Stone(Player player) {
		this.player = player;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || this.getClass() != obj.getClass()) {
			return false;
		}

		Stone stone = (Stone) obj;

		return this.player == stone.player;
	}

	@Override
	public int hashCode() {

		return Objects.hash(player);
	}
}
