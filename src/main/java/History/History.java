package History;

import java.io.IOException;
import java.util.Set;

public interface History {

    public Set<String> getHistory();
    public boolean saveHistory(Set<String> history) throws IOException;
    public boolean deleteHistory();
}
