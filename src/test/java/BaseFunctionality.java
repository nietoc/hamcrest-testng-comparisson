import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;

@Slf4j
public class BaseFunctionality {

    private static final String A = "A";
    private static final String A_1 = "A";
    private static final String B = "B";

    private static final int ACTUAL_VALUE = 1;
    private static final int EXPECTED_VALUE = 2;

    @Test
    public void assertTrueTestNgTest() {
        log.info("TestNG's assertTrue without a message:");
        Assert.assertTrue(A.equals(A_1));
        Assert.assertTrue(A.equals(B));
    }

    @Test
    public void equalToHamcrestTest() {
        log.info("Hamcrest's 'equalTo' without a message:");
        assertThat(A, equalTo(A_1));
        assertThat(A, equalTo(B));
    }

    /**
     * A meaningful assertion failed message will greatly help debugging and tracing
     * Note that TestNG concatenates your message to the base string returned by the assertion method
     */
    @Test
    public void assertEqualsWithMessageTestNGTest() {
        log.info("TestNG's assertEquals with message:");
        Assert.assertEquals(
                ACTUAL_VALUE, EXPECTED_VALUE,
                getValueMismatchAssertionMessage(ACTUAL_VALUE, EXPECTED_VALUE)
        );
    }

    /**
     * Hamcrest assertions display the exact error message provided by the tester
     * Note that the position for the message is opposite to TestNG's
     */
    @Test
    public void equalToWithMessageHamcrestTest() {
        log.info("Hamcrest's 'assertTrue' with a message:");
        assertThat(
                getValueMismatchAssertionMessage(ACTUAL_VALUE, EXPECTED_VALUE),
                ACTUAL_VALUE, equalTo(EXPECTED_VALUE)
        );
    }

    /**
     * Weirdly enough all that is outputted by TestNG is 'java.lang.AssertionError: null'
     */
    @Test
    public void logicalNegationTestNgTest() {
        log.info("Logical negation using TestNG's 'assertNotEquals': ");
        assertNotEquals(A, B);
        assertNotEquals(A, A_1);
    }

    @Test
    public void logicalNegationAssertFalseTestNgTest() {
        log.info("Logical negation using TestNG's 'assertFalse': ");
        assertFalse(A.equals(B));
        assertFalse(A.equals(A_1));
    }

    @Test
    public void logicalNegationHamcrestTest() {
        log.info("Logical negation using Hamcrest: ");
        assertThat(A, is(not(equalTo(B))));
        assertThat(A, not(equalTo(A_1)));
    }

    private String getValueMismatchAssertionMessage(int expected, int actual) {
        return String.format("The provided values do not match! Expected %s got %s", expected, actual);
    }
}
