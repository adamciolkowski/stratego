package boardgames.stratego.piece.ranked;

import boardgames.stratego.piece.Color;

import static boardgames.stratego.piece.ranked.EngagementOutcome.ATTACKER_WINS;

public class Spy extends RankedPiece {

    public Spy(Color color) {
        super(color, 1);
    }

    @Override
    public EngagementOutcome attackRanked(RankedPiece piece) {
        if (piece instanceof Marshall)
            return ATTACKER_WINS;
        return super.attackRanked(piece);
    }
}
