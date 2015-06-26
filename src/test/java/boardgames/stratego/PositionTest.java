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

    @Test
    public void shouldBeWithinBounds() {
        Position position = Position.of(3, 3);

        boolean withinBounds = position.isWithinBounds(1, 3);

        assertThat(withinBounds).isTrue();
    }

    @Test
    public void shouldBeOutsideBoundsWhenCoordinateTooBig() {
        Position position = Position.of(3, 4);

        boolean withinBounds = position.isWithinBounds(1, 3);

        assertThat(withinBounds).isFalse();
    }

    @Test
    public void shouldBeOutsideBoundsWhenCoordinateTooSmall() {
        Position position = Position.of(0, 3);

        boolean withinBounds = position.isWithinBounds(1, 3);

        assertThat(withinBounds).isFalse();
    }
}