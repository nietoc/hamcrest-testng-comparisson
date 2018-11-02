import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@Slf4j
public class CollectionMatchers {

    private static final List<Integer> ONE_TO_NINE = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    @Test
    public void hasItemHamcrestTest() {
        log.info("Hamcrest's hasItem:");
        assertThat(ONE_TO_NINE, hasItem(5));
        assertThat(ONE_TO_NINE, hasItem(10));
    }

    @Test
    public void hasItemTestNgTest() {
        log.info("Asserting if an element is contained in a collection using TestNG");
        Assert.assertTrue(ONE_TO_NINE.contains(5));
        Assert.assertTrue(ONE_TO_NINE.contains(10));
    }

    @Test
    public void hasSizeHamcrestTest() {
        log.info("Hamcrest's hasSize:");
        assertThat(ONE_TO_NINE, hasSize(9));
        assertThat(ONE_TO_NINE, hasSize(greaterThan(9)));
    }

    @Test
    public void hasSizeTestNgTest() {
        log.info("Collection size assertion using TestNG");
        Assert.assertEquals(ONE_TO_NINE.size(), 9);
        Assert.assertTrue(ONE_TO_NINE.size() > 9);
    }

    @Test
    public void everyItemHamcrestTest() {
        log.info("Hamcrest's everyItem:");
        assertThat(ONE_TO_NINE, everyItem(greaterThan(0)));
        assertThat(ONE_TO_NINE, everyItem(greaterThan(5))); //Note that only the first mismatching item is displayed in the AssertionError
    }

    @Test
    public void everyItemTestNgTest() {
        log.info("Asserting every item in a collection using TestNG");

        for (Integer item : ONE_TO_NINE) {
            Assert.assertTrue(item > 0);
        }

        for (Integer item : ONE_TO_NINE) {
            Assert.assertTrue(item > 5);
        }
    }
}
