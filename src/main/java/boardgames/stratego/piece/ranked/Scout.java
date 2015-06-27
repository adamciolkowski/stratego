package boardgames.stratego.piece.ranked;

import boardgames.stratego.Board;
import boardgames.stratego.Position;
import boardgames.stratego.piece.Color;

import java.util.Set;
import java.util.function.UnaryOperator;

import static boardgames.stratego.Position.allDirections;
import static com.codepoetics.protonpack.StreamUtils.takeWhile;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.iterate;

public class Scout extends RankedPiece {

    public Scout(Color color) {
        super(color, Rank.SCOUT);
    }

    @Override
    public Set<Position> getPossibleMovesFrom(Position position, Board board) {
        return allDirections().stream()
                .flatMap(direction -> allMovesInDirection(direction, position, board).stream())
                .collect(toSet());
    }

    private Set<Position> allMovesInDirection(UnaryOperator<Position> direction, Position origin, Board board) {
        return takeWhile(iterate(direction.apply(origin), direction), board::isValid).collect(toSet());
    }

}
