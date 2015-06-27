package boardgames.stratego.piece.ranked;

import boardgames.stratego.piece.Color;

public class Spy extends RankedPiece {

    public Spy(Color color) {
        super(color, Rank.SPY);
    }

    @Override
    public EngagementOutcome attackRanked(RankedPiece piece) {
        if (piece.getRank() == Rank.MARSHALL) {
            return EngagementOutcome.ATTACKER_WINS;
        }
        return super.attackRanked(piece);
    }
}
