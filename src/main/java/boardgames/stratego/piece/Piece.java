package boardgames.stratego.piece;

import boardgames.stratego.Board;
import boardgames.stratego.Position;

import java.util.Set;

public abstract class Piece {

    protected final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public abstract Set<Position> getPossibleMovesFrom(Position position, Board board);

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
