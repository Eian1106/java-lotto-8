package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Rank;

public class OutputView {

    private final Rank[] PRINT_ORDER = {
            Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST
    };

    public void printLottoNumbers(Lottos lottos) {
        List<Lotto> tickets = lottos.getLottos();
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (Lotto lotto : tickets) {
            System.out.println(lotto.getNumbers());
        }
        System.out.println();
    }

    public void printStatistics(Map<Rank, Long> counts) {
        System.out.println("당첨 통계");
        System.out.println("---");
        for (Rank rank : PRINT_ORDER) {
            printRankLine(rank, counts.get(rank));
        }
        System.out.println();
    }

    private void printRankLine(Rank rank, long count) {
        String prize = money(rank.getPrize());
        if (rank == Rank.SECOND) {
            System.out.println("5개 일치, 보너스 볼 일치 (" + prize + ") - " + count + "개");
            return;
        }
        System.out.println(rank.getMatchCount() + "개 일치 (" + prize + ") - " + count + "개");
    }

    public void printYield(long totalPrize, int purchaseAmount) {
        double rate = (double) totalPrize / purchaseAmount * 100.0;
        System.out.printf("총 수익률은 %.1f%%%s%n", rate, "입니다.");
    }

    private String money(long won) {
        return String.format("%,d원", won);
    }
}
