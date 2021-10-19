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
     * Class constructor which initialises an empty board.
     */
    public Board() {
        this.positions = new HashMap<>();
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