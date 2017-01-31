import org.junit.Test

/**
 * Created by Stas on 31.01.2017.
 */

class LookForAccess {

    static Map jData1 = [

            SMS: [
                    [direction: "MO", access: "denied"],
                    [direction: "MT", access: "denied"]
            ]
    ]

    static String fn1(String key, String direction) {
        def data = jData1.get(key)

        if (data)
            for (e in data) {
                if (e.get('direction') == direction)
                    return e.get('access')
            }

        null
    }

    static Map jData2 = [

            SMS: [
                    MO: 'denied',
                    MT: 'denied'
            ]
    ]

    static String fn2(String key, String direction) {
        def data = jData2.get(key)
        data?.get(direction)
    }

    @Test
    void 'simple'() {

        String result1
        String result2

        String key = 'SMS'
        String direction = 'MT'
        benchmark {
            'fn1' {
                result1 = fn1(key, direction)
            }

            'fn2' {
                result2 = fn2(key, direction)
            }

        }.prettyPrint()

        assert result1 == result2

        println("Result is [$result1]")
    }
}
