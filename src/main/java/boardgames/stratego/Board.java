package boardgames.stratego;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {

    private final Map<Position, Piece> pieces = new HashMap<>();

    public void placePieceAt(Position position, Piece piece) {
        pieces.put(position, piece);
    }

    public Piece getPieceAt(Position position) {
        return pieces.get(position);
    }

    public Set<Position> getPossibleMovesFrom(Position position) {
        return getPieceAt(position).getPossibleMovesFrom(position);
    }
}
