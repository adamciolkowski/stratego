package boardgames.stratego;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class Board {

    private static final int STANDARD_SIZE = 10;

    private final Map<Position, Piece> pieces = new HashMap<>();

    private static final Set<Position> illegalPositions = illegalPositions();

    private static Set<Position> illegalPositions() {
        Set<Position> positions = new HashSet<>();
        positions.add(Position.of(3, 5));
        positions.add(Position.of(3, 6));
        positions.add(Position.of(4, 5));
        positions.add(Position.of(4, 6));
        positions.add(Position.of(7, 5));
        positions.add(Position.of(7, 6));
        positions.add(Position.of(8, 5));
        positions.add(Position.of(8, 6));
        return unmodifiableSet(positions);
    }

    public void placePieceAt(Position position, Piece piece) {
        pieces.put(position, piece);
    }

    public Piece getPieceAt(Position position) {
        return pieces.get(position);
    }

    public Set<Position> getPossibleMovesFrom(Position position) {
        return getPieceAt(position).getPossibleMovesFrom(position, this);
    }

    public boolean isValid(Position position) {
        if(illegalPositions.contains(position))
            return false;
        return position.isWithinBounds(1, getSize());
    }

    public int getSize() {
        return STANDARD_SIZE;
    }
}