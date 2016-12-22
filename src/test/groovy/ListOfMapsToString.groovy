import org.junit.Test

class ListOfMapsToString {

    List ingredients = [
            [name: "flour", unit: "g", pcs: 500],
            [name: "sugar", unit: "g", pcs: 400],
            [name: "vanilla sugar", unit: "g", pcs: 70],
            [name: "eggs, beaten", unit: "pcs", pcs: 5],
            [name: "butter, melted", unit: "g", pcs: 400]
    ]

    static String usingCollectWithJoin(List list) {
        list?.collect { it.name }?.join('|')
    }

    static String usingFor(List list) {
        String result = ''

        for (e in list) {
            if (result)
                result += '|'
            result += e.name
        }
        result
    }

    @Test
    void benchmark() {
        def result1
        def result2
        def result3
        def result4

        benchmark {
            usingCollectWithJoin {
                result1 = usingCollectWithJoin(ingredients)
            }
            usingFor {
                result2 = usingFor(ingredients)
            }

        }.prettyPrint()

        print result1

        assert result1 == result2
/*
        assert result2 == result3
        assert result3 == result4
        */
    }
}
