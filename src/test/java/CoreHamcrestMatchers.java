import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.hamcrest.number.OrderingComparison.lessThanOrEqualTo;

@Slf4j
public class CoreHamcrestMatchers {

    @Test
    public void allOfGreaterThanTestA() {
        assertNumberBetweenRange(5, 0, 10);
        assertNumberBetweenRange(5, 4, 5);
        log.info("5 is not greater than 5");
        assertNumberBetweenRange(5, 5, 5);
    }

    @Test
    public void allOfGreaterThanTestB() {
        log.info("5 is less than 5");
        assertNumberBetweenRange(5, 10, 20);
    }

    @Test
    public void allOfLessThanTestB() {
        log.info("30 is greater than 20");
        assertNumberBetweenRange(30, 10, 20);
    }

    /**
     * Note that Hamcrest 'short circuits' (fails fast) and stops checking as soon as one matcher is not met
     */
    @Test
    public void allOfBothConditionsFail() {
        log.info("150 is neither greater than 199 nor lesser than 100");
        assertNumberBetweenRange(1, 199, 100);
    }

    /**
     * a combination of inclusive and exlusive is used just to illustrate the usage of the 'OrEqualTo' matchers
     */
    private void assertNumberBetweenRange(int input, int minExclusive, int maxInclusive) {
        assertThat(
                input,
                allOf(
                        greaterThan(minExclusive),
                        lessThanOrEqualTo(maxInclusive)
                )
        );
    }
}
