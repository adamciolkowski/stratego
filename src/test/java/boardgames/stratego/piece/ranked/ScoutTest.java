package boardgames.stratego.piece.ranked;

import boardgames.stratego.Board;
import boardgames.stratego.Position;
import boardgames.stratego.piece.Color;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ScoutTest {

    Scout scout = new Scout(Color.BLUE);

    Position origin = Position.of(5, 3);

    Board board = mock(Board.class);

    @Test
    public void scoutCanMoveAnyNumberOfSquaresHorizontallyOrVertically() {
        when(board.isValid(any())).thenAnswer(invocation -> {
            Position p = (Position) invocation.getArguments()[0];
            return p.isWithinBounds(1, 10);
        });

        Set<Position> possibleMoves = scout.getPossibleMovesFrom(origin, board);

        assertThat(possibleMoves).containsOnly(allVerticalAndHorizontalMoves(origin, 10));
    }

    private Position[] allVerticalAndHorizontalMoves(Position origin, int boardSize) {
        Set<Position> moves = new HashSet<>();
        moves.addAll(allVertical(origin, boardSize));
        moves.addAll(allHorizontal(origin, boardSize));
        return moves.toArray(new Position[moves.size()]);
    }

    private Set<Position> allVertical(Position origin, int boardSize) {
        Set<Position> moves = new HashSet<>();
        for (int i = 1; i <= boardSize; i++) {
            if (i != origin.getY())
                moves.add(Position.of(origin.getX(), i));
        }
        return moves;
    }

    private Set<Position> allHorizontal(Position origin, int boardSize) {
        Set<Position> moves = new HashSet<>();
        for (int i = 1; i <= boardSize; i++) {
            if (i != origin.getX())
                moves.add(Position.of(i, origin.getY()));
        }
        return moves;
    }
}