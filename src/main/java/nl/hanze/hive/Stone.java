package nl.hanze.hive;

import java.util.Objects;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class Stone {
	/**
	 * To whom the stone belongs.
	 */
	private Player player;

	/**
	 * The face of the stone.
	 */
	private Tile tile;

	/**
	 * Class constructor which specifies the player.
	 * 
	 * @param Player The player.
	 */
	public Stone(Player player) {
		this(player, Tile.BEETLE);
	}

	/**
	 * Class constructor which specifies the player and tile.
	 * 
	 * @param Player The player.
	 * @param Tile   The tile.
	 */
	public Stone(Player player, Tile tile) {
		this.player = player;
		this.tile = tile;
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

		return s1.player == player && s1.tile == tile;
	}

	@Override
	public int hashCode() {

		return Objects.hash(player, tile);
	}
}
