package nl.hanze.hive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller implements Hive {
	/**
	 * The players and their hand.
	 */
	private Map<Player, Hand> players;

	/**
	 * The board.
	 */
	private Board board;

	/**
	 * The player who's turn it is.
	 */
	private Player turn;

	/**
	 * Class constructor.
	 */
	public Controller() {
		// Initialise the players.
		players = new HashMap<>();

		for (Player player : Player.values()) {

			Hand hand = new Hand();

			// The starting set of stones.
			hand.add(new Stone(player, Tile.QUEEN_BEE));
			hand.add(new Stone(player, Tile.SPIDER), 2);
			hand.add(new Stone(player, Tile.BEETLE), 2);
			hand.add(new Stone(player, Tile.SOLDIER_ANT), 3);
			hand.add(new Stone(player, Tile.GRASSHOPPER), 3);

			players.put(player, hand);
		}

		// Initialise the board and set the first turn.
		board = new Board();
		turn = Player.WHITE;
	}

	/**
	 * Class constructor which specifies the board.
	 * 
	 * @param board The board to use instead of an empty board.
	 */
	public Controller(Board board) {
		this();

		this.board = board;
	}

	@Override
	public void play(Tile tile, int q, int r) throws IllegalMove {
		// Create the stone and position.
		Stone stone = new Stone(turn, tile);
		Position position = new Position(q, r);

		if (board.getStone(position) != null) {
			// 4b. The provided position is not empty.
			throw new IllegalMove("The provided position is not empty.");
		}

		if (board.getNumberOfStones(turn) == 3 && board.getPosition(new Stone(turn, Tile.QUEEN_BEE)) == null) {
			// 4e. The queen bee must be added within the first four moves.
			throw new IllegalMove("Fourth move, add the queen bee.");
		}

		if (!board.isPure()) {
			// Temporarely add the stone.
			board.add(position, stone);

			if (!board.isConnected()) {
				// Remove the temporary stone.
				board.remove(position);

				// 4c. A stone must be played next to another stone.
				throw new IllegalMove("The stone must be played next to another stone.");
			}

			board.remove(position);
		}

		if (board.getNumberOfStones(turn) >= 1) {
			// Walk through all neighbouring positions of the provided position.
			for (Position p : position.getNeighbours()) {
				// Retrieve the stone from the board.
				Stone s = board.getStone(p);

				if (s != null && !s.belongsTo(turn)) {
					// 4d. When both players have stones, it must be played next to the same color.
					throw new IllegalMove("The stone must be played next to your own stone.");
				}
			}
		}

		if (!players.get(turn).remove(stone)) {
			// 4a. The player does not have the stone at hand.
			throw new IllegalMove("You do not have one of the played stone left.");
		}

		// Add the stone.
		board.add(new Position(q, r), stone);

		// Move turn to the opponent.
		turn = opponent(turn);
	}

	@Override
	public void move(int fromQ, int fromR, int toQ, int toR) throws IllegalMove {
		// Retrieve the stone.
		Stone s1 = board.getStone(new Position(fromQ, fromR));

		if (board.getPosition(new Stone(turn, Tile.QUEEN_BEE)) == null) {
			// The queen bee should be played before moving tiles.
			throw new IllegalMove("Play the queen bee before moving tiles.");
		}

		if (s1 == null || s1.belongsTo(opponent(turn))) {
			// There is no stone to player or it's the opponents.
			throw new IllegalMove("There is no stone or it's the opponents.");
		}

		List<Position> possibleMoves = board.getPossibleMoves(new Position(fromQ, fromR));

		if (possibleMoves.isEmpty()) {
			// There are no possible moves for the stone at the position.
			throw new IllegalMove("There are no possible moves for this stone.");
		}

		if (!possibleMoves.contains(new Position(toQ, toR))) {
			// The provided move is not possible.
			throw new IllegalMove("The provided move is not possible.");
		}

		// Remove the stone from the board.
		board.remove(new Position(fromQ, fromR));

		// Place the stone at the provided location.
		board.add(new Position(toQ, toR), s1);

		// Check whether the board is connected.
		if (!board.isConnected()) {
			// Re-add and re-remove the stones.
			board.remove(new Position(toQ, toR));
			board.add(new Position(fromQ, toR), s1);

			throw new IllegalMove("The board is no longer connected.");
		}

		turn = opponent(turn);
	}

	@Override
	public void pass() throws IllegalMove {
		turn = opponent(turn);
	}

	@Override
	public boolean isWinner(Player player) {
		// Retrieve the position of the opponent's queen bee.
		Stone stone = new Stone(opponent(player), Tile.QUEEN_BEE);
		Position position = board.getPosition(stone);

		if (position == null) {
			// The player has not played their queen bee yet.
			return false;
		}

		// Check all the neighbouring positions.
		for (Position p : position.getNeighbours()) {
			// When the cell is empty, the queen bee is not surrounded.
			if (board.getStone(p) == null) {
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
