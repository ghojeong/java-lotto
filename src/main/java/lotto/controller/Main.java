package lotto.controller;

import lotto.domain.*;
import lotto.dto.LottosDto;
import lotto.dto.NumOfLottosDto;
import lotto.dto.StatisticsDto;
import lotto.view.ResultView;

public class Main {
    public static void main(String[] args) {
        Money purchaseMoney = InputMapper.getPurchaseMoney();
        int numOfLottos = Lotto.getNumOfLottos(purchaseMoney);
        NumOfLottosDto numOfLottosDto = new NumOfLottosDto(numOfLottos);
        ResultView.printNumOfLottos(numOfLottosDto);

        Shuffler shuffler = new Shuffler(LottoNo.MIN, LottoNo.MAX);
        Lottos lottos = new Lottos(numOfLottos, () -> new Lotto(
                shuffler.getIntegers(Lotto.SIZE)
        ));
        LottosDto lottosDto = new LottosDto(lottos);
        ResultView.printLottos(lottosDto);

        WinningCondition condition = InputMapper.getWinningCondition();
        Result result = lottos.getResult(condition);
        StatisticsDto statisticsDto = new StatisticsDto(result, purchaseMoney);
        ResultView.printStatistics(statisticsDto);
    }
}
