Demo: Apache Commons XMLConfiguration ignores the configuration key immediately following one whose value contains a comma

- Expected: All tests succeed
- Actual: The tests `testWithOnlyComma`, `testWithCommaSeparatedList` and `testWithOnlyCommaWithStringBuilder` fail


Running
---

Use the bundled Gradle wrapper:

    $ ./gradlew test
