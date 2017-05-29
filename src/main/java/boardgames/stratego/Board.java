package boardgames.stratego;

import boardgames.stratego.piece.Color;
import boardgames.stratego.piece.Piece;
import boardgames.stratego.piece.ranked.EngagementOutcome;
import boardgames.stratego.piece.ranked.RankedPiece;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;

public class Board {

    private static final int STANDARD_SIZE = 10;

    private final Map<Position, Piece> pieces = new HashMap<>();

    private static final Set<Position> illegalPositions = illegalPositions();

    private final int size;

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

    public Board() {
        this(STANDARD_SIZE);
    }

    public Board(int size) {
        this.size = size;
    }

    public void placePieceAt(Position position, Piece piece) {
        pieces.put(position, piece);
    }

    public Piece getPieceAt(Position position) {
        return pieces.get(position);
    }

    public boolean isEmpty(Position position) {
        return !pieces.containsKey(position);
    }

    public Set<Position> getPossibleMovesFrom(Position position) {
        Piece piece = getPieceAt(position);
        return piece.getPossibleMovesFrom(position, this);
    }

    public boolean isValid(Position position) {
        if (illegalPositions.contains(position))
            return false;
        return position.isWithinBounds(1, size);
    }

    public int getSize() {
        return size;
    }

    public void movePiece(Position origin, Position goal) {
        if (isEmpty(goal)) {
            doMovePiece(origin, goal);
        } else {
            fight(origin, goal);
        }
    }

    private void doMovePiece(Position origin, Position goal) {
        Piece piece = removePieceFrom(origin);
        placePieceAt(goal, piece);
    }

    private void fight(Position attackerPosition, Position defenderPosition) {
        RankedPiece attacker = (RankedPiece) getPieceAt(attackerPosition);
        Piece defender = getPieceAt(defenderPosition);
        EngagementOutcome outcome = attacker.attack(defender);
        outcome.apply(this, attackerPosition, defenderPosition);
    }

    public Piece removePieceFrom(Position position) {
        return pieces.remove(position);
    }

    public boolean isPresentPieceOfColor(Position position, Color color) {
        return !isEmpty(position) && getPieceAt(position).getColor() == color;
    }
}
