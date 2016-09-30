package boardgames.stratego.piece.ranked;

import boardgames.stratego.Board;
import boardgames.stratego.Direction;
import boardgames.stratego.Position;
import boardgames.stratego.piece.Color;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.codepoetics.protonpack.StreamUtils.takeWhile;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.iterate;

public class Scout extends RankedPiece {

    public Scout(Color color) {
        super(color, Rank.SCOUT);
    }

    @Override
    public Set<Position> getPossibleMovesFrom(Position position, Board board) {
        return stream(Direction.values())
                .flatMap(direction -> allMovesInDirection(direction, position, board))
                .collect(toSet());
    }

    private Stream<Position> allMovesInDirection(Direction direction, Position origin, Board board) {
        return takeWhile(iterate(direction.next(origin), direction::next), canMove(board, direction));
    }

    private Predicate<Position> canMove(Board board, Direction direction) {
        return position -> board.isValid(position) &&
                !board.isPresentPieceOfColor(position, color) &&
                board.isEmpty(direction.previous(position));
    }

}
