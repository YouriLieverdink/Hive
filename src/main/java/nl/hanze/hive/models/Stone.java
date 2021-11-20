package nl.hanze.hive.models;

import java.util.Objects;

import nl.hanze.hive.Hive.Player;
import nl.hanze.hive.Hive.Tile;
import nl.hanze.hive.rules.Beetle;
import nl.hanze.hive.rules.GrassHopper;
import nl.hanze.hive.rules.QueenBee;
import nl.hanze.hive.rules.Rules;
import nl.hanze.hive.rules.SoldierAnt;
import nl.hanze.hive.rules.Spider;

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
	 * This stones' rules.
	 */
	public final Rules rules;

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

		// Initialise the stone's rules.
		switch (tile) {
		case BEETLE:
			this.rules = new Beetle();
			break;
		case GRASSHOPPER:
			this.rules = new GrassHopper();
			break;
		case QUEEN_BEE:
			this.rules = new QueenBee();
			break;
		case SOLDIER_ANT:
			this.rules = new SoldierAnt();
			break;
		case SPIDER:
			this.rules = new Spider();
			break;
		default:
			this.rules = null;
			break;

		}
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
