import History.*;
import Joke.JokeRetriever;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JokeStreamFormatter {

    private final History history;
    private final JokeRetriever jokeRetriever;

    public JokeStreamFormatter(History history, JokeRetriever jokeRetriever) {
        this.history = history;
        this.jokeRetriever = jokeRetriever;
    }

    List<String> getNewJokes(int howMuch) throws UnirestException, IOException {
        List<String> jokes = new ArrayList();
        int i = 0;
        Set historySet = history.getHistory();

        while (i < howMuch) {
            Joke joke = jokeRetriever.getJoke();
            try {
                if (historySet.add(joke.getId()) && joke.getValue() != null) {
                    jokes.add(joke.getValue());
                    history.saveHistory(historySet);
                    i++;
                }
            } catch (NullPointerException ex) {
                continue;
            }
        }

        return jokes;
    }
}
