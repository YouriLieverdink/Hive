package nl.hanze.hive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ControllerSpec {
    @Test
    public void whenNewControllerWhenPlayerWhiteThenTrue() {
        Controller c = new Controller();

        assertTrue(c.getTurn() == Hive.Player.WHITE);
    }

    @Test
    public void whenNewControllerThenBlackStartingHand() {
        Controller c = new Controller();
        Hand h = new Hand(Hive.Player.BLACK);

        assertTrue(c.black.equals(h));
    }

    @Test
    public void whenNewControllerThenWhiteStartingHand() {
        Controller c = new Controller();
        Hand h = new Hand(Hive.Player.WHITE);

        assertTrue(c.white.equals(h));
    }
}
