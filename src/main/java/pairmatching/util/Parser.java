package pairmatching.util;

import static pairmatching.domain.Course.valueOfCourse;
import static pairmatching.domain.Level.valueOfLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;

public class Parser {
    private Parser() {}

    public static Mission parseMission(String input) {
        input = input.replace(" ", "");
        List<String> inputs = Arrays.asList(input.split(","));
        Course course = valueOfCourse(inputs.get(0));
        Level level = valueOfLevel(inputs.get(1));
        String name = inputs.get(2);
        return new Mission(course, level, name);
    }

    public static List<String> parsePairToString(List<Pair> pairs) {
        List<String> result = new ArrayList<>();
        for (Pair pair : pairs) {
            result.add(pair.toString());
        }
        return result;
    }
}
