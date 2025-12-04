package pairmatching.service;
import static pairmatching.domain.Course.BACKEND;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;
import pairmatching.domain.Pair;

public class PairMatchingService {
    private static final String FRONT_FILE_PATH = "src/main/resources/frontend-crew.md";
    private static final String BACK_FILE_PATH = "src/main/resources/backend-crew.md";

    public PairMatchingService() {}

    public List<Crew> loadShuffledCrews(Course course) {
        String filePath = FRONT_FILE_PATH;
        if (course == BACKEND) {
            filePath = BACK_FILE_PATH;
        }
        List<String> names = readFile(filePath);
        return names.stream()
                .map(Crew::new)
                .collect(Collectors.toList());
    }

    private List<String> readFile(String filePath) {
        List<String> names = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String name;
            while ((name = reader.readLine()) != null) {
                names.add(name);
            }
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return shuffleNames(names);
    }

    private List<String> shuffleNames(List<String> names) {
        return Randoms.shuffle(names);
    }

    public List<Pair> matchingPair(List<Crew> crews) {
        List<Pair> pairs = new ArrayList<>();
        for (int i = 0; i < crews.size() - 1; i += 2) {
            List<Crew> crew = new ArrayList<>();
            crew.add(crews.get(i));
            crew.add(crews.get(i + 1));
            if (i + 2 == crews.size() - 1) {
                crew.add(crews.get(i + 2));
                i += 100;
            }
            Pair pair = new Pair(crew);
            pairs.add(pair);
        }
        return pairs;
    }
}
