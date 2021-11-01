package nl.hanze.hive;

import java.util.HashMap;
import java.util.Map;

public class Controller implements Hive {
	/**
	 * The hands of the active players.
	 */
	private Map<Player, Hand> hands;

	/**
	 * The active board.
	 */
	private Board board;

	/**
	 * The player whose turn it is.
	 */
	private Player turn;

	/**
	 * Class constructor.
	 */
	public Controller() {
		// Initialise the hands of the players.
		hands = new HashMap<>();
		for (Player p : Player.values()) {
			hands.put(p, new Hand(p));
		}

		// Create the board and set the turn.
		board = new Board();
		turn = Player.WHITE;
	}

	/**
	 * Class constructor which specifies the board.
	 * 
	 * @param board The board to use.
	 */
	public Controller(Board board) {
		this();

		this.board = board;
	}

	@Override
	public void play(Tile tile, int q, int r) throws IllegalMove {
		// Create the stone to play.
		Stone stone = new Stone(turn, tile);

		if (!hands.get(turn).remove(stone)) {
			// The player does not have the stone.
			throw new IllegalMove();
		}

		// The player has the stone, add it to the board.
		board.add(new Position(q, r), stone);

		// Set the turn to the opponent.
		turn = opponent(turn);
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
		Stone stone = new Stone(opponent(player), Tile.QUEEN_BEE);
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
	public Player opponent(Player player) {
		return player == Player.WHITE ? Player.BLACK : Player.WHITE;
	}
}
