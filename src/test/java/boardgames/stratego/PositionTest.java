package boardgames.stratego;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class PositionTest {

    Position position = Position.of(2, 2);

    @Test
    public void shouldGetPositionAbove() {
        Position above = position.above();

        assertThat(above).isEqualTo(Position.of(2, 3));
    }

    @Test
    public void shouldGetPositionBelow() {
        Position below = position.below();

        assertThat(below).isEqualTo(Position.of(2, 1));
    }

    @Test
    public void shouldGetPositionOnLeft() {
        Position left = position.left();

        assertThat(left).isEqualTo(Position.of(1, 2));
    }

    @Test
    public void shouldGetPositionOnRight() {
        Position right = position.right();

        assertThat(right).isEqualTo(Position.of(3, 2));
    }
}