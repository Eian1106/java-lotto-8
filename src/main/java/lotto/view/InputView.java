package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import lotto.validator.PurchaseAmountValidator;

public class InputView {

    private static final String LOTTO_NUMBER_DELIMITER = ",";

    public int getPurchaseAmount() {
        while (true) {
            try {
                return readPurchaseAmount();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private int readPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String userInput = getUserInput();

        isNumber(userInput);
        int amount = parseInt(userInput);
        PurchaseAmountValidator.validate(amount);

        return amount;
    }

    public List<Integer> getWinningNumbers() {
        while (true) {
            try {
                return readWinningNumbers();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private List<Integer> readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String userInput = getUserInput();

        return splitNumbers(userInput);
    }

    public int getBonusNumber() {
        while (true) {
            try {
                return readBonusNumber();
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private int readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String userInput = getUserInput();

        isNumber(userInput);

        return parseInt(userInput);
    }

    private List<Integer> splitNumbers(String input) {
        return Arrays.stream(input.split(LOTTO_NUMBER_DELIMITER))
                .map(String::strip)
                .filter(InputView::isNumber)
                .map(InputView::parseInt)
                .toList();
    }

    private String getUserInput() {
        String input = Console.readLine();
        System.out.println();
        validateNotBlank(input);

        return input;
    }

    private static boolean isNumber(String input) {
        if (!input.matches("^[0-9]+$")) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
        return true;
    }

    private static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력한 값이 너무 큽니다.");
        }
    }

    private void validateNotBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("입력값이 비어있습니다.");
        }
    }
}
