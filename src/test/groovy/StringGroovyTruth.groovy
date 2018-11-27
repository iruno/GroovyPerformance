import org.junit.Test

class StringGroovyTruth {

    // 1. Groovy-Truth: Non-empty Strings, GStrings and CharSequences are coerced to true. Slow! DefaultTypeTransformation.booleanUnbox() is called here
    // 2. Equivalent: (myString != null && myString.length() > 0).
    // 3. Just check for null

    //                       user  system  cpu  real
    // Groovy-Truth           131       0  131   131
    // check null && length    15       0   15    17
    // check null               8       0    8     8

    @Test
    void benchmark() {
        Boolean expectedResult = true
        Boolean result1
        Boolean result2

        String myString = "blabla"

        benchmark {
            'Groovy-Truth' {
                result1 = (myString) // booleanUnbox is called here
            }
            'check null && length' {
                result2 = (myString != null && myString.length() > 0)
            }
            'check null' {
                result2 = (myString != null)
            }
        }.prettyPrint()

        assert result1 == expectedResult
        assert result2 == expectedResult
    }
}
