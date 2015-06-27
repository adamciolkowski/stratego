package boardgames.stratego.piece;

public abstract class Piece {

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Piece)
            return color == ((Piece) o).color;
        return false;
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }
}
