package pairmatching.domain;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private static final String INVALID_COURSE = "존재하지 않는 과정입니다.";

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course valueOfCourse(String name) {
        return Arrays.stream(values())
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_COURSE));
    }

    public String getName() {
        return name;
    }
}
