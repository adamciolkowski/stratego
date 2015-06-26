package boardgames.stratego;

import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class PieceTest {

    Piece piece = new Piece();

    @Test
    public void pieceCanMoveOneSquareHorizontallyOrVertically() {
        Set<Position> possibleMoves = piece.getPossibleMovesFrom(Position.of(3, 3), new Board());

        assertThat(possibleMoves).containsOnly(Position.of(2, 3), Position.of(4, 3),
                Position.of(3, 2), Position.of(3, 4));
    }

    @Test
    public void pieceCannotMoveBeyondBoard() {
        Set<Position> possibleMoves = piece.getPossibleMovesFrom(Position.of(1, 4), new Board());

        assertThat(possibleMoves).containsOnly(Position.of(2, 4), Position.of(1, 3), Position.of(1, 5));
    }
}