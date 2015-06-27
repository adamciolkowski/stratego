package boardgames.stratego.piece.ranked;

import boardgames.stratego.piece.Bomb;
import boardgames.stratego.piece.Color;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class MinerTest {

    @Test
    public void minerCanDefuseBombs() {
        Miner miner = new Miner(Color.BLUE);
        Bomb bomb = new Bomb(Color.RED);

        EngagementOutcome outcome = miner.attack(bomb);

        assertThat(outcome).isEqualTo(EngagementOutcome.ATTACKER_WINS);
    }
}