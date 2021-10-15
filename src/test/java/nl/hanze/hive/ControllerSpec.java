package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerSpec {
    @Test
    public void whenNewControllerWhenPlayerWhiteThenTrue() {
        Controller c = new Controller();

        assertTrue(c.getTurn() == Hive.Player.WHITE);
    }
}
