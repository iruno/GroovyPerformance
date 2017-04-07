import org.junit.Test

class SetContains {

    final static String C2 = 'C2'
    final static String C7 = 'C7'
    public final static Set<String> preparedSet = [C2, C7] as Set<String>

    static Boolean usingIn(String elem) {
        elem in [C2, C7]
    }

    static Boolean usingContains(String elem) {
        [C2, C7].contains(elem)
    }

    static Boolean usingEquals(String elem) {
        elem == C2 || elem == C7
    }

    static Boolean usingContainsInPreparedSet(String elem) {
        preparedSet.contains(elem)
    }

    @Test
    void benchmark() {
        Boolean result1
        Boolean result2
        Boolean result3
        Boolean result4

        String elem = 'eggs'

        benchmark {
            usingIn {
                result1 = usingIn(elem)
            }
            usingContains {
                result2 = usingContains(elem)
            }
            usingEquals {
                result3 = usingEquals(elem)
            }
            usingContainsInPreparedSet {
                result4 = usingContainsInPreparedSet(elem)
            }
        }.prettyPrint()

        println result1

        assert result1 == result2
        assert result2 == result3
        assert result3 == result4
    }
}
