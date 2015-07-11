package boardgames.stratego.piece.ranked;

import boardgames.stratego.Board;
import boardgames.stratego.Position;
import boardgames.stratego.piece.Bomb;
import boardgames.stratego.piece.Color;
import boardgames.stratego.piece.Flag;
import boardgames.stratego.piece.Piece;

import java.util.Set;

import static boardgames.stratego.piece.ranked.EngagementOutcome.*;
import static java.util.stream.Collectors.toSet;

public abstract class RankedPiece extends Piece {

    private final Rank rank;

    public RankedPiece(Color color, Rank rank) {
        super(color);
        this.rank = rank;
    }

    @Override
    public Set<Position> getPossibleMovesFrom(Position position, Board board) {
        return position.adjacent().stream()
                .filter(board::isValid)
                .filter(p -> isEmptyOrOccupiedByEnemy(p, board))
                .collect(toSet());
    }

    private boolean isEmptyOrOccupiedByEnemy(Position position, Board board) {
        Piece piece = board.getPieceAt(position);
        return piece == null || piece.getColor() != color;
    }

    @Override
    public EngagementOutcome attack(Piece piece) {
        if (piece instanceof Flag)
            return ATTACKER_WINS;
        if (piece instanceof Bomb)
            return ATTACKER_LOSES;
        return attackRanked((RankedPiece) piece);
    }

    protected EngagementOutcome attackRanked(RankedPiece piece) {
        int result = piece.rank.compareTo(rank);
        if (result > 0) {
            return ATTACKER_LOSES;
        } else if (result < 0) {
            return ATTACKER_WINS;
        } else {
            return BOTH_DIE;
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
