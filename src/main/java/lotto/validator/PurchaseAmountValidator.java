package lotto.validator;

public class PurchaseAmountValidator {
    private static final int PURCHASE_AMOUNT_LIMIT = 100_000;
    private static final int PURCHASE_AMOUNT_UNIT = 1_000;

    public static void validate(int amount) {
        validatePositive(amount);
        validateLimit(amount);
        validateUnit(amount);
    }

    private static void validateLimit(int amount) {
        if (amount > PURCHASE_AMOUNT_LIMIT) {
            throw new IllegalArgumentException(String.format("%d원 까지만 구매 가능합니다.", PURCHASE_AMOUNT_LIMIT));
        }
    }

    private static void validateUnit(int amount) {
        if (amount % PURCHASE_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(String.format("구입 금액은 %d원 단위로 입력해야 합니다.", PURCHASE_AMOUNT_UNIT));
        }
    }

    private static void validatePositive(int amount) {
        if (amount < PURCHASE_AMOUNT_UNIT) {
            throw new IllegalArgumentException(String.format("구입 금액은 %d원 이상이어야 합니다.", PURCHASE_AMOUNT_UNIT));
        }
    }
}

