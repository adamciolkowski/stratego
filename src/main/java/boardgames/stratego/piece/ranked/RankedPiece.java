package boardgames.stratego.piece.ranked;

import boardgames.stratego.Board;
import boardgames.stratego.Position;
import boardgames.stratego.piece.Color;
import boardgames.stratego.piece.Flag;
import boardgames.stratego.piece.Piece;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public abstract class RankedPiece extends Piece {

    private final Rank rank;

    public RankedPiece(Color color, Rank rank) {
        super(color);
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
        return piece == null || piece.getColor() != color;
    }

    private List<Position> probableMovesFrom(Position p) {
        return asList(p.above(), p.below(), p.left(), p.right());
    }

    public EngagementOutcome attack(Piece piece) {
        if (piece instanceof Flag) {
            return EngagementOutcome.ATTACKER_WINS;
        }
        return attackRanked((RankedPiece) piece);
    }

    protected EngagementOutcome attackRanked(RankedPiece piece) {
        int result = piece.rank.compareTo(rank);
        if (result > 0) {
            return EngagementOutcome.ATTACKER_LOSES;
        } else if (result < 0) {
            return EngagementOutcome.ATTACKER_WINS;
        } else {
            return EngagementOutcome.BOTH_DIE;
        }
    }

    public Rank getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RankedPiece that = (RankedPiece) o;
        return rank == that.rank;

    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + rank.hashCode();
    }

    @Override
    public String toString() {
        return color + " " + rank;
    }
}
