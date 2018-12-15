import History.*;
import Joke.JokeRetriever;

import java.util.List;

public class JokeStreamFormatter {

    private final History history;
    private final JokeRetriever jokeRetriever;

    public JokeStreamFormatter(History history, JokeRetriever jokeRetriever) {
        this.history = history;
        this.jokeRetriever = jokeRetriever;
    }

    List<String> getNewJokes(int howMuch){
        return null;
    }
}
