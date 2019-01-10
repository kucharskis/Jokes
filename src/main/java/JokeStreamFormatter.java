import History.*;
import Joke.JokeRetriever;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
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
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

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
