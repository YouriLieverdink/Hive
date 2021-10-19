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
	 * The board.
	 */
	private Board board;

	/**
	 * Class constructor.
	 */
	public Controller() {
		this.turn = Player.WHITE;
		this.black = new Hand(Player.BLACK);
		this.white = new Hand(Player.WHITE);
		this.board = new Board();
	}

	/**
	 * Class constructor which specifies the board.
	 *
	 * @param board The board to use.
	 */
	public Controller(Board board) {
		this.turn = Player.WHITE;
		this.black = new Hand(Player.BLACK);
		this.white = new Hand(Player.WHITE);
		this.board = board;
	}

	@Override
	public void play(Tile tile, int q, int r) throws IllegalMove {
		Hand hand = getPlayer();

		// Check whether the player has the provided tile at hand.
		if (!hand.hasTile(tile)) {
			throw new IllegalMove();
		}

		// Check whether the position on the board is empty.
		if (!board.isEmpty(new Position(q, r))) {
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
		// Determine the opponent.
		Player opponent = player == Player.WHITE ? Player.BLACK : Player.WHITE;

		// Retrieve the position of their queen bee on the board.
		Position position = board.getPosition(new Stone(opponent, Tile.QUEEN_BEE));

		// Check whether all the spaces around the queen bee are filled.
		for (Position neighbour : position.getNeighbours()) {
			if (board.isEmpty(neighbour)) {
				return false;
			}
		}

		return true;
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
