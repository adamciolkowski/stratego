package boardgames.stratego.piece.ranked;

import boardgames.stratego.Board;
import boardgames.stratego.Position;
import boardgames.stratego.piece.Piece;

public enum EngagementOutcome {
    ATTACKER_WINS {
        @Override
        public void apply(Board board, Position attackerPosition, Position defenderPosition) {
            Piece attacker = board.removePieceFrom(attackerPosition);
            board.placePieceAt(defenderPosition, attacker);
        }
    },
    ATTACKER_LOSES {
        @Override
        public void apply(Board board, Position attackerPosition, Position defenderPosition) {
            board.removePieceFrom(attackerPosition);
        }
    },
    BOTH_DIE {
        @Override
        public void apply(Board board, Position attackerPosition, Position defenderPosition) {
            board.removePieceFrom(attackerPosition);
            board.removePieceFrom(defenderPosition);
        }
    };

    public abstract void apply(Board board, Position attackerPosition, Position defenderPosition);
}
