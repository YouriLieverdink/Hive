package nl.hanze.hive;

import java.util.List;
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

	/**
	 * Returns the traits based on the face of this stone.
	 * 
	 * @return A list of traits.
	 */
	public List<Trait> getTraits() {

		switch (tile) {
		case BEETLE:
			return List.of(Trait.moveOne, Trait.stack);

		case GRASSHOPPER:
			return List.of(Trait.jump);

		case QUEEN_BEE:
			return List.of(Trait.moveOne);

		case SOLDIER_ANT:
			return List.of(Trait.move);

		case SPIDER:
			return List.of(Trait.moveThree);

		default:
			return List.of();
		}
	}

	/**
	 * Whether this stone has a trait.
	 * 
	 * @param trait The trait to check.
	 * @return If the traits is present.
	 */
	public boolean hasTrait(Trait trait) {
		return this.getTraits().contains(trait);
	}

	/**
	 * Types of traits.
	 */
	enum Trait {
		// Allowed to move indefinitely .
		move,
		// Allowed to move one times.
		moveOne,
		// Allowed to move three times.
		moveThree,
		// Allowed to jump over stones.
		jump,
		// Allowed to stack on top of others.
		stack,
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
