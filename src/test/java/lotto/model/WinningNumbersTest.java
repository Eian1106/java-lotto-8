package lotto.model;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class WinningNumbersTest {

    private Lotto lotto;

    @BeforeEach
    void setUp() {
        lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
    }

    @DisplayName("정상적인 당첨 번호와 보너스 번호를 입력하면 예외가 발생하지 않는다.")
    @Test
    void 정상_생성_성공() {
        assertThatCode(() -> new WinningNumbers(lotto, 7))
                .doesNotThrowAnyException();
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다.")
    @Test
    void 보너스번호_중복_예외() {
        assertThatThrownBy(() -> new WinningNumbers(lotto, 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다. (1 미만)")
    @Test
    void 보너스번호_하한_미만_예외() {
        assertThatThrownBy(() -> new WinningNumbers(lotto, 0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외가 발생한다. (45 초과)")
    @Test
    void 보너스번호_상한_초과_예외() {
        assertThatThrownBy(() -> new WinningNumbers(lotto, 46))
                .isInstanceOf(IllegalArgumentException.class);
    }
}