package nl.hanze.hive;

public class Controller implements Hive {
	/**
	 * The player whose turn it is.
	 */
	private Player turn;

	/**
	 * Class constructor.
	 */
	public Controller() {
		this.turn = Player.WHITE;
	}

	@Override
	public void play(Tile tile, int q, int r) throws IllegalMove {
	}

	@Override
	public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
	}

	@Override
	public void pass() throws IllegalMove {
	}

	@Override
	public boolean isWinner(Player player) {
		return false;
	}

	@Override
	public boolean isDraw() {
		return false;
	}

	/**
	 * Retrieves the player whose turn it is.
	 *
	 * @return Player
	 */
	public Player getTurn() {
		return turn;
	}
}
