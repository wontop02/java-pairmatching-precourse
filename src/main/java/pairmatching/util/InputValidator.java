package pairmatching.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InputValidator {
    private static final Set<String> FUNCTION_INPUT =
            new HashSet<>(Arrays.asList("1", "2", "3", "Q"));
    private static final Set<String> REMATCHING_INPUT =
            new HashSet<>(Arrays.asList("네", "아니오"));

    private static final String INVALID_FUNCTION_INPUT = "기능을 정확히 선택해 주세요.";
    private static final String INVALID_REMATCHING_INPUT = "네 | 아니오 중 하나를 정확히 입력해 주세요.";

    private InputValidator() {}

    public static void validateFunctionInput(String input) {
        if (!FUNCTION_INPUT.contains(input)) {
            throw new IllegalArgumentException(INVALID_FUNCTION_INPUT);
        }
    }

    public static void validateReMatchingInput(String input) {
        if (!REMATCHING_INPUT.contains(input)) {
            throw new IllegalArgumentException(INVALID_REMATCHING_INPUT);
        }
    }
}
