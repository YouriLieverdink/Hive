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
		// Create the stone to play.
		Stone stone = new Stone(turn, tile);

		if (board.getPosition(new Stone(turn, Tile.QUEEN_BEE)) == null && board.getNumberOfStones(turn) == 3) {
			// The player is on their 4th turn and hasn't player their queen bee yet.
			throw new IllegalMove("fourth turn and no queen bee");
		}

		if (board.getStone(new Position(q, r)) != null) {
			// The provided position is not empty.
			throw new IllegalMove("There is already a stone at the position");
		}

		if (board.getNumberOfStones(turn) >= 1) {

			for (Position p : new Position(q, r).getNeighbours()) {
				// Check whether a neighbouring position contains a stone of the opponent.
				Stone s1 = board.getStone(p);

				if (s1 != null && !s1.belongsTo(turn)) {
					throw new IllegalMove("The stone is placed next to an opponent's stone.");
				}
			}
		}

		if (!board.isPure()) {
			boolean isConnected = false;

			// Check all the neighbouring positions.
			for (Position p : new Position(q, r).getNeighbours()) {
				// When a cell is empty, it is not connected.
				if (board.getStone(p) != null) {
					isConnected = true;
				}
			}

			if (!isConnected) {
				throw new IllegalMove("The stone must be placed next to another stone.");
			}
		}

		if (!players.get(turn).remove(stone)) {
			// The player does not have the stone.
			throw new IllegalMove("player does not own the played stone");
		}

		// Add it to the board.
		board.add(new Position(q, r), stone);

		// Set the turn to the opponent.
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
