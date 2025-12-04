package pairmatching.controller;

import java.util.List;
import pairmatching.domain.Crew;
import pairmatching.domain.MatchingPairs;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.service.PairMatchingService;
import pairmatching.util.InputValidator;
import pairmatching.util.Parser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private static final String NOT_FOUND_MATCHING_HISTORY = "매칭 이력이 없습니다.";
    private static final String CAN_NOT_MATCHING = "같은 레벨에서 중복된 페어 매칭이 발생합니다.";

    public void start() {
        PairMatchingService service = new PairMatchingService();
        MatchingPairs matchingPairs = new MatchingPairs();
        while(true) {
            try {
                String function = InputView.requestFunction();
                InputValidator.validateFunctionInput(function);
                if (function.equals("1")) {
                    matchingFunction(service, matchingPairs);
                }
                if (function.equals("2")) {
                    readPairFunction(matchingPairs);
                }
                if (function.equals("3")){
                    matchingPairs = new MatchingPairs();
                    OutputView.printResetComplete();
                }
                if (function.equals("Q")) {
                    return;
                }
            } catch (Exception e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void matchingFunction(PairMatchingService service, MatchingPairs matchingPairs) {
        OutputView.printMissions();
        while (true) {
            try {
                String input = InputView.requestMission();
                Mission mission = Parser.parseMission(input);
                if (matchingPairs.containsMatchingPairs(mission)) {
                    String reMatchingInput = InputView.requestReMatching();
                    InputValidator.validateReMatchingInput(reMatchingInput);
                    if (reMatchingInput.equals("아니오")) {
                        continue;
                    }
                }
                List<Crew> crews = service.loadShuffledCrews(mission.getCourse());
                List<Pair> pairs = service.matchingPair(crews);
                if (matchingPairs.existDuplicatedPairs(mission, pairs)) {
                    int i = 0;
                    while (i < 3) {
                        crews = service.loadShuffledCrews(mission.getCourse());
                        pairs = service.matchingPair(crews);
                        if (!matchingPairs.existDuplicatedPairs(mission, pairs)) {
                            break;
                        }
                        i++;
                    }
                    if (i >= 3) {
                        throw new IllegalArgumentException(CAN_NOT_MATCHING);
                    }
                }
                matchingPairs.addMatchingPairs(mission, pairs);
                List<String> matchingResult = Parser.parsePairToString(pairs);
                OutputView.printMatchingResult(matchingResult);
                break;
            } catch (Exception e) {
                OutputView.printErrorMessage(e.getMessage());
                break;
            }
        }
    }

    private void readPairFunction(MatchingPairs matchingPairs) {
        try {
            OutputView.printMissions();
            String input = InputView.requestMission();
            Mission mission = Parser.parseMission(input);
            if (!matchingPairs.containsMatchingPairs(mission)) {
                throw new IllegalArgumentException(NOT_FOUND_MATCHING_HISTORY);
            }
            List<String> matchingResult = Parser.parsePairToString(matchingPairs.getPairsWithMission(mission));
            OutputView.printMatchingResult(matchingResult);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }
}
