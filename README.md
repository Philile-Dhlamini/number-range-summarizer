# Number Range Summarizer

A Java implementation of the `NumberRangeSummarizer` interface.

## Example

Input:
`1,3,6,7,8,12,13,14,15,21,22,23,24,31`

Output:
`1, 3, 6-8, 12-15, 21-24, 31`

## Requirements

- Java 8 or higher
- Maven 3.6 or higher

## Running the tests

```bash
mvn test
```

## Assumptions

- Input numbers are separated by commas.
- Whitespace around numbers is allowed.
- The collection passed to `summarizeCollection` does not have to be sorted.
- Numbers are sorted before ranges are generated.
- A single number is represented as an individual number.
- Two or more consecutive numbers are represented as a range.
- Null or empty input produces an empty result.
- Invalid numeric input results in a `NumberFormatException`.
- Duplicate numbers are not treated as sequential numbers.

These assumptions are covered by the unit tests where applicable.

## Complexity

Sorting results in O(n log n) time and O(n) additional space. The sorted values are then processed in one pass.
