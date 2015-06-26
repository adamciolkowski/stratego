package boardgames.stratego.piece;

public class Spy extends Piece {

    public Spy(Color color) {
        super(color, Rank.SPY);
    }

    @Override
    public EngagementOutcome attack(Piece piece) {
        if(piece.getRank() == Rank.MARSHALL) {
            return EngagementOutcome.ATTACKER_WINS;
        }
        return super.attack(piece);
    }
}
