package Joke;

import History.Joke;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface JokeRetriever {

    public Joke getJoke() throws UnirestException;
}
