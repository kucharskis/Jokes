import History.Joke;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class AppToTest {

    public static void main(String[] args) throws UnirestException, IOException {
        // Only one time
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

// Response to Object
//        HttpResponse<Joke> bookResponse = Unirest.get("https://api.chucknorris.io/jokes/random").asObject(Joke.class);
//        Joke bookObject = bookResponse.getBody();
//
//        System.out.println(bookObject);

        HttpResponse<Joke> authorResponse = Unirest.get("https://api.chucknorris.io/jokes/random").asObject(Joke.class);
        Joke authorObject = authorResponse.getBody();
        System.out.println(authorObject);


        List<String> lines = Arrays.asList("The first line");
        Path file = Paths.get("the-file-name1.txt");
        Files.write(file, lines, Charset.forName("UTF-8"));
        File myfile = new File("the-file-name1.txt");
        myfile.setReadOnly();

        try {
            List<String> lines2 = Arrays.asList("The first line", "The second line");
            Path file2 = Paths.get("the-file-name1.txt");
            Files.write(file2, lines2, Charset.forName("UTF-8"));
        } catch (IOException e) {
            System.out.println("error");
        }
    }
}
