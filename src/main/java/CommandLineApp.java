import History.*;
import Joke.JokeRetriever;
import Joke.RESTJokeRetriever;
import picocli.CommandLine.*;

public class CommandLineApp {

    @Option(names = {"-a", "--Amount"})
    int quantity = 10;
    @Option(names = {"-j", "--Jokes"})
    boolean jokes;
    @Option(names = {"-d", "--Delete"})
    boolean delete;


    public void run() throws Exception {
        History history = new FileHistory("history.txt");
        if (jokes) {
            JokeRetriever jokeRetriever = new RESTJokeRetriever();

            JokeStreamFormatter jokeStreamFormatter = new JokeStreamFormatter(history, jokeRetriever);
            StringBuilder stringBuilder = new StringBuilder();
            for (String joke : jokeStreamFormatter.getNewJokes(quantity)) {
                stringBuilder.append(joke + "\n");
            }
            System.out.println(stringBuilder.toString());
        }
        if (delete) {
            history.deleteHistory();
        }
    }
}
