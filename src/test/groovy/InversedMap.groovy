import groovy.transform.CompileStatic
import org.junit.Test

// Task: find a key in a map and return its value
class InversedMap {

    public final static Map<String, Integer> DICTIONARY = [
            'SGSN_CHANGE'                                            : 0,
            'QOS_CHANGE'                                             : 1,
            'RAT_CHANGE'                                             : 2,
            'TFT_CHANGE'                                             : 3,
            'PLMN_CHANGE'                                            : 4,
            'LOSS_OF_BEARER'                                         : 5,
            'RECOVERY_OF_BEARER'                                     : 6,
            'IP-CAN_CHANGE'                                          : 7,
            'QOS_CHANGE_EXCEEDING_AUTHORIZATION'                     : 11,
            'RAI_CHANGE'                                             : 12,
            'USER_LOCATION_CHANGE'                                   : 13,
            'NO_EVENT_TRIGGERS'                                      : 14,
            'OUT_OF_CREDIT'                                          : 15,
            'REALLOCATION_OF_CREDIT'                                 : 16,
            'UE_IP_ADDRESS_ALLOCATE'                                 : 18,
            'UE_IP_ADDRESS_RELEASE'                                  : 19,
            'DEFAULT_EPS_BEARER_QOS_CHANGE'                          : 20,
            'AN_GW_CHANGE'                                           : 21,
            'SUCCESSFUL_RESOURCE_ALLOCATION'                         : 22,
            'RESOURCE_MODIFICATION_REQUEST'                          : 23,
            'PGW_TRACE_CONTROL'                                      : 24,
            'UE_TIME_ZONE_CHANGE'                                    : 25,
            'TAI_CHANGE'                                             : 26,
            'ECGI_CHANGE'                                            : 27,
            'CHARGING_CORRELATION_EXCHANGE'                          : 28,
            'APN-AMBR_MODIFICATION_FAILURE'                          : 29,
            'USER_CSG_INFORMATION_CHANGE'                            : 30,
            'USAGE_REPORT'                                           : 33,
            'DEFAULT-EPS-BEARER-QOS_MODIFICATION_FAILURE'            : 34,
            'USER_CSG_HYBRID_SUBSCRIBED_INFORMATION_CHANGE'          : 35,
            'USER_CSG_ HYBRID_UNSUBSCRIBED_INFORMATION_CHANGE'       : 36,
            'ROUTING_RULE_CHANGE'                                    : 37,
            'APPLICATION_START'                                      : 39,
            'APPLICATION_STOP'                                       : 40,
            'CS_TO_PS_HANDOVER'                                      : 42,
            'UE_LOCAL_IP_ADDRESS_CHANGE'                             : 43,
            'H(E)NB_LOCAL_IP_ADDRESS_CHANGE'                         : 44,  // TODO: ask what is it
            'HNB_LOCAL_IP_ADDRESS_CHANGE'                            : 44,  // ??
            'HENB_LOCAL_IP_ADDRESS_CHANGE'                           : 44,  // ??
            'ACCESS_NETWORK_INFO_REPORT'                             : 45,
            'CREDIT_MANAGEMENT_SESSION_FAILURE'                      : 46,
            'DEFAULT_QOS_CHANGE'                                     : 47,
            'CHANGE_OF_UE_PRESENCE_IN_PRESENCE_REPORTING_AREA_REPORT': 48,
    ]

    public final static Map<Integer, String> DICTIONARY_INVERSED = new LinkedHashMap<Integer, String>()
    static {
        for (e in DICTIONARY) {
            DICTIONARY_INVERSED.put(e.value, e.key)
        }
    }

    @CompileStatic
    static String findDirect(Integer i) {
        DICTIONARY.find { it.value == i }?.key
    }

    @CompileStatic
    static String getReversed(Integer i) {
        DICTIONARY_INVERSED.get(i)
    }

    @Test
    void benchmark() {
        String result1
        String result2

        Integer i = 47

        benchmark {
            findDirect {
                result1 = findDirect(i)
            }
            getReversed {
                result2 = getReversed(i)
            }

        }.prettyPrint()

        println result1

        assert result1 == result2
    }

    @Test
    void gpathBenchmark() {
        Integer result1
        Integer result2

        String key = 'DEFAULT_QOS_CHANGE'

        benchmark {
            usingGet {
                result1 = DICTIONARY.get(key)
            }
            usingGpath {
                result2 = DICTIONARY."$key"
            }

        }.prettyPrint()


        assert result1 == 47
        assert result2 == 47
    }
}
