package nl.hanze.hive;

import java.util.HashMap;
import java.util.Map;

import nl.hanze.hive.models.Board;
import nl.hanze.hive.models.Hand;
import nl.hanze.hive.models.Position;
import nl.hanze.hive.models.Stone;

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

	/**
	 * Class constructor which specifies the board and the hand to use.
	 * 
	 * @param board The board to use.
	 * @param hand  The starting hand to use for each player.
	 */
	public Controller(Board board, Hand hand) {
		this();

		this.board = board;

		for (Player player : Player.values()) {
			// Set the hand.
			players.put(player, hand);
		}
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

		if (board.getNumberOfStones(turn) == 3 && board.getPositionOfQueenBee(turn) == null) {
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
		// Create the positions.
		Position from = new Position(fromQ, fromR);
		Position to = new Position(toQ, toR);

		// Retrieve the stone from the board.
		Stone stone = board.getStone(from);

		if (stone == null) {
			// There is no stone at the provided location.
			throw new IllegalMove("There is no stone at the provided location.");
		}

		if (!stone.belongsTo(turn)) {
			// 5a. The stone does not belong to the current player.
			throw new IllegalMove("The stone does not belong to you.");
		}

		if (board.getPositionOfQueenBee(turn) == null) {
			// 5b. The queen must be added in order to move tiles.
			throw new IllegalMove("The queen bee must be added before moving tiles.");
		}

		if (!stone.rules.isAllowedToMove(board, from, to)) {
			// The move is not allowed.
			throw new IllegalMove("This is not a valid move.");
		}

		// Remove the stone from the old position.
		board.remove(from);

		if (!board.isConnected()) {
			// Re-add the removed stone.
			board.add(from, stone);

			// 5d. The stone can't be moved because it separates the hive.
			throw new IllegalMove("This move separates the hive.");
		}

		// Add the stone to the new position.
		board.add(to, stone);

		if (!board.isConnected()) {
			// Re-add the removed stone.
			board.add(from, stone);
			// Remove the added stone.
			board.remove(to);

			// 5c. The stone must be connected to at least on other stone.
			throw new IllegalMove("The stone must be moved to a position next to at least one stone");
		}

		// Move turn to the opponent.
		turn = opponent(turn);
	}

	@Override
	public void pass() throws IllegalMove {

		turn = opponent(turn);
	}

	/**
	 * Whether the queen bee is surrounded.
	 * 
	 * @param player The player whose queen bee to check.
	 * @return Whether it is surrounded.
	 */
	public boolean isQueenBeeSurrounded(Player player) {
		// Retrieve the position of the queen bee.
		Position position = board.getPositionOfQueenBee(player);

		if (position == null) {
			// The player has not player their queen bee yet.
			return false;
		}

		// Check if all the neighbouring positions are occupied.
		for (Position n : position.getNeighbours()) {
			// Return false when a position is empty.
			if (board.getStone(n) == null) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean isWinner(Player player) {
		// Check whether the opponent's queen bee is surrounded.
		return isQueenBeeSurrounded(opponent(player)) && !isQueenBeeSurrounded(player);
	}

	@Override
	public boolean isDraw() {
		// Check whether both queen bees are surrounded.
		return isQueenBeeSurrounded(Player.WHITE) && isQueenBeeSurrounded(Player.BLACK);
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
