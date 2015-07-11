package boardgames.stratego;

import boardgames.stratego.piece.Color;
import boardgames.stratego.piece.Piece;
import boardgames.stratego.piece.ranked.General;
import boardgames.stratego.piece.ranked.RankedPiece;
import boardgames.stratego.piece.ranked.Sergeant;
import org.junit.Before;
import org.junit.Test;

import static boardgames.stratego.piece.ranked.EngagementOutcome.*;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BoardTest {

    Board board;

    RankedPiece piece = mock(RankedPiece.class);

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
        board.placePieceAt(Position.of(2, 2), new Sergeant(Color.BLUE));

        Piece piece = board.getPieceAt(Position.of(2, 2));

        assertThat(piece).isEqualTo(new Sergeant(Color.BLUE));
    }

    @Test
    public void possibleMovesFromPositionArePossibleMovesForPieceOccupyingThatPosition() {
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

    @Test
    public void shouldMovePiece() {
        board.placePieceAt(Position.of(2, 2), new Sergeant(Color.BLUE));

        board.movePiece(Position.of(2, 2), Position.of(2, 3));

        assertThat(board.getPieceAt(Position.of(2, 2))).isNull();
        assertThat(board.getPieceAt(Position.of(2, 3))).isEqualTo(new Sergeant(Color.BLUE));
    }

    @Test
    public void whenAttackingPieceWithLowerRankItIsRemovedFromBoardAndTheWinnerTakesItsPlace() {
        when(piece.attack(any(Piece.class))).thenReturn(ATTACKER_WINS);
        board.placePieceAt(Position.of(2, 2), piece);
        board.placePieceAt(Position.of(2, 3), new Sergeant(Color.BLUE));

        board.movePiece(Position.of(2, 2), Position.of(2, 3));

        assertThat(board.getPieceAt(Position.of(2, 2))).isNull();
        assertThat(board.getPieceAt(Position.of(2, 3))).isEqualTo(piece);
    }

    @Test
    public void whenAttackingPieceWithHigherRankAttackerIsRemovedFromBoard() {
        when(piece.attack(any(Piece.class))).thenReturn(ATTACKER_LOSES);
        board.placePieceAt(Position.of(2, 2), piece);
        board.placePieceAt(Position.of(2, 3), new Sergeant(Color.BLUE));

        board.movePiece(Position.of(2, 2), Position.of(2, 3));

        assertThat(board.getPieceAt(Position.of(2, 2))).isNull();
        assertThat(board.getPieceAt(Position.of(2, 3))).isEqualTo(new Sergeant(Color.BLUE));
    }

    @Test
    public void whenAttackingPieceWithSameRankBothPiecesAreRemovedFromBoard() {
        when(piece.attack(any(Piece.class))).thenReturn(BOTH_DIE);
        board.placePieceAt(Position.of(2, 2), piece);
        board.placePieceAt(Position.of(2, 3), new General(Color.BLUE));

        board.movePiece(Position.of(2, 2), Position.of(2, 3));

        assertThat(board.getPieceAt(Position.of(2, 2))).isNull();
        assertThat(board.getPieceAt(Position.of(2, 3))).isNull();
    }

    @Test
    public void shouldNotBeEmptyWhenPieceIsPresent() {
        board.placePieceAt(Position.of(2, 2), new Sergeant(Color.BLUE));

        assertThat(board.isEmpty(Position.of(2, 2))).isFalse();
    }

    @Test
    public void shouldBeEmptyWhenNoPiecePresent() {
        assertThat(board.isEmpty(Position.of(2, 2))).isTrue();
    }
}