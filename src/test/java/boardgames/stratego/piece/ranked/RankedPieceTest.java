package boardgames.stratego.piece.ranked;

import boardgames.stratego.Board;
import boardgames.stratego.Position;
import boardgames.stratego.piece.Color;
import boardgames.stratego.piece.Flag;
import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RankedPieceTest {

    Board board = mock(Board.class);

    RankedPiece piece = new Sergeant(Color.BLUE);

    @Test
    public void pieceCanMoveOneSquareHorizontallyOrVertically() {
        when(board.isValid(any())).thenReturn(true);
        when(board.getPieceAt(any())).thenReturn(null);

        Set<Position> possibleMoves = piece.getPossibleMovesFrom(Position.of(3, 3), board);

        assertThat(possibleMoves).containsOnly(Position.of(2, 3), Position.of(4, 3),
                Position.of(3, 2), Position.of(3, 4));
    }

    @Test
    public void pieceCannotMoveBeyondBoard() {
        when(board.isValid(any())).thenAnswer(invocation -> {
            Position p = (Position) invocation.getArguments()[0];
            return !Position.of(0, 4).equals(p);
        });
        when(board.getPieceAt(any())).thenReturn(null);

        Set<Position> possibleMoves = piece.getPossibleMovesFrom(Position.of(1, 4), board);

        assertThat(possibleMoves).containsOnly(Position.of(2, 4), Position.of(1, 3), Position.of(1, 5));
    }

    @Test
    public void pieceCanMoveToSquareOccupiedByPieceOfDifferentColor() {
        when(board.isValid(any())).thenReturn(true);
        when(board.getPieceAt(Position.of(2, 2))).thenReturn(new Sergeant(Color.BLUE));
        when(board.getPieceAt(Position.of(2, 3))).thenReturn(new Sergeant(Color.RED));

        Set<Position> possibleMoves = piece.getPossibleMovesFrom(Position.of(2, 2), board);

        assertThat(possibleMoves).containsOnly(Position.of(1, 2), Position.of(3, 2), Position.of(2, 1),
                Position.of(2, 3));
    }

    @Test
    public void pieceCannotMoveToSquareOccupiedByPieceOfTheSameColor() {
        when(board.isValid(any())).thenReturn(true);
        when(board.getPieceAt(Position.of(2, 2))).thenReturn(new Sergeant(Color.BLUE));
        when(board.getPieceAt(Position.of(2, 3))).thenReturn(new Sergeant(Color.BLUE));

        Set<Position> possibleMoves = piece.getPossibleMovesFrom(Position.of(2, 2), board);

        assertThat(possibleMoves).containsOnly(Position.of(1, 2), Position.of(3, 2), Position.of(2, 1));
    }

    @Test
    public void pieceWithHigherRankDefeatsPieceWithLowerRank() {
        General general = new General(Color.BLUE);
        Scout scout = new Scout(Color.RED);

        EngagementOutcome outcome = general.attack(scout);

        assertThat(outcome).isEqualTo(EngagementOutcome.ATTACKER_WINS);
    }

    @Test
    public void pieceWithLowerRankLosesToPieceWithHigherRank() {
        General general = new General(Color.BLUE);
        Scout scout = new Scout(Color.RED);

        EngagementOutcome outcome = scout.attack(general);

        assertThat(outcome).isEqualTo(EngagementOutcome.ATTACKER_LOSES);
    }

    @Test
    public void whenPieceAttacksAnotherPieceWithTheSameRankBothLose() {
        Scout blueScout = new Scout(Color.BLUE);
        Scout redScout = new Scout(Color.RED);

        EngagementOutcome outcome = redScout.attack(blueScout);

        assertThat(outcome).isEqualTo(EngagementOutcome.BOTH_DIE);
    }

    @Test
    public void anyPieceCanCaptureFlag() {
        Spy spy = new Spy(Color.BLUE);
        Flag flag = new Flag(Color.RED);

        EngagementOutcome outcome = spy.attack(flag);

        assertThat(outcome).isEqualTo(EngagementOutcome.ATTACKER_WINS);
    }
}