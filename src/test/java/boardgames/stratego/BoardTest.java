package boardgames.stratego;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoardTest {

    Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test
    public void shouldReturnNullWhenNoPiecePresent() {
        Piece piece = board.getPieceAt(Position.of(2, 2));

        assertThat(piece).isNull();
    }

    @Test
    public void shouldReturnPieceThatWasPreviouslyPlacedAtGivenPosition() {
        board.placePieceAt(Position.of(2, 2), new Piece());

        Piece piece = board.getPieceAt(Position.of(2, 2));

        assertThat(piece).isEqualTo(new Piece());
    }

    @Test
    public void possibleMovesFromPositionArePossibleMovesForPieceOccupyingThatPosition() {
        Piece piece = mock(Piece.class);
        Position position = Position.of(4, 4);
        board.placePieceAt(position, piece);

        board.getPossibleMovesFrom(position);

        verify(piece).getPossibleMovesFrom(position, board);
    }

    @Test
    public void positionOutsideBoardIsNotValid() {
        assertThat(board.isValid(Position.of(5, 0))).isFalse();
        assertThat(board.isValid(Position.of(5, 11))).isFalse();
    }

    @Test
    public void twoZonesInTheMiddleOfBoardCannotBeEntered() {
        assertThat(board.isValid(Position.of(3, 5))).isFalse();
        assertThat(board.isValid(Position.of(3, 6))).isFalse();
        assertThat(board.isValid(Position.of(4, 5))).isFalse();
        assertThat(board.isValid(Position.of(4, 6))).isFalse();

        assertThat(board.isValid(Position.of(7, 5))).isFalse();
        assertThat(board.isValid(Position.of(7, 6))).isFalse();
        assertThat(board.isValid(Position.of(8, 5))).isFalse();
        assertThat(board.isValid(Position.of(8, 6))).isFalse();
    }
}