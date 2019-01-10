package Joke;

import History.Joke;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RESTJokeRetriever implements JokeRetriever {

    @Override
    public Joke getJoke() throws UnirestException {
        HttpResponse<Joke> jokeResponse = Unirest.get("https://api.chucknorris.io/jokes/random").asObject(Joke.class);
        return jokeResponse.getBody();
    }
}
