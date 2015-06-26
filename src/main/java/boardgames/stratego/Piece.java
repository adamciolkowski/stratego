package boardgames.stratego;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Set<Position> getPossibleMovesFrom(Position position, Board board) {
        return probableMovesFrom(position).stream()
                .filter(board::isValid)
                .filter(p -> isEmptyOrOccupiedByEnemy(p, board))
                .collect(toSet());
    }

    private boolean isEmptyOrOccupiedByEnemy(Position position, Board board) {
        Piece piece = board.getPieceAt(position);
        return piece == null || piece.color != color;
    }

    private List<Position> probableMovesFrom(Position p) {
        return asList(p.above(), p.below(), p.left(), p.right());
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Piece) {
            return color == ((Piece) o).color;
        }
        return false;
    }
}
