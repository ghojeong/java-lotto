package lotto.domain;

import lotto.exception.BadNumOfLottoNoException;
import lotto.exception.DuplicatedLottoException;
import lotto.exception.LottoRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LottoTest {

    @Test
    @DisplayName("6개의 로또 번호가 성공적으로 입력됨")
    void createLotto_int_success() {
        assertThat(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)).toString()
        ).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @Test
    @DisplayName("1 부터 45가 아닌 로또 번호가 입력되면 LottoRangeException 이 발생한다.")
    void createLotto_int_range() {
        assertThatExceptionOfType(LottoRangeException.class)
                .isThrownBy(() -> new Lotto(Arrays.asList(-1, -2, -3, 46, 47, 48)));
    }

    @Test
    @DisplayName("로또 번호가 6개 입력되지 않으면, BadNumOfLottoNoException 이 발생한다.")
    void createLotto_int_badNumOfLottoNo() {
        assertThatExceptionOfType(BadNumOfLottoNoException.class)
                .isThrownBy(() -> new Lotto(Arrays.asList(46, 0, -1)));
    }

    @ParameterizedTest
    @DisplayName("같은 LottoNo 개수 만큼 countSameNo 값이 나와야한다.")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6})
    void countSameNo(int count) {
        int size = 6;
        Integer[] lottoNos1 = new Integer[size];
        Integer[] lottoNos2 = new Integer[size];
        for (int i = 0; i < size; i++) {
            lottoNos1[i] = i + 1;
            lottoNos2[i] = i + 1 + size - count;
        }

        Lotto lotto1 = new Lotto(Arrays.asList(lottoNos1));
        Lotto lotto2 = new Lotto(Arrays.asList(lottoNos2));

        assertThat(lotto1.countSameNo(lotto2))
                .isEqualTo(count);
    }

    @Test
    @DisplayName("같은 숫자가 있으면, DuplicatedLottoException 이 발생한다.")
    void add_duplicated_int() {
        assertThatExceptionOfType(DuplicatedLottoException.class)
                .isThrownBy(() -> {
                    new Lotto(Arrays.asList(1, 1, 1, 1, 1, 1));
                });
    }

    @ParameterizedTest
    @DisplayName("1부터 45가 아닌 숫자를 add 하면, LottoRangeException 이 발생한다.")
    @ValueSource(ints = {-1, 0, 46})
    public void constructor_int_fail(int lottoNo) {
        List<Integer> lottoNoList = new LinkedList<>();
        lottoNoList.add(lottoNo);
        for (int i = 1; i <= 5; i++) {
            lottoNoList.add(i);
        }
        assertThatExceptionOfType(LottoRangeException.class)
                .isThrownBy(() -> new Lotto(lottoNoList));
    }


    @Test
    void testToString() {
        Lotto lotto = new Lotto(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        String expected = "[1, 2, 3, 4, 5, 6]";
        assertThat(lotto.toString())
                .isEqualTo(expected);
    }

    @Test
    void testEquals() {
        Lotto lotto1 = new Lotto(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        Lotto lotto2 = new Lotto(
                Arrays.asList(6, 5, 4, 3, 2, 1)
        );
        assertThat(lotto1)
                .isEqualTo(lotto2);
    }

    @Test
    void testHashCode() {
        Lotto lotto1 = new Lotto(
                Arrays.asList(1, 2, 3, 4, 5, 6)
        );
        Lotto lotto2 = new Lotto(
                Arrays.asList(6, 5, 4, 3, 2, 1)
        );
        assertThat(lotto1.hashCode())
                .isEqualTo(lotto2.hashCode());
    }
}
