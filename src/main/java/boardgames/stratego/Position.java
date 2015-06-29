package boardgames.stratego;

public class Position {

    private final int x;
    private final int y;

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position above() {
        return Position.of(x, y + 1);
    }

    public Position below() {
        return Position.of(x, y - 1);
    }

    public Position left() {
        return Position.of(x - 1, y);
    }

    public Position right() {
        return Position.of(x + 1, y);
    }

    public boolean isWithinBounds(int lower, int upper) {
        return isWithinBounds(x, lower, upper) && isWithinBounds(y, lower, upper);
    }

    private boolean isWithinBounds(int val, int lower, int upper) {
        return val >= lower && val <= upper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return 31 * x + y;
    }

    @Override
    public String toString() {
        return "Position{" + x + ", " + y + '}';
    }
}
