package pairmatching.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class MatchingPairs {
    private final Map<Mission, List<Pair>> matchingPairs;

    public MatchingPairs() {
        matchingPairs = new HashMap<>();
    }

    public void addMatchingPairs(Mission mission, List<Pair> pairs) {
        matchingPairs.put(mission, pairs);
    }

    public boolean existDuplicatedPairs(Mission mission, List<Pair> pairs) {
        Set<Set<Crew>> newCrews = pairListToCrewSet(pairs);
        List<List<Pair>> sameLevelPairs = getPairsWithCourseAndLevel(mission.getCourse(), mission.getLevel());
        for (List<Pair> samePairs : sameLevelPairs) {
            Set<Set<Crew>> sameLevelCrews = pairListToCrewSet(samePairs);
            if (newCrews.equals(sameLevelCrews)) {
                return true;
            }
        }
        return false;
    }

    private Set<Set<Crew>> pairListToCrewSet(List<Pair> pairs) {
        Set<Set<Crew>> crews = new HashSet<>();
        for (Pair pair : pairs) {
            crews.add(new HashSet<>(pair.getCrews()));
        }
        return crews;
    }

    private List<List<Pair>> getPairsWithCourseAndLevel(Course course, Level level) {
        Map<Mission, List<Pair>> levelPairs = new HashMap<>(matchingPairs);
        levelPairs = levelPairs.entrySet()
                .stream()
                .filter(entry -> entry.getKey().getCourse() == course
                        && entry.getKey().getLevel() == level)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return new ArrayList<>(levelPairs.values());
    }

    public boolean containsMatchingPairs(Mission mission) {
        return matchingPairs.get(mission) != null;
    }

    public List<Pair> getPairsWithMission(Mission mission) {
        return new ArrayList<>(matchingPairs.get(mission));
    }

    public void clear() {
        matchingPairs.clear();
    }
}
