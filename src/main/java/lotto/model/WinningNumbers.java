package lotto.model;

import java.util.List;

public class WinningNumbers {
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;

    private final Lotto winningLotto;
    private final int bonusNumber;

    public WinningNumbers(Lotto winningLotto, int bonusNumber) {
        validateNumberRange(bonusNumber);
        validateBonusNumberDuplicate(winningLotto, bonusNumber);
        this.winningLotto = winningLotto;
        this.bonusNumber = bonusNumber;
    }

    private void validateBonusNumberDuplicate(Lotto winningLotto, int bonusNumber) {
        List<Integer> numbers = winningLotto.getNumbers();
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("당첨번호와 보너스 번호는 중복될 수 없습니다.");
        }
    }

    private void validateNumberRange(Integer number) {
        if(number < LOTTO_NUMBER_MIN || LOTTO_NUMBER_MAX < number) {
            throw new IllegalArgumentException(
                    String.format("로또 번호는 %d부터 %d사이의 숫자여야 합니다.", LOTTO_NUMBER_MIN, LOTTO_NUMBER_MAX)
            );
        }
    }

    public Lotto getWinningLotto() {
        return winningLotto;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }
}