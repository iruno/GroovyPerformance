import org.junit.Test

/**
 * Created by Stas on 29.05.2017.
 */
class MapCalculateKey {

    final static DICTIONARY = [
            '23': 'RESOURCE_MODIFICATION_REQUEST',
            '24': 'PGW_TRACE_CONTROL',
            '25': 'UE_TIME_ZONE_CHANGE',
            '26': 'TAI_CHANGE',
            '27': 'ECGI_CHANGE',
            '28': 'CHARGING_CORRELATION_EXCHANGE',
            '29': 'APN-AMBR_MODIFICATION_FAILURE',
            '30': 'USER_CSG_INFORMATION_CHANGE',
            '33': 'USAGE_REPORT',
            '34': 'DEFAULT-EPS-BEARER-QOS_MODIFICATION_FAILURE',
            '35': 'USER_CSG_HYBRID_SUBSCRIBED_INFORMATION_CHANGE',
    ]

    @Test
    void gpathBenchmark() {
        String result1
        String result2

        Integer key = 30
        String expectedResult = 'USER_CSG_INFORMATION_CHANGE'

        benchmark {
            usingGet {
                result1 = DICTIONARY.get(Integer.toString(key))
            }
            usingGpath {
                result2 = DICTIONARY."$key"
            }

        }.prettyPrint()


        assert result1 == expectedResult
        assert result2 == expectedResult
    }

}
