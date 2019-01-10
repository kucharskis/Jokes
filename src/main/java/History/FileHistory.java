package History;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileHistory implements History {

    private String file;

    public FileHistory(String file) {
        this.file = file;
    }

    @Override
    public Set<String> getHistory() {
        Path filepath = Paths.get(file);
        Charset charset = Charset.forName("UTF-8");

        try {
            List<String> lines = Files.readAllLines(filepath, charset);
            return new HashSet(lines);
        } catch (IOException e) {
            return new HashSet();
        }
    }

    @Override
    public boolean saveHistory(Set<String> history) throws IOException {
        try {
            Path filepath = Paths.get(file);
            Files.write(filepath, history, Charset.forName("UTF-8"));
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteHistory() {
        File history = new File(file);
        return history.delete();
    }
}
