import org.junit.Test

class BuildStringFromMap {
    final Map shaping = [down: [burst: 0, speed: 64], prio: 3, up: [burst: 200000, speed: 64]]

    static String mapToString(Map shaping) {
        [[shaping.down?.speed ?: '', shaping.up?.speed ?: ''].join('.'), shaping.prio, shaping.qci].grep {
            it != null
        }.join('-')
    }

    static String mapToStringSimple(Map shaping) {
        String result = shaping.down?.speed ?: ''
        result += '.'
        result += shaping.up?.speed ?: ''
        if (shaping.prio) {
            result += '-'
            result += shaping.prio

            if (shaping.qci) {
                result += '-'
                result += shaping.qci
            }
        }

        result
    }

    static String mapToStringWithStringBuilder(Map shaping) {
        def result = StringBuilder.newInstance()

        result << shaping.down?.speed ?: ''
        result << '.'
        result << shaping.up?.speed ?: ''
        if (shaping.prio) {
            result << '-'
            result << shaping.prio

            if (shaping.qci) {
                result << '-'
                result << shaping.qci
            }
        }

        result
    }

    @Test
    void benchmark() {
        String result1
        String result2
        String result3

        benchmark {
            'mapToString' {
                result1 = mapToString(shaping)
            }

            'mapToStringSimple' {
                result2 = mapToStringSimple(shaping)
            }

            'mapToStringWithStringBuilder' {
                result3 = mapToStringWithStringBuilder(shaping)
            }
        }.prettyPrint()

        print result1

        assert result1 == result2
        assert result2 == result3
    }
}
