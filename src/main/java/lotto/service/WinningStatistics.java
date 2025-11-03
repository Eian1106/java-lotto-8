package lotto.service;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Rank;
import lotto.model.WinningNumbers;


public class WinningStatistics {

    public Map<Rank, Long> lottoWinningResult(Lottos lottos, WinningNumbers winning) {
        Map<Rank, Long> result = initResult();
        List<Lotto> tickets = lottos.getLottos();

        for (Lotto ticket : tickets) {
            Rank rank = compare(ticket, winning);
            addCount(result, rank);
        }
        return result;
    }

    public Rank compare(Lotto ticket, WinningNumbers winning) {
        Lotto winningNumbers = winning.getWinningLotto();
        int matches = countMatches(ticket, winningNumbers);
        boolean bonusMatched = isBonusMatched(ticket, winning.getBonusNumber(), matches);
        return Rank.valueOf(matches, bonusMatched);
    }

    private Map<Rank, Long> initResult() {
        Map<Rank, Long> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0L);
        }
        return result;
    }

    private void addCount(Map<Rank, Long> result, Rank rank) {
        long count = result.get(rank);
        result.put(rank, count + 1);
    }

    private int countMatches(Lotto ticket, Lotto winningNumbers) {
        Set<Integer> winningSet = new HashSet<>(winningNumbers.getNumbers());
        int count = 0;

        for (int number : ticket.getNumbers()) {
            if (winningSet.contains(number)) {
                count++;
            }
        }
        return count;
    }

    private boolean isBonusMatched(Lotto ticket, int bonusNumber, int matchCount) {
        if (matchCount != 5) {
            return false;
        }
        for (int number : ticket.getNumbers()) {
            if (number == bonusNumber) {
                return true;
            }
        }
        return false;
    }
}