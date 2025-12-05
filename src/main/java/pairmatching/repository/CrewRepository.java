package pairmatching.repository;

import static pairmatching.domain.Course.BACKEND;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.Course;
import pairmatching.domain.Crew;

public class CrewRepository {
    private static final String FRONT_FILE_PATH = "src/main/resources/frontend-crew.md";
    private static final String BACK_FILE_PATH = "src/main/resources/backend-crew.md";

    private static final String CAN_NOT_LOAD_FILE = "파일을 불러오는 데 문제가 발생했습니다.";

    public List<Crew> loadShuffledCrews(Course course) {
        String filePath = FRONT_FILE_PATH;
        if (course == BACKEND) {
            filePath = BACK_FILE_PATH;
        }
        List<String> names = readFile(filePath);
        List<String> shuffledNames = shuffleNames(names);

        return shuffledNames.stream()
                .map(Crew::new)
                .collect(Collectors.toList());
    }

    private List<String> readFile(String filePath) {
        List<String> names = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String name;
            while ((name = reader.readLine()) != null) {
                names.add(name);
            }
        } catch (Exception e) {
            throw new IllegalStateException(CAN_NOT_LOAD_FILE);
        }
        return names;
    }

    private List<String> shuffleNames(List<String> names) {
        return Randoms.shuffle(names);
    }
}
