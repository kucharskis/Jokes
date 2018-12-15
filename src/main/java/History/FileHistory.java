package History;

import java.util.Set;

public class FileHistory implements History {

    @Override
    public Set<String> getHistory() {
        return null;
    }

    @Override
    public boolean saveHistory(Set<String> history) {
        return false;
    }
}
