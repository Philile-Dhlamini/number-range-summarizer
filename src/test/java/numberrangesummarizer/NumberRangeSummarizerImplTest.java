package numberrangesummarizer;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NumberRangeSummarizerImplTest {

    private final NumberRangeSummarizer summarizer =
            new NumberRangeSummarizerImpl();

    @Test
    void shouldCollectNumbersFromCommaSeparatedInput() {
        Collection<Integer> result =
                summarizer.collect("1,3,6,7,8,12");

        assertEquals(Arrays.asList(1, 3, 6, 7, 8, 12), result);
    }

    @Test
    void shouldIgnoreWhitespaceWhenCollectingNumbers() {
        Collection<Integer> result =
                summarizer.collect("1, 3, 6, 7, 8");

        assertEquals(Arrays.asList(1, 3, 6, 7, 8), result);
    }

    @Test
    void shouldReturnEmptyCollectionForNullInput() {
        assertEquals(Collections.emptyList(), summarizer.collect(null));
    }

    @Test
    void shouldReturnEmptyCollectionForEmptyInput() {
        assertEquals(Collections.emptyList(), summarizer.collect(""));
    }

    @Test
    void shouldSummarizeSampleInputCorrectly() {
        Collection<Integer> input = Arrays.asList(
                1, 3, 6, 7, 8, 12, 13, 14, 15,
                21, 22, 23, 24, 31
        );

        assertEquals(
                "1, 3, 6-8, 12-15, 21-24, 31",
                summarizer.summarizeCollection(input)
        );
    }

    @Test
    void shouldSummarizeAllSequentialNumbersAsOneRange() {
        assertEquals(
                "1-5",
                summarizer.summarizeCollection(
                        Arrays.asList(1, 2, 3, 4, 5)
                )
        );
    }

    @Test
    void shouldKeepNonSequentialNumbersSeparate() {
        assertEquals(
                "1, 3, 5, 7",
                summarizer.summarizeCollection(
                        Arrays.asList(1, 3, 5, 7)
                )
        );
    }

    @Test
    void shouldKeepSingleNumberAsSingleValue() {
        assertEquals(
                "5",
                summarizer.summarizeCollection(
                        Collections.singletonList(5)
                )
        );
    }

    @Test
    void shouldHandleUnsortedInput() {
        assertEquals(
                "1-5",
                summarizer.summarizeCollection(
                        Arrays.asList(3, 5, 1, 4, 2)
                )
        );
    }

    @Test
    void shouldHandleNegativeNumbers() {
        assertEquals(
                "-5--2, 1-3",
                summarizer.summarizeCollection(
                        Arrays.asList(-5, -4, -3, -2, 1, 2, 3)
                )
        );
    }

    @Test
    void shouldReturnEmptyStringForEmptyCollection() {
        assertEquals("", summarizer.summarizeCollection(Collections.emptyList()));
    }

    @Test
    void shouldReturnEmptyStringForNullCollection() {
        assertEquals("", summarizer.summarizeCollection(null));
    }

    @Test
    void shouldRejectInvalidNumberInput() {
        assertThrows(
                NumberFormatException.class,
                () -> summarizer.collect("1,2,abc,4")
        );
    }
}
