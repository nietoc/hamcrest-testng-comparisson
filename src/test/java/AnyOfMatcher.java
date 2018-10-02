import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

@Slf4j
public class AnyOfMatcher {

    /**
     * More info about Java's short circuiting: https://users.drew.edu/bburd/JavaForDummies6/shortCircuitEval.pdf
     */

    private static final List<Integer> EMPTY_LIST = Collections.emptyList();
    private static final List<Integer> NULL_LIST = null;
    private static final List<Integer> POPULATED_LIST = Arrays.asList(1, 2, 3);

    @Test
    public void emptyOrNullCollectionTest() {
        assertEmptyOrNullCollection(EMPTY_LIST);
        assertEmptyOrNullCollection(NULL_LIST);
        log.info("Empty of null collection using Hamcrest's anyOf matcher");
        assertEmptyOrNullCollection(POPULATED_LIST);
    }

    @Test
    public void emptyOrNullCollectionUsingEitherTest() {
        assertEmptyOrNullCollectionUsingEither(EMPTY_LIST);
        log.info("Empty of null collection using Hamcrest's either matcher - strangely enough the null check fails");
        assertEmptyOrNullCollectionUsingEither(NULL_LIST);
        assertEmptyOrNullCollectionUsingEither(POPULATED_LIST);
    }

    /**
     * Note that as soon as a predicate is met the assertion evaluation stops (short circuits like Java ||)
     */
    private void assertEmptyOrNullCollection(List<?> list) {
        assertThat(
                list,
                anyOf(
                        is(empty()),
                        is(nullValue())
                )
        );
    }

    /**
     * Same functionality as in {@link AnyOfMatcher#assertEmptyOrNullCollection(java.util.List)}
     */
    private void assertEmptyOrNullCollectionUsingEither(List<?> list) {
        assertThat(
                list,
                either(is(empty())).
                        or(is(nullValue()))
        );
    }
}
