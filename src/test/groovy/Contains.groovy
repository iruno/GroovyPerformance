import org.junit.Test

// Task: check if one list contains element from the other
class Contains {

    static Set<String> list = ['flour', 'sugar', 'eggs', 'butter']

    static Boolean usingContains(String elem) {
        list.contains(elem)
    }

    static Boolean usingIn(String elem) {
        elem in list
    }

    @Test
    void benchmark() {
        Boolean result1
        Boolean result2

        String elem = 'eggs'

        benchmark {
            usingIn {
                result2 = usingIn(elem)
            }
            usingContains {
                result1 = usingContains(elem)
            }

        }.prettyPrint()

        println result1

        assert result1 == result2
    }
}
