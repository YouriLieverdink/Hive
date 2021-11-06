package nl.hanze.hive;

import java.util.Objects;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class Stone {
	/**
	 * The owner of the stone.
	 */
	private Player player;

	/**
	 * The stone's face.
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

	/**
	 * Whether the player is owner of this stone.
	 * 
	 * @param player The player to compare against.
	 * @return Whether the player is the owner.
	 */
	public boolean belongsTo(Player player) {
		return this.player == player;
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
