package nl.hanze.hive;

public class Controller implements Hive {
	/**
	 * The player whose turn it is.
	 */
	private Player turn;

	/**
	 * The hand of the black player.
	 */
	private Hand black;

	/**
	 * The hand of the white player.
	 */
	private Hand white;

	/**
	 * Class constructor.
	 */
	public Controller() {
		this.turn = Player.WHITE;
		this.black = new Hand(Player.BLACK);
		this.white = new Hand(Player.WHITE);
	}

	@Override
	public void play(Tile tile, int q, int r) throws IllegalMove {
		Hand hand = getPlayer();

		// Check whether the player has the provided tile at hand.
		if (!hand.hasTile(tile)) {
			throw new IllegalMove();
		}
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

	/**
	 * Retrieves the hand of the player whose turn it is.
	 *
	 * @return Hand
	 */
	public Hand getPlayer() {
		return turn == Player.WHITE ? white : black;
	}

	/**
	 * Retrieves the hand of the black player.
	 *
	 * @return Hand
	 */
	public Hand getBlack() {
		return black;
	}

	/**
	 * Retrieves the hand of the white player.
	 *
	 * @return White
	 */
	public Hand getWhite() {
		return white;
	}
}
