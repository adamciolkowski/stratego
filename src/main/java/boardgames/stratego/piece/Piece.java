package boardgames.stratego.piece;

import boardgames.stratego.Board;
import boardgames.stratego.Position;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public class Piece {

    private final Color color;

    private final Rank rank;

    public Piece(Color color, Rank rank) {
        this.color = color;
        this.rank = rank;
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

    public EngagementOutcome attack(Piece piece) {
        int result = piece.rank.compareTo(rank);
        if(result > 0) {
            return EngagementOutcome.ATTACKER_LOSES;
        } else if(result < 0) {
            return EngagementOutcome.ATTACKER_WINS;
        } else {
            return EngagementOutcome.BOTH_DIE;
        }
    }

    public Color getColor() {
        return color;
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && rank == piece.rank;
    }

    @Override
    public int hashCode() {
        return 31 * color.hashCode() + rank.hashCode();
    }

    @Override
    public String toString() {
        return color + " " + rank;
    }
}
