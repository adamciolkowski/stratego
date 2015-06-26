package boardgames.stratego;

import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public class Piece {

    public Set<Position> getPossibleMovesFrom(Position position, Board board) {
        return probableMovesFrom(position).stream()
                .filter(board::isValid)
                .collect(toSet());
    }

    private List<Position> probableMovesFrom(Position p) {
        return asList(p.above(), p.below(), p.left(), p.right());
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }
}
