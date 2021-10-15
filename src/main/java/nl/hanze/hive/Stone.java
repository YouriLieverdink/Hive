package nl.hanze.hive;

import java.util.Objects;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;

public class Stone {
	/**
	 * The player to which the stone belongs.
	 */
	private Player player;

	/**
	 * The image on the stone.
	 */
	private Tile tile;

	/**
	 * Class constructor which specifies the player.
	 * 
	 * @param player The player.
	 */
	public Stone(Player player) {
		this(player, Tile.BEETLE);
	}

	/**
	 * Class constructor which specifies the player and tile.
	 * 
	 * @param player The player.
	 * @param tile   The tile.
	 */
	public Stone(Player player, Tile tile) {
		this.player = player;
		this.tile = tile;
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

		return this.player == stone.player && this.tile == stone.tile;
	}
}
