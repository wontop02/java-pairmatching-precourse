package pairmatching.domain;

import static pairmatching.domain.Level.LEVEL1;
import static pairmatching.domain.Level.LEVEL2;
import static pairmatching.domain.Level.LEVEL4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Mission {
    private static final Set<String> LEVEL1_MISSIONS =
            new HashSet<>(Arrays.asList("자동차경주", "로또", "숫자야구게임"));
    private static final Set<String> LEVEL2_MISSIONS =
            new HashSet<>(Arrays.asList("장바구니", "결제", "지하철노선도"));
    private static final Set<String> LEVEL4_MISSIONS =
            new HashSet<>(Arrays.asList("성능개선", "배포"));

    private static final String INVALID_MISSION_NAME = "해당 레벨의 미션을 정확히 입력해 주세요.";

    private final Course course;
    private final Level level;
    private final String name;

    public Mission(Course course, Level level, String name) {
        this.course = course;
        this.level = level;
        validateMissionName(name, level);
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mission mission = (Mission) o;
        return course == mission.course &&
                level == mission.level &&
                name.equals(mission.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course, level, name);
    }

    private void validateMissionName(String name, Level level) {
        if (level == LEVEL1) {
            validateLevel1Mission(name);
        }
        if (level == LEVEL2) {
            validateLevel2Mission(name);
        }
        if (level == LEVEL4) {
            validateLevel4Mission(name);
        }
    }

    private void validateLevel1Mission(String name) {
        if (!LEVEL1_MISSIONS.contains(name)) {
            throw new IllegalArgumentException(INVALID_MISSION_NAME);
        }
    }

    private void validateLevel2Mission(String name) {
        if (!LEVEL2_MISSIONS.contains(name)) {
            throw new IllegalArgumentException(INVALID_MISSION_NAME);
        }
    }

    private void validateLevel4Mission(String name) {
        if (!LEVEL4_MISSIONS.contains(name)) {
            throw new IllegalArgumentException(INVALID_MISSION_NAME);
        }
    }

    public Course getCourse() {
        return this.course;
    }

    public Level getLevel() {
        return this.level;
    }
}
