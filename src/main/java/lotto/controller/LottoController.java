package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Rank;
import lotto.model.WinningNumbers;
import lotto.service.LottoGenerator;
import lotto.service.WinningStatistics;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;
    private final WinningStatistics statistics;

    public LottoController(InputView inputView, OutputView outputView, LottoGenerator lottoGenerator, WinningStatistics statistics) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
        this.statistics = statistics;
    }

    public void run() {
        int purchaseAmount = inputView.getPurchaseAmount();

        Lottos lottos = lottoGenerator.buyTickets(purchaseAmount);
        outputView.printLottoNumbers(lottos);

        WinningNumbers winningNumbers = createWinningNumber();

        Map<Rank, Long> result = statistics.lottoWinningResult(lottos, winningNumbers);

        outputView.printStatistics(result);
        long totalPrize = calculateTotalPrize(result);
        outputView.printYield(totalPrize, purchaseAmount);
    }

    private WinningNumbers createWinningNumber() {
        Lotto winningLotto = getWinningLotto();
        while (true) {
            try {
                int bonusNumber = inputView.getBonusNumber();
                return new WinningNumbers(winningLotto, bonusNumber);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }

    private Lotto getWinningLotto() {
        while (true) {
            try {
                List<Integer> winningNumbers = inputView.getWinningNumbers();
                return new Lotto(winningNumbers);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        }
    }


    private long calculateTotalPrize(Map<Rank, Long> result) {
        long total = 0;
        for (Rank rank : Rank.values()) {
            total += rank.getPrize() * result.get(rank);
        }
        return total;
    }
}
