package boardgames.stratego.piece;

public class Bomb extends ImmobilePiece {

    public Bomb(Color color) {
        super(color);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Bomb && super.equals(o);
    }
}
