package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String REQUEST_FUNCTION =
            "기능을 선택하세요.\n"
                    + "1. 페어 매칭\n"
                    + "2. 페어 조회\n"
                    + "3. 페어 초기화\n"
                    + "Q. 종료";

    private static final String REQUEST_MISSION =
            "과정, 레벨, 미션을 선택하세요.\n"
                    + "ex) 백엔드, 레벨1, 자동차경주";

    private static final String REQUEST_RE_MATCHING =
            "\n매칭 정보가 있습니다. 다시 매칭하시겠습니까?\n"
                    + "네 | 아니오";

    public static String requestFunction() {
        System.out.println(REQUEST_FUNCTION);
        return Console.readLine();
    }

    public static String requestMission() {
        System.out.println(REQUEST_MISSION);
        return Console.readLine();
    }

    public static String requestReMatching() {
        System.out.println(REQUEST_RE_MATCHING);
        return Console.readLine();
    }
}
