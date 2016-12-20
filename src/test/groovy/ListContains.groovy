import groovy.transform.CompileStatic
import org.junit.Test

// Task: check if one list contains element from the other
class ListContains {

    static ArrayList<String> list1 = ['flour', 'sugar', 'eggs', 'butter']
    static ArrayList<String> list2 = ['eggs', 'butter']

    static Boolean anyWithContains() {
        list1.any { list2.contains(it) }
    }

    static Boolean forWithContains() {
        for (e in list1)
            if (list2.contains(e))
                return true
        false
    }

    @CompileStatic
    static Boolean forWithContainsWithCompileStatic() {
        for (e in list1)
            if (list2.contains(e))
                return true
        false
    }

    @Test
    void benchmark() {
        Boolean result1
        Boolean result2
        Boolean result3

        benchmark {
            'Any + Contains' {
                result1 = anyWithContains()
            }
            'For + Contains' {
                result2 = forWithContains()
            }
            'For + Contains + CompileStatic' {
                result3 = forWithContainsWithCompileStatic()
            }

        }.prettyPrint()

        println result1

        assert result1 == result2
        assert result2 == result3
    }
}
