package pairmatching.domain;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Pair {
    private final List<Crew> crews;

    public Pair(List<Crew> crews) {
        this.crews = crews;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < crews.size() - 1; i++) {
            result.append(crews.get(i).getName()).append(" : ");
        }
        result.append(crews.get(crews.size() - 1).getName());
        return result.toString();
    }

    public List<Crew> getCrews() {
        return new ArrayList<>(crews);
    }
}
