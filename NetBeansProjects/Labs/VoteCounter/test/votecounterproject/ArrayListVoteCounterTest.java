package votecounterproject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @version Spring 2019
 * @author clatulip Slightly modified for Fall 2022 by Bruce Long
 * @since 08-29-2022
 */
public class ArrayListVoteCounterTest {

    private ArrayListVoteCounter voteCounter;

    /**
     * Initialization before any test methods are run.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        this.voteCounter = new ArrayListVoteCounter();
        /*
        Data Legend:
            [0] = "Darth Sidius"
            [1] = "Darth Maul"
            [2] = "Darth Vader"
            [3] = "Darth Plagueis"
         */
    }

    /**
     * Tests the recordVote method.
     */
    @Test
    public void recordVote() {
        // 1. Assert both the votes and spoiled votes ArrayLists are empty.
        assertTrue(voteCounter.getVotes().isEmpty());
        assertTrue(voteCounter.getSpoiledVotes().isEmpty());

        // 2. Record some votes for one or more of the candidates. 
        // Use the legend above for clarification.
        voteCounter.recordVote("Darth Sidius"); // 1 vote for Darth Sidius
        voteCounter.recordVote("Darth Maul");   // 1 vote for Darth Maul
        voteCounter.recordVote("Darth Vader");  // 1 vote for Darth Vader
        voteCounter.recordVote("unknown");      // 1 spoiled vote

        // 3. Using the appropriate getter, make sure the candidates actually 
        // received the appropriate votes.
        assertEquals(1, voteCounter.getSithSenateMemberVotes("Darth Sidius"));
        assertEquals(1, voteCounter.getSithSenateMemberVotes("Darth Maul"));
        assertEquals(1, voteCounter.getSithSenateMemberVotes("Darth Vader"));
        assertEquals(0, voteCounter.getSithSenateMemberVotes("Darth Plagueis"));

        // 4. Assert that the size of the votes list has increased correctly.
        assertEquals(4, voteCounter.getVotes().size());
        assertEquals(1, voteCounter.getSpoiledVotes().size());
    }

}
