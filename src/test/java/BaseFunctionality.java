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

    @Test
    public void assertTrueTest() {
        boolean isElementPresent = false;

        log.info("TestNG's assertTrue without a message:");
        Assert.assertTrue(isElementPresent);
    }

    @Test
    public void assertTrueHamcrestTest() {
        boolean isElementPresent = false;

        log.info("Hamcrest's 'assertTrue' without a message:");
        assertThat(isElementPresent, equalTo(true));
    }

    /**
     * A meaningful assertion failed message will greatly help debugging and tracing
     * Note that TestNG concatenates your message to the base string returned by the assertion method
     */
    @Test
    public void assertTrueWithMessageTest() {
        boolean isElementPresent = false;

        log.info("TestNG's assertTrue with message:");
        Assert.assertTrue(
                isElementPresent,
                getElementNotPresentAssertionMessage(isElementPresent)
        );
    }

    /**
     * Hamcrest assertions display the exact error message provided by the tester
     */
    @Test
    public void assertTrueWithMessageHamcrestTest() {
        boolean isElementPresent = false;

        log.info("Hamcrest's 'assertTrue' with a message:");
        assertThat(
                getElementNotPresentAssertionMessage(isElementPresent),
                isElementPresent, equalTo(true)
        );
    }

    @Test
    public void logicalNegationTestNGTest() {
        log.info("Logical negation using TestNG's 'assertNotEquals': ");
        assertNotEquals(A, B);
        assertNotEquals(A, A_1);
    }

    @Test
    public void logicalNegationAssertFalseTestNGTest() {
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

    private String getElementNotPresentAssertionMessage(boolean isElementPresent) {
        return String.format("Expected the element to be present but got %s", isElementPresent);
    }
}
