import History.FileHistory;
import History.History;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class HIstoryTest {

    History history;

    @Before
    public void setUp() {
        history = new FileHistory("historyTest.txt");
    }

    @Test
    public void historyGetTest() throws IOException {
        Set historySet = new HashSet(Arrays.asList("a", "b", "c", "d"));
        history.saveHistory(historySet);

        assertEquals(historySet, history.getHistory());
    }

    @Test
    public void historyDeleteTest() {
        history.deleteHistory();

        assertEquals(new HashSet<>(), history.getHistory());
    }

}
