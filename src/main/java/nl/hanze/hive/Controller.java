package nl.hanze.hive;

public class Controller implements Hive {
	/**
	 * The hand of the white player.
	 */
	private Hand white;

	/**
	 * The hand of the black player.
	 */
	private Hand black;

	/**
	 * The board.
	 */
	private Board board;

	/**
	 * The current player.
	 */
	private Player current;

	/**
	 * Class constructor.
	 */
	public Controller() {
		white = new Hand(Player.WHITE);
		black = new Hand(Player.BLACK);
		board = new Board();
		this.current = Player.WHITE;
	}

	/**
	 * Class constructor which specifies the board.
	 * 
	 * @param board The board to use.
	 */
	public Controller(Board board) {
		white = new Hand(Player.WHITE);
		black = new Hand(Player.BLACK);
		this.board = board;
		this.current = Player.WHITE;
	}

	@Override
	public void play(Tile tile, int q, int r) throws IllegalMove {
		// Create the stone to play.
		Stone stone = new Stone(current, tile);

		if (current == Player.WHITE) {

			if (!white.remove(stone)) {
				throw new IllegalMove();
			}

			board.add(new Position(q, r), stone);
		} //
		else if (current == Player.BLACK) {

			if (!black.remove(stone)) {
				throw new IllegalMove();
			}

			board.add(new Position(q, r), stone);
		}

		current = getOpponent(current);
	}

	@Override
	public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
	}

	@Override
	public void pass() throws IllegalMove {
	}

	@Override
	public boolean isWinner(Player player) {
		// Retrieve the position of the opponent's queen bee.
		Stone stone = new Stone(getOpponent(player), Tile.QUEEN_BEE);
		Position position = board.getPosition(stone);

		// Check all the neighbouring positions.
		for (Position p : position.getNeighbours()) {
			// When the cell is empty, the queen bee is not surrounded.
			if (board.isEmpty(p)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isDraw() {
		return isWinner(Player.WHITE) && isWinner(Player.BLACK);
	}

	/**
	 * Returns the opponent of the player.
	 * 
	 * @param player The player.
	 * @return The opponent of the player.
	 */
	public Player getOpponent(Player player) {
		return player == Player.WHITE ? Player.BLACK : Player.WHITE;
	}
}
