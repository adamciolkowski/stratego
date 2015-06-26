package boardgames.stratego;

import java.util.HashSet;
import java.util.Set;

public class Piece {

    public Set<Position> getPossibleMovesFrom(Position position) {
        Set<Position> moves = new HashSet<>();
        moves.add(position.above());
        moves.add(position.below());
        moves.add(position.left());
        moves.add(position.right());
        return moves;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
