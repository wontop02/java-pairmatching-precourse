package pairmatching.controller;

import java.util.List;
import pairmatching.domain.MatchingPairs;
import pairmatching.domain.Mission;
import pairmatching.domain.Pair;
import pairmatching.repository.CrewRepository;
import pairmatching.service.PairMatchingService;
import pairmatching.util.InputValidator;
import pairmatching.util.Parser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class PairMatchingController {
    private static final String NOT_FOUND_MATCHING_HISTORY = "매칭 이력이 없습니다.";
    private static final String PAIR_MATCHING_FUNCTION = "1";
    private static final String PAIR_READ_FUNCTION = "2";
    private static final String PAIR_RESET_FUNCTION = "3";
    private static final String END_FUNCTION = "Q";
    private static final String YES = "네";

    public void start() {
        CrewRepository crewRepository = new CrewRepository();
        PairMatchingService service = new PairMatchingService(crewRepository);
        MatchingPairs matchingPairs = new MatchingPairs();
        while (true) {
            try {
                String function = InputView.requestFunction();
                InputValidator.validateFunctionInput(function);
                if (function.equals(END_FUNCTION)) {
                    return;
                }
                startFunction(function, service, matchingPairs);
            } catch (Exception e) {
                OutputView.printErrorMessage(e.getMessage());
                return;
            }
        }
    }

    private void startFunction(String function, PairMatchingService service, MatchingPairs matchingPairs) {
        if (function.equals(PAIR_MATCHING_FUNCTION)) {
            OutputView.printMissions();
            matchingFunction(service, matchingPairs);
        }
        if (function.equals(PAIR_READ_FUNCTION)) {
            OutputView.printMissions();
            readPairFunction(matchingPairs);
        }
        if (function.equals(PAIR_RESET_FUNCTION)) {
            matchingPairs.clear();
            OutputView.printResetComplete();
        }
    }

    private void matchingFunction(PairMatchingService service, MatchingPairs matchingPairs) {
        while (true) {
            try {
                Mission mission = makeMission();
                if (matchingPairs.containsMatchingPairs(mission) && isReMatching()) {
                    continue;
                }
                List<Pair> pairs = service.matchingTry(mission, matchingPairs);
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

    private boolean isReMatching() {
        String reMatchingInput = InputView.requestReMatching();
        InputValidator.validateReMatchingInput(reMatchingInput);
        return reMatchingInput.equals(YES);
    }

    private void readPairFunction(MatchingPairs matchingPairs) {
        try {
            Mission mission = makeMission();
            if (!matchingPairs.containsMatchingPairs(mission)) {
                throw new IllegalArgumentException(NOT_FOUND_MATCHING_HISTORY);
            }
            List<String> matchingResult = Parser.parsePairToString(matchingPairs.getPairsWithMission(mission));
            OutputView.printMatchingResult(matchingResult);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private Mission makeMission() {
        String input = InputView.requestMission();
        return Parser.parseMission(input);
    }
}
