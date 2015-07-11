package boardgames.stratego.piece.ranked;

import boardgames.stratego.piece.Bomb;
import boardgames.stratego.piece.Color;
import boardgames.stratego.piece.Piece;

import static boardgames.stratego.piece.ranked.EngagementOutcome.ATTACKER_WINS;

public class Miner extends RankedPiece {

    public Miner(Color color) {
        super(color, Rank.MINER);
    }

    @Override
    public EngagementOutcome attack(Piece piece) {
        if(piece instanceof Bomb)
            return ATTACKER_WINS;
        return super.attack(piece);
    }
}
