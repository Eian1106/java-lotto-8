package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.model.Lotto;
import lotto.model.Lottos;

public class LottoGenerator {

    private static final int TICKET_PRICE = 1000;
    private static final int LOTTO_NUMBER_MIN = 1;
    private static final int LOTTO_NUMBER_MAX = 45;
    private static final int LOTTO_NUMBER_COUNT = 6;

    public Lottos buyTickets(int amount) {
        int lottoCount = amount / TICKET_PRICE;
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            Lotto lotto = generateTicket();
            lottos.add(lotto);
        }

        return new Lottos(lottos);
    }

    private List<Integer> generateNumbers() {
        List<Integer> picked = Randoms.pickUniqueNumbersInRange(
                LOTTO_NUMBER_MIN,
                LOTTO_NUMBER_MAX,
                LOTTO_NUMBER_COUNT
        );

        List<Integer> numbers = new ArrayList<>(picked);
        Collections.sort(numbers);

        return numbers;
    }

    private Lotto generateTicket() {
        return new Lotto(generateNumbers());
    }
}
