package lotto.validator;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class PurchaseAmountValidatorTest {

    @DisplayName("정상 금액(1,000원 단위, 범위 내)은 통과한다")
    @ParameterizedTest(name = "유효 금액: {0}원")
    @ValueSource(ints = {1_000, 2_000, 50_000, 100_000})
    void valid_amounts_pass(int amount) {
        assertThatCode(() -> PurchaseAmountValidator.validate(amount))
                .doesNotThrowAnyException();
    }

    @DisplayName("구입 금액이 최소 단위(1,000원) 미만이면 예외")
    @ParameterizedTest(name = "하한 미만: {0}원")
    @ValueSource(ints = {0, -1, 999})
    void below_min_throws(int amount) {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 상한(100,000원) 초과면 예외")
    @Test
    void over_limit_throws() {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(100_001))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 금액이 1,000원 단위가 아니면 예외")
    @ParameterizedTest(name = "단위 미준수: {0}원")
    @ValueSource(ints = {1_500, 2_500, 10_001, 99_999})
    void not_unit_throws(int amount) {
        assertThatThrownBy(() -> PurchaseAmountValidator.validate(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }
}