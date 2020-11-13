package lotto.controller;

import lotto.controller.strategy.GenerateStrategy;
import lotto.domain.Lotto;
import lotto.domain.LottoFactory;
import lotto.domain.Lottos;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottosGeneratorTest {
    int numOfLottos = 3;
    List<Integer> dummyLottoNos1;
    List<Integer> dummyLottoNos2;
    GenerateStrategy dummyStrategy1;
    GenerateStrategy dummyStrategy2;

    @BeforeEach
    void setUp() {
        dummyLottoNos1 = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6});
        dummyLottoNos2 = Arrays.asList(new Integer[]{45, 40, 35, 30, 25, 20});
        dummyStrategy1 = () -> LottoFactory.createLottoFromIntList(dummyLottoNos1);
        dummyStrategy2 = () -> LottoFactory.createLottoFromIntList(dummyLottoNos2);
    }

    private Lottos generateExpectedLottos(List<Integer> dummyLottoNos, int numOfLottos) {
        Lotto dummyLotto = LottoFactory.createLottoFromIntList(dummyLottoNos);
        Lottos expectedLottos = new Lottos();
        for (int i = 0; i < numOfLottos; i++) {
            expectedLottos.add(dummyLotto);
        }
        return expectedLottos;
    }

    @Test
    @DisplayName("Strategy 를 바꾸면 Lottos 가 다르게 generate 되어야 한다.")
    void setStrategy() {
        LottosGenerator generator = new LottosGenerator(numOfLottos, dummyStrategy1);
        Assertions.assertAll(
                () -> {
                    Lottos expectedLottos = generateExpectedLottos(dummyLottoNos1, numOfLottos);
                    assertThat(generator.generateLottos())
                            .isEqualTo(expectedLottos);
                },
                () -> {
                    generator.setStrategy(dummyStrategy2);
                    Lottos expectedLottos = generateExpectedLottos(dummyLottoNos2, numOfLottos);
                    assertThat(generator.generateLottos())
                            .isEqualTo(expectedLottos);
                }
        );
    }
}
