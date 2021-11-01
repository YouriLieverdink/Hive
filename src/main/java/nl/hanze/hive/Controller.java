package nl.hanze.hive;

import nl.hanze.hive.Hive.Player;

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
	 * Class constructor.
	 */
	public Controller() {
		white = new Hand(Player.WHITE);
		black = new Hand(Player.BLACK);
		board = new Board();
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
		// Retrieve the position of the opponent's queen bee.
		Player opponent = Player.BLACK == player ? Player.WHITE : Player.BLACK;
		Stone stone = new Stone(opponent, Tile.QUEEN_BEE);

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

}
