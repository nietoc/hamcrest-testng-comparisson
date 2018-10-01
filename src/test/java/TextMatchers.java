import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class TextMatchers {

    /*
     * Other useful String matchers: endsWith, equalToIgnoringWhiteSpace, isEmptyString, matchesPattern, stringContainsInOrder
     *
     * Additional matcher examples:
     * https://www.baeldung.com/hamcrest-text-matchers
     */

    private static final String TEXT_WITH_NAMES = "Hello world bla bla John DOe more texty boopity bapity";
    private static final String EXPECTED_SUBSTRING = "John";
    private static final String NON_PRESENT_SUBSTRING = "NotContaintedText";

    private static final String UPPERCASE_TEXT = "ABCDEFGHIJ";
    private static final String LOWERCASE_TEXT = "abcdefghij";

    private static final String EMPTY_TEXT = "";
    private static final String NULL_TEXT = null;

    @Test
    public void containsStringTestNGTest() {
        log.info("String.contains using TestNG:");
        Assert.assertTrue(TEXT_WITH_NAMES.contains(EXPECTED_SUBSTRING));
        Assert.assertTrue(TEXT_WITH_NAMES.contains(NON_PRESENT_SUBSTRING));
    }

    @Test
    public void containsStringTest() {
        log.info("Hamcrest's containsString:");
        assertThat(TEXT_WITH_NAMES, containsString(EXPECTED_SUBSTRING));
        assertThat(TEXT_WITH_NAMES, containsString(NON_PRESENT_SUBSTRING));
    }

    @Test
    public void equalToIgnoringCaseTestNGTest() {
        log.info("String.equalsIgnoreCase using TestNG:");
        Assert.assertTrue(UPPERCASE_TEXT.equalsIgnoreCase(LOWERCASE_TEXT));
        Assert.assertTrue(UPPERCASE_TEXT.equalsIgnoreCase(NON_PRESENT_SUBSTRING));
    }

    @Test
    public void equalToIgnoringCaseTest() {
        log.info("Hamcrest's equalToIgnoringCase:");
        assertThat(UPPERCASE_TEXT, is(equalToIgnoringCase(LOWERCASE_TEXT)));
        assertThat(UPPERCASE_TEXT, is(equalToIgnoringCase(NON_PRESENT_SUBSTRING)));
    }

    @Test
    public void emptyOrNullStringTestNGTest() {
        log.info("Empy or null string using TestNG:");
        Assert.assertNull(NULL_TEXT);
        Assert.assertTrue(EMPTY_TEXT.isEmpty());
        //Note that String.isEmpty will throw a NullPointerException if the String is null
        Assert.assertTrue(TEXT_WITH_NAMES.isEmpty());
    }

    @Test
    public void emptyOrNullStringTest() {
        log.info("Hamcrest's emptyOrNullString:");
        assertThat(EMPTY_TEXT, is(isEmptyOrNullString()));
        assertThat(NULL_TEXT, is(isEmptyOrNullString()));
        assertThat(TEXT_WITH_NAMES, is(isEmptyOrNullString()));
    }
}
