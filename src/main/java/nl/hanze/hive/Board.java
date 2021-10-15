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
}