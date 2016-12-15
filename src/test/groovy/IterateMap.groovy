import groovy.transform.CompileStatic
import org.junit.Test

class IterateMap {

    static Map<String, String> data = [
            USER      : '71234567890',
            ACCOUNTEXT: '49',
            OSRV_KEY  : '1',
            TSRV_KEY  : '1',
            TRFTYPE   : '1',
            UNITTYPE  : '1',
            LOCATION  : '70000000000T1N1|0.205.1.eeee.ffff|C7|12345667',
            SNAME     : 'gprs limit',
            IMSI      : '250011234567890'
    ]

    static void doSmth(String key, String value) {
        //println("${key}: ${value}")
    }

    static void usingFor() {
        for (e in data)
            doSmth(e.key, e.value)
    }

    @CompileStatic
    static void usingForWithStatic() {
        for (e in data)
            doSmth(e.key, e.value)
    }

    static void usingEach() {
        data.each { e ->
            doSmth(e.key, e.value)
        }
    }

    @CompileStatic
    static void usingEachWithStatic() {
        data.each { e ->
            doSmth(e.key, e.value)
        }
    }

    static void usingEachKV() {
        data.each { k, v ->
            doSmth(k, v)
        }
    }

    @CompileStatic
    static void usingEachKVWithStatic() {
        data.each { k, v ->
            doSmth(k, v)
        }
    }

    @Test
    void 'simple'() {

        benchmark {
            'Loop using "for"' {
                usingFor()
            }

            'Loop using "for" + CompileStatic' {
                usingForWithStatic()
            }

            'Loop using "each"' {
                usingEach()
            }

            'Loop using "each" + CompileStatic' {
                usingEachWithStatic()
            }

            'Loop using "each" +  k, v' {
                usingEachKV()
            }

            'Loop using "each" +  k, v + CompileStatic' {
                usingEachKVWithStatic()
            }
        }.prettyPrint()
    }
}