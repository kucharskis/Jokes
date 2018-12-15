package History;

import java.util.Set;

public interface History {

    public Set<String> getHistory();
    public boolean saveHistory(Set<String> history);
}
