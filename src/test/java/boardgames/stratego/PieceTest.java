package boardgames.stratego;

import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class PieceTest {

    @Test
    public void pieceCanMoveOneSquareHorizontallyOrVertically() {
        Piece piece = new Piece();

        Set<Position> possibleMoves = piece.getPossibleMovesFrom(Position.of(4, 4));

        assertThat(possibleMoves).containsOnly(Position.of(3, 4), Position.of(5, 4),
                Position.of(4, 3), Position.of(4, 5));
    }
}