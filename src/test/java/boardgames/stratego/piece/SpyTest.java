package boardgames.stratego.piece;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class SpyTest {

    @Test
    public void spyDefeatsMarshallIfHeAttacksFirst() {
        Spy spy = new Spy(Color.BLUE);
        Marshall marshall = new Marshall(Color.RED);

        EngagementOutcome outcome = spy.attack(marshall);

        assertThat(outcome).isEqualTo(EngagementOutcome.ATTACKER_WINS);
    }
}