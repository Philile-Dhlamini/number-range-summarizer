package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Default implementation of NumberRangeSummarizer.
 */
public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        return Stream.of(input.split(","))
                .map(String::trim)
                .filter(value -> !value.isEmpty())
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        List<Integer> numbers = new ArrayList<>(input);
        Collections.sort(numbers);

        List<String> ranges = new ArrayList<>();

        int rangeStart = numbers.get(0);
        int previousNumber = numbers.get(0);

        for (int i = 1; i < numbers.size(); i++) {
            int currentNumber = numbers.get(i);

            if (currentNumber == previousNumber + 1) {
                previousNumber = currentNumber;
            } else {
                ranges.add(formatRange(rangeStart, previousNumber));
                rangeStart = currentNumber;
                previousNumber = currentNumber;
            }
        }

        ranges.add(formatRange(rangeStart, previousNumber));

        return String.join(", ", ranges);
    }

    private String formatRange(int start, int end) {
        if (start == end) {
            return String.valueOf(start);
        }

        return start + "-" + end;
    }
}
