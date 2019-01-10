import History.History;
import Joke.JokeRetriever;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import History.*;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JokeStreamFormatterTest {

    @Mock
    private JokeRetriever jokeRetriever;

    @Mock
    private History history;

    @Test
    public void avoidRepeatingJokesTest() throws UnirestException, IOException {
        when(history.getHistory()).thenReturn(new HashSet<String>(Arrays.asList("1", "2", "3", "4")));
        Joke joke1 = new Joke();
        joke1.setId("1");
        joke1.setValue("joke1");
        Joke joke2 = new Joke();
        joke2.setId("2");
        joke2.setValue("joke2");
        Joke joke3 = new Joke();
        joke3.setId("3");
        joke3.setValue("joke3");
        Joke joke5 = new Joke();
        joke5.setId("5");
        joke5.setValue("joke5");
        Joke joke6 = new Joke();
        joke6.setId("5");
        joke6.setValue("joke5");
        when(jokeRetriever.getJoke()).thenReturn(joke1, joke2, joke3, joke5, joke6);
        when(history.saveHistory(anySet())).thenReturn(true);
        JokeStreamFormatter jokeStreamFormatter = new JokeStreamFormatter(history, jokeRetriever);

        assertEquals(Arrays.asList("joke5"), jokeStreamFormatter.getNewJokes(1));
    }

    @Test
    public void nullTest() throws UnirestException, IOException {
        when(history.getHistory()).thenReturn(new HashSet<String>());
        Joke joke5 = null;
        Joke joke6 = new Joke();
        joke6.setId("5");
        joke6.setValue("joke5");
        when(jokeRetriever.getJoke()).thenReturn(joke5, joke6);
        when(history.saveHistory(anySet())).thenReturn(true);
        JokeStreamFormatter jokeStreamFormatter = new JokeStreamFormatter(history, jokeRetriever);

        assertEquals(Arrays.asList("joke5"), jokeStreamFormatter.getNewJokes(1));
    }

    @Test
    public void returnTest() throws UnirestException, IOException {
        when(history.getHistory()).thenReturn(new HashSet<String>(Arrays.asList("2", "3")));
        Joke joke1 = new Joke();
        joke1.setId("1");
        joke1.setValue("joke1");
        Joke joke2 = new Joke();
        joke2.setId("2");
        joke2.setValue("joke2");
        Joke joke3 = new Joke();
        joke3.setId("3");
        joke3.setValue("joke3");
        Joke joke5 = new Joke();
        joke5.setId("5");
        joke5.setValue("joke5");
        Joke joke6 = new Joke();
        joke6.setId("5");
        joke6.setValue("joke5");
        Joke joke7 = new Joke();
        joke7.setId("7");
        joke7.setValue("joke7");
        Joke joke8 = new Joke();
        joke8.setId("8");
        joke8.setValue("joke8");

        when(jokeRetriever.getJoke()).thenReturn(joke1, joke2, joke3, joke5, joke6, joke7, joke8);
        when(history.saveHistory(anySet())).thenReturn(true);
        JokeStreamFormatter jokeStreamFormatter = new JokeStreamFormatter(history, jokeRetriever);

        assertEquals(Arrays.asList("joke1", "joke5", "joke7"), jokeStreamFormatter.getNewJokes(3));
    }
}
