import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;

@Slf4j
public class CloseToNumber {

    private static final int EXPECTED_NUMBER = 5;
    private static final double MAX_DELTA = 0.2;

    @Test
    public void closeToHamcrestTest() {
        assertThat(5.1, closeTo(EXPECTED_NUMBER, MAX_DELTA));
        assertThat(5.3, closeTo(EXPECTED_NUMBER, MAX_DELTA));
    }

    @Test
    public void closeToTestNgTest() {
        Assert.assertTrue(
              isNumberBetweenRange(5.1)
        );
        Assert.assertTrue(
                isNumberBetweenRange(5.3)
        );
    }

    private boolean isNumberBetweenRange(double actual) {
        //The boolean expression is equal to:
        //(EXPECTED_NUMBER + MAX_DELTA) > actual > (EXPECTED_NUMBER - MAX_DELTA)
        return ( EXPECTED_NUMBER + MAX_DELTA > actual ) && ( actual> EXPECTED_NUMBER - MAX_DELTA);
    }
}
