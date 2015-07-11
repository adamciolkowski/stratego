package boardgames.stratego.piece;

public class Flag extends ImmobilePiece {

    public Flag(Color color) {
        super(color);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Flag && super.equals(o);
    }
}
