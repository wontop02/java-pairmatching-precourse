package pairmatching.view;

import java.util.List;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PRINT_MISSIONS =
            "\n#############################################\n"
                    + "과정: 백엔드 | 프론트엔드\n"
                    + "미션:\n"
                    + "  - 레벨1: 자동차경주 | 로또 | 숫자야구게임\n"
                    + "  - 레벨2: 장바구니 | 결제 | 지하철노선도\n"
                    + "  - 레벨3: \n"
                    + "  - 레벨4: 성능개선 | 배포\n"
                    + "  - 레벨5: \n"
                    + "############################################";
    private static final String PRINT_MATCHING_RESULT = "\n페어 매칭 결과입니다.";
    private static final String PRINT_RESET_COMPLETE = "\n초기화 되었습니다.\n";
    private OutputView() {}

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printMissions() {
        System.out.println(PRINT_MISSIONS);
    }

    public static void printMatchingResult(List<String> pairs) {
        System.out.println(PRINT_MATCHING_RESULT);
        for (String pair : pairs) {
            System.out.println(pair);
        }
        System.out.println();
    }

    public static void printResetComplete() {
        System.out.println(PRINT_RESET_COMPLETE);
    }
}
