package boardgames.stratego.piece;

import boardgames.stratego.Board;
import boardgames.stratego.Position;
import boardgames.stratego.piece.ranked.EngagementOutcome;

import java.util.Set;

import static java.util.Collections.emptySet;

public abstract class ImmobilePiece extends Piece {

    public ImmobilePiece(Color color) {
        super(color);
    }

    @Override
    public Set<Position> getPossibleMovesFrom(Position position, Board board) {
        return emptySet();
    }

    @Override
    public EngagementOutcome attack(Piece piece) {
        throw new UnsupportedOperationException();
    }
}
