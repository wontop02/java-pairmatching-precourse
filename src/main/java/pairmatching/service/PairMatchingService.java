package pairmatching.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.domain.Crew;
import pairmatching.domain.MatchingPairs;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.repository.CrewRepository;

public class PairMatchingService {
    private static final String CAN_NOT_MATCHING = "같은 레벨에서 중복된 페어 매칭이 발생합니다.";
    private static final String INVALID_CREW_SIZE = "페어 매칭은 2명 이상부터 가능합니다.";
    private final CrewRepository crewRepository;

    public PairMatchingService(CrewRepository crewRepository) {
        this.crewRepository = crewRepository;
    }

    public List<Pair> matchingTry(Mission mission, MatchingPairs matchingPairs) {
        int i = 0;
        List<Pair> pairs = new ArrayList<>();
        while (i < 3) {
            List<Crew> crews = crewRepository.loadShuffledCrews(mission.getCourse());
            pairs = matchingPair(crews);
            if (!matchingPairs.existDuplicatedPairs(mission, pairs)) {
                break;
            }
            i++;
        }
        if (i >= 3) {
            throw new IllegalArgumentException(CAN_NOT_MATCHING);
        }
        return pairs;
    }

    private List<Pair> matchingPair(List<Crew> crews) {
        List<Pair> pairs = new ArrayList<>();
        if (crews.size() <= 1) {
            throw new IllegalStateException(INVALID_CREW_SIZE);
        }
        for (int i = 0; i < crews.size(); i += 2) {
            if (i == crews.size() - 3) {
                List<Crew> pair = new ArrayList<>(Arrays.asList(crews.get(i), crews.get(i + 1), crews.get(i + 2)));
                pairs.add(new Pair(pair));
                break;
            }
            List<Crew> pair = new ArrayList<>(Arrays.asList(crews.get(i), crews.get(i + 1)));
            pairs.add(new Pair(pair));
        }
        return pairs;
    }
}
