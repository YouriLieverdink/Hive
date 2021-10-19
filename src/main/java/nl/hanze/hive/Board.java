package nl.hanze.hive;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    /**
     * The positions on the board.
     */
    private Map<Position, List<Stone>> positions;

    /**
     * Class constructor which initialises with an empty board.
     */
    public Board() {
        this.positions = new HashMap<>();
    }

    /**
     * Class constructor which initialises with a predefined board.
     *
     * @param positions The positions to use.
     */
    public Board(Map<Position, List<Stone>> positions) {
        this.positions = positions;
    }

    /**
     * Return the positions.
     *
     * @return <Map<Position, List<Stone>>
     */
    public Map<Position, List<Stone>> getPositions() {
        return positions;
    }

    /**
     * Checks whether the provided position is empty.
     *
     * @param position The position to check.
     * @return boolean
     */
    public boolean isEmpty(Position position) {
        // Return true when the position has not been registered yet.
        if (!positions.containsKey(position)) {
            return true;
        }

        return positions.get(position).isEmpty();
    }
}